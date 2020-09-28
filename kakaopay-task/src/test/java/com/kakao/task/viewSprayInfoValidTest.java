package com.kakao.task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.task.model.ResultCode;

public class viewSprayInfoValidTest extends SpringTestSupport {

	@Test
	@DisplayName("조회 API 로직 체크")
    void viewSprayInfoValidCheck() throws Exception {		
		ObjectMapper mapper = new ObjectMapper(); 
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("totalMoney", 30000);
		map.put("personAmount", 8);
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", "ROOM0000000000000010")
        		.header("X-USER-ID", "00000000000000000001")        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();    
        
        content = result.getResponse().getContentAsString(); 
		map = mapper.readValue(content, HashMap.class);
		String orgToken = (String) map.get("token");
		
		map = new HashMap<String, Object>();
		map.put("token", orgToken);
		
		content = mapper.writeValueAsString(map);
		
		this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
        		.header("X-ROOM-ID", "ROOM0000000000000010")
        		.header("X-USER-ID", "00000000000000000002")        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  

		this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
        		.header("X-ROOM-ID", "ROOM0000000000000010")
        		.header("X-USER-ID", "00000000000000000003")        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  

		this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
        		.header("X-ROOM-ID", "ROOM0000000000000010")
        		.header("X-USER-ID", "00000000000000000004")        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  

		this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
        		.header("X-ROOM-ID", "ROOM0000000000000010")
        		.header("X-USER-ID", "00000000000000000005")        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", "ROOM0000000000000010")
        		.header("X-USER-ID", "00000000000000000001")        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", "ROOM0000000000000010")
        		.header("X-USER-ID", "00000000000000000005")        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.notValidAccess))
            	.andReturn();  
		
		map.put("date", "8");
		map.put("roomId", "ROOM0000000000000010");
		map.put("userId", "00000000000000000001");
		this.service.updateCreateDate(map);
			
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", "ROOM0000000000000010")
        		.header("X-USER-ID", "00000000000000000001")        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.viewTimeOut))
            	.andReturn();
	}
}
