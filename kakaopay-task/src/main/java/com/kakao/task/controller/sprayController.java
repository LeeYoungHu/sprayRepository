package com.kakao.task.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kakao.task.dto.sprayDto;
import com.kakao.task.dto.tokenDto;
import com.kakao.task.service.sprayService;

@RestController
@RequestMapping("/kakao/spray/*")
public class sprayController {
	
	@Autowired
	private sprayService service;
	
	@RequestMapping(value= "/addSprayInfo",consumes = "application/json;charset=utf-8",produces = "application/json;charset=utf-8")
	public HashMap<String, Object> addSprayInfo(@RequestBody @Valid sprayDto dto, HttpServletRequest req) {		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("personAmount", dto.getPersonAmount());
		param.put("totalMoney", dto.getTotalMoney());
		
		HashMap<String, Object> resultMap = service.addSprayInfo(param, req);		
		return resultMap;
	}
	
	@RequestMapping(value= "/addReceiveInfo",consumes = "application/json;charset=utf-8",produces = "application/json;charset=utf-8")
	public HashMap<String, Object> receiveSpray(@RequestBody @Valid tokenDto dto, HttpServletRequest req) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("token", dto.getToken());

		HashMap<String, Object> resultMap = service.addReceiveInfo(param, req);		
		return resultMap;
	}

	@RequestMapping(value= "/viewSprayInfo",consumes = "application/json;charset=utf-8",produces = "application/json;charset=utf-8")
	public HashMap<String, Object> viewSprayInfo(@RequestBody @Valid tokenDto dto, HttpServletRequest req) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("token", dto.getToken());

		HashMap<String, Object> resultMap = service.viewSprayInfo(param, req);		
		return resultMap;
	}
	
}
