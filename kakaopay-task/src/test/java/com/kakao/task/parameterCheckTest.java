package com.kakao.task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.task.model.ResultCode;

public class parameterCheckTest extends SpringTestSupport {
	
	@Test
	@DisplayName("뿌리기 API 파라미터 체크")
    void addSprayInfoParamCheck() throws Exception {
		ObjectMapper mapper = new ObjectMapper(); 
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("totalMoney", 0);
		map.put("personAmount", 2);
		
		String content = mapper.writeValueAsString(map);

        this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        	.contentType(MediaType.APPLICATION_JSON_UTF8)
        	.content(content))
        	.andDo(print())
        	.andExpect(jsonPath("$.resultCode").value(ResultCode.minTotalMoney))
        	.andReturn();

		map.put("totalMoney", 10000);
		map.put("personAmount", 1);
		
		content = mapper.writeValueAsString(map);
        
        this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.minPersonAmount))
            	.andReturn();        
        
		map.put("totalMoney", 10000);
		map.put("personAmount", 2);
		
		content = mapper.writeValueAsString(map);
        
        this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.notExistRoomHeaderInfo))
            	.andReturn();       
        
        this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", "ROOM0000000000000001")
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.notExistUserHeaderInfo))
            	.andReturn();      
    }
	
	@Test
	@DisplayName("받기 API 파라미터 체크")
    void addReceiveInfoParamCheck() throws Exception {
		ObjectMapper mapper = new ObjectMapper(); 
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("token", null);
		
		String content = mapper.writeValueAsString(map);

        this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
        	.contentType(MediaType.APPLICATION_JSON_UTF8)
        	.content(content))
        	.andDo(print())
        	.andExpect(jsonPath("$.resultCode").value(ResultCode.notEmptyToken))
        	.andReturn();

		map.put("token", "12");
		
		content = mapper.writeValueAsString(map);
        
        this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.tokenLengthValidation))
            	.andReturn();        
        
		map.put("token", "123");
		
		content = mapper.writeValueAsString(map);
        
        this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.notExistRoomHeaderInfo))
            	.andReturn();       
        
        this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
        		.header("X-ROOM-ID", "ROOM0000000000000001")
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.notExistUserHeaderInfo))
            	.andReturn();      

    }
	
	@Test
	@DisplayName("조회 API 파라미터 체크")
    void viewSprayInfoParamCheck() throws Exception {
		ObjectMapper mapper = new ObjectMapper(); 
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("token", null);
		
		String content = mapper.writeValueAsString(map);

        this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        	.contentType(MediaType.APPLICATION_JSON_UTF8)
        	.content(content))
        	.andDo(print())
        	.andExpect(jsonPath("$.resultCode").value(ResultCode.notEmptyToken))
        	.andReturn();

		map.put("token", "12");
		
		content = mapper.writeValueAsString(map);
        
        this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.tokenLengthValidation))
            	.andReturn();        
        
		map.put("token", "123");
		
		content = mapper.writeValueAsString(map);
        
        this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.notExistRoomHeaderInfo))
            	.andReturn();       
        
        this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", "ROOM0000000000000001")
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.notExistUserHeaderInfo))
            	.andReturn();      

    }
}
