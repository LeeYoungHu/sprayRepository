package com.kakao.task.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;

import com.kakao.task.mapper.sprayMapper;
import com.kakao.task.model.ResultCode;

@Service
public class sprayService {
	
	@Value("${kakao.permit-chars}")
	protected String permitChars;

	@Value("${kakao.tokenLength}")
	protected String tokenLength;
	
	@Autowired
	private sprayMapper mapper;
	
	@Autowired 
	MessageSource messageSource;
	
	/**
	 * 뿌리기 정보 생성 및 셋팅
	 * @param param
	 * @param req
	 * @return
	 */
	public HashMap<String, Object> addSprayInfo(HashMap<String, Object> param, HttpServletRequest req) {
		returnHeaderInfo(param, req);
		setYearMonth(param);

		HashMap<String, Object> resultMap = new HashMap<String, Object>();	
		commonValidation(param, resultMap, req);
		if (!resultMap.isEmpty()) return resultMap;		
		String tokenValidation = "";
		
		/**
		 * 조회 시 token만 받기 때문에 2개월 범위 내 token 겹치기 방지
		 */
		while (true) {
			param.put("token", generateToken());
			tokenValidation = mapper.getAbleToken(param);
			if ("Y".equals(tokenValidation)) break;
		}		
		mapper.insertSprayMaster(param);
		ArrayList<HashMap<String, Object>> sprayInfos = returnSprayMoney((int)param.get("totalMoney"), (int)param.get("personAmount"));
		for (HashMap<String, Object> sprayInfo : sprayInfos) {
			param.putAll(sprayInfo);
			mapper.insertSprayDetail(param);			
		}
		resultMap.put("token", param.get("token"));
		getSuccessCode(resultMap);
		return resultMap;
	}

	/**
	 * 받기 정보 생성
	 * @param param
	 * @param req
	 * @return
	 */
	public HashMap<String, Object> addReceiveInfo(HashMap<String, Object> param, HttpServletRequest req) {
		returnHeaderInfo(param, req);
		setYearMonth(param);

		HashMap<String, Object> resultMap = new HashMap<String, Object>();	
		commonValidation(param, resultMap, req);
		if (!resultMap.isEmpty()) return resultMap;				
		tokenValidation(param, resultMap);
		if (!resultMap.isEmpty()) return resultMap;	

		String sprayUser = mapper.getSprayUserValid(param);
		if ("Y".equals(sprayUser)) {
			resultMap.put("resultCode", ResultCode.notReceiveMyself);
			resultMap.put("resultMessage", messageSource.getMessage(ResultCode.notReceiveMyself, null, LocaleContextHolder.getLocale()));					
			return resultMap;	
		} 
		String alreadyReceive = mapper.getAlreadyReceiveInfo(param);
		if ("Y".equals(alreadyReceive)) {
			resultMap.put("resultCode", ResultCode.alreadyReceiveUser);
			resultMap.put("resultMessage", messageSource.getMessage(ResultCode.alreadyReceiveUser, null, LocaleContextHolder.getLocale()));					
			return resultMap;	
		}
		ArrayList<HashMap<String, Object>> notReceiveInfos = mapper.getNotReceiveInfos(param);
		if (notReceiveInfos.size() == 0) {
			resultMap.put("resultCode", ResultCode.finishSpray);
			resultMap.put("resultMessage", messageSource.getMessage(ResultCode.finishSpray, null, LocaleContextHolder.getLocale()));								
			return resultMap;	
		} 
		HashMap<String, Object> receiveInfo = notReceiveInfos.get(0);
		receiveInfo.put("REC_USER_ID", param.get("userId"));
		mapper.insertSprayRecDetail(receiveInfo);
		
		resultMap.put("sprayMoney", receiveInfo.get("SPRAY_MONEY"));
		getSuccessCode(resultMap);
		return resultMap;
	}

