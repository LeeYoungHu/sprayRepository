package com.kakao.task.mapper;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface sprayMapper {

	String getRoomValidation(HashMap<String, Object> param);
	
	String getAbleToken(HashMap<String, Object> param);
	
	String getTokenValidation(HashMap<String, Object> param);

	String getSprayUserValid(HashMap<String, Object> param);

	String getAlreadyReceiveInfo(HashMap<String, Object> param);

	ArrayList<HashMap<String, Object>> getNotReceiveInfos(HashMap<String, Object> param);

	HashMap<String, Object> getSprayInfo(HashMap<String, Object> param);

	ArrayList<HashMap<String, Object>> getReceiveInfos(HashMap<String, Object> param);
	
	void insertSprayMaster(HashMap<String, Object> param);

	void insertSprayDetail(HashMap<String, Object> param);

	void insertSprayRecDetail(HashMap<String, Object> hashMap);

	void insertRoomInfo(HashMap<String, Object> temp);

	void insertUserInfo(HashMap<String, Object> temp);

	void updateCreateDate(HashMap<String, Object> param);

	HashMap<String, Object> getRandomChatInfo(HashMap<String, Object> randomMap);

	ArrayList<HashMap<String, Object>> getSprayUserInfo(HashMap<String, Object> map);

}
