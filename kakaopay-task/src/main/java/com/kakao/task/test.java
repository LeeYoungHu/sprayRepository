package com.kakao.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class test {
	
	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper(); 
		String content = mapper.writeValueAsString("123");
		System.out.println(content);
	}

}