	public HashMap<String, Object> viewSprayInfo(HashMap<String, Object> param, HttpServletRequest req) {
		returnHeaderInfo(param, req);
		setYearMonth(param);

		HashMap<String, Object> resultMap = new HashMap<String, Object>();	
		commonValidation(param, resultMap, req);
		if (!resultMap.isEmpty()) return resultMap;		
		tokenValidation(param, resultMap);
		if (!resultMap.isEmpty()) return resultMap;		
		String sprayUser = mapper.getSprayUserValid(param);
		if ("N".equals(sprayUser)) {
			resultMap.put("resultCode", ResultCode.notValidAccess);
			resultMap.put("resultMessage", messageSource.getMessage(ResultCode.notValidAccess, null, LocaleContextHolder.getLocale()));					
			return resultMap;
		}		
		
		resultMap = mapper.getSprayInfo(param);
		if (resultMap == null || resultMap.isEmpty()) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("resultCode", ResultCode.viewTimeOut);
			resultMap.put("resultMessage", messageSource.getMessage(ResultCode.viewTimeOut, null, LocaleContextHolder.getLocale()));					
			return resultMap;
		}
		resultMap.put("receiveInfos", mapper.getReceiveInfos(param));
		
		getSuccessCode(resultMap);
		return resultMap;
	}

	/**
	 * token 생성기
	 * 등록 된 임의의 단어 조합하여 생성
	 * @return token
	 */
	private String generateToken() {
		char[] chars = permitChars.toCharArray();
        String token = "";
        for (int i = 0; i < Integer.parseInt(tokenLength); i++) {
        	token += chars[(int) (Math.random() * (chars.length - 1))];
        }
        return token;
	}
	
	/**
	 * 토큰에 정상적인 문자만 있는 지 검증
	 * @param token
	 * @return
	 */
	private boolean checkToken(String token) {
		boolean tokenValidation = true;
		for (int i = 0; i < token.length(); i++) {
        	if (permitChars.indexOf(token.substring(i, i+1)) == -1) {
        		tokenValidation = false;
        		break;
        	}
		}
		return tokenValidation;
	}
	
	/**
	 * 뿌리기 머니 분배 기능
	 * 1원도 뿌려지는 게 확인 되었기에 어떤 인원에게 0원이 돌아가도 상관 없음을 가정하고 로직 작성
	 * @param totalMoney
	 * @param personAmount
	 * @return sprayInfos
	 */
	private ArrayList<HashMap<String, Object>> returnSprayMoney(int totalMoney, int personAmount) {
		ArrayList<HashMap<String, Object>> sprayInfos = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> sprayInfo = new HashMap<String, Object>();
		int sprayMoney = 0;
		for (int i = 0; i < personAmount; i++) {
			sprayInfo = new HashMap<String, Object>();
			sprayMoney = (int)((Math.random()* totalMoney));
			if (i == personAmount - 1) {
				sprayMoney = totalMoney;
			} else {
				totalMoney = totalMoney - sprayMoney;				
			}
			sprayInfo.put("seq", i + 1);
			sprayInfo.put("sprayMoney", sprayMoney);
			sprayInfos.add(sprayInfo);
		}		
		return sprayInfos;
	}
	
	/**
	 * 공통 헤더 정보 셋팅
	 * @param param
	 * @param req
	 */
	private void returnHeaderInfo(HashMap<String, Object> param, HttpServletRequest req) {
		param.put("userId", req.getHeader("X-USER-ID"));
		param.put("roomId", req.getHeader("X-ROOM-ID"));
	}
	
	/**
	 * 공통 벨리데이션 검증
	 * @param param
	 * @param resultMap
	 */
	private void commonValidation(HashMap<String, Object> param, HashMap<String, Object> resultMap, HttpServletRequest req) {
		if (StringUtils.isEmpty(req.getHeader("X-ROOM-ID"))) {
			resultMap.put("resultCode", ResultCode.notExistRoomHeaderInfo);
			resultMap.put("resultMessage", messageSource.getMessage(ResultCode.notExistRoomHeaderInfo, null, LocaleContextHolder.getLocale()));			
		}
		if (StringUtils.isEmpty(req.getHeader("X-USER-ID")) && resultMap.isEmpty()) {
			resultMap.put("resultCode", ResultCode.notExistUserHeaderInfo);
			resultMap.put("resultMessage", messageSource.getMessage(ResultCode.notExistUserHeaderInfo, null, LocaleContextHolder.getLocale()));			
		}		
		if (resultMap.isEmpty()) {
			String validation = mapper.getRoomValidation(param);
			if ("N".equals(validation)) {
				resultMap.put("resultCode", ResultCode.notExistUser);
				resultMap.put("resultMessage", messageSource.getMessage(ResultCode.notExistUser, null, LocaleContextHolder.getLocale()));
			}					
		}
	}
	
	/**
	 * 토큰 유효성 검증
	 * @param param
	 * @param resultMap
	 */
	private void tokenValidation(HashMap<String, Object> param, HashMap<String, Object> resultMap) {
		if (!checkToken((String)param.get("token"))) {
			resultMap.put("resultCode", ResultCode.notValidToken);
			resultMap.put("resultMessage", messageSource.getMessage(ResultCode.notValidToken, null, LocaleContextHolder.getLocale()));
		}
		if (resultMap.isEmpty()) {
			String validation = mapper.getTokenValidation(param);
			if ("N".equals(validation)) {
				resultMap.put("resultCode", ResultCode.notValidAccess);
				resultMap.put("resultMessage", messageSource.getMessage(ResultCode.notValidAccess, null, LocaleContextHolder.getLocale()));
			}					
		}
	}
	
	/**
	 * 정상 성공 셋팅
	 * @param resultMap
	 */
	private void getSuccessCode(HashMap<String, Object> resultMap) {
		resultMap.put("resultCode", ResultCode.Success);
		resultMap.put("resultMessage", messageSource.getMessage(ResultCode.Success, null, LocaleContextHolder.getLocale()));		
	}
	
	/**
	 * 파티션 테이블 조회를 위한 년월 셋팅
	 * @param param
	 */
	private void setYearMonth(HashMap<String, Object> param) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		param.put("yearMonth", new SimpleDateFormat("yyyyMM").format(cal.getTime()));
		cal.add(Calendar.MONTH, -1);
		param.put("preYearMonth", new SimpleDateFormat("yyyyMM").format(cal.getTime()));		
	}
	
	/**
	 * error 발생 시 결과 값, 메시지 리턴
	 * 많이 발생하여도 가장 위에 있는 메시지 하나만 리턴해줌
	 * @param list
	 * @return
	 */
	public HashMap<String, Object> getErrorCode(List<ObjectError> list) {
		HashMap<String, Object> tempMap = new HashMap<String, Object>();
		tempMap.put("resultCode", list.get(0).getDefaultMessage());
		tempMap.put("resultMessage", messageSource.getMessage(list.get(0).getDefaultMessage(), null, LocaleContextHolder.getLocale()));	
		return tempMap;
	}
	
	/**
	 * 임의의 채팅방 및 유저 생성 용 메소드
	 * 십만개의 룸 생성 + 한 방에 3명 이상의 유저 생성
	 */
	public void addChatInfo() {		
		for (int i = 0; i < 100000; i++) {
			HashMap<String, Object> temp = new HashMap<String, Object>();
			temp.put("ROOMNO", i + 1);
			mapper.insertRoomInfo(temp);
			int userMod = i % 10;
			if (userMod < 3) userMod = 3;
			for (int j = 0; j < userMod; j++) {
				temp.put("USERNO", j + 1);
				mapper.insertUserInfo(temp);
			}
		}		
	}
	
	/**
	 * 날짜 관련 테스트 위해 임의로 생성한 메소드
	 * param에 담긴 second 값으로 업데이트 시킴
	 * @param param
	 * @param req
	 */
	public void updateCreateDate(HashMap<String, Object> param) {
		setYearMonth(param);
		mapper.updateCreateDate(param);
	}
	
	/**
	 * 임의의 방 선정 후 뿌릴 인원(총 방 인원 -1), 뿌릴 금액(랜덤 배정 1~1000000사이) 리턴
	 * @return
	 */
	public HashMap<String, Object> getRandomChatIfno() {
		HashMap<String, Object> randomMap = new HashMap<String, Object>();
		String roomId = "ROOM" + String.format("%016d", (int)((Math.random()* 99999)) + 1);
		randomMap.put("roomId", roomId);
		randomMap = mapper.getRandomChatInfo(randomMap);
		randomMap.put("totalMoney", (int)((Math.random()* 1000000)) + 1);
		
		return randomMap;
	}

	public ArrayList<HashMap<String, Object>> getSprayUserInfo(HashMap<String, Object> map) {
		return mapper.getSprayUserInfo(map);	
	}

}
