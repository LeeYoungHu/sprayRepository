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

public class addReceiveInfoValidTest extends SpringTestSupport {
	
	@Test
	@DisplayName("받기 API 로직 체크")
    void addReceiveInfoValidCheck() throws Exception {
		ObjectMapper mapper = new ObjectMapper(); 
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("totalMoney", 10000);
		map.put("personAmount", 2);
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", "ROOM0000000000000001")
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
		map.put("token", "@12");
		content = mapper.writeValueAsString(map);
		
		this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
        		.header("X-ROOM-ID", "ROOM0000000000000001")
        		.header("X-USER-ID", "00000000000000000001")        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.notValidToken))
            	.andReturn();    
		
		map.put("token", orgToken);
		content = mapper.writeValueAsString(map);
		
		this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
        		.header("X-ROOM-ID", "ROOM0000000000000001")
        		.header("X-USER-ID", "00000000000000000001")        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.notReceiveMyself))
            	.andReturn();  
		
		this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
        		.header("X-ROOM-ID", "ROOM0000000000000001")
        		.header("X-USER-ID", "00000000000000000004")        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.notExistUser))
            	.andReturn();  

		this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
        		.header("X-ROOM-ID", "ROOM0000000000000001")
        		.header("X-USER-ID", "00000000000000000002")        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  

		this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
        		.header("X-ROOM-ID", "ROOM0000000000000001")
        		.header("X-USER-ID", "00000000000000000002")        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.alreadyReceiveUser))
            	.andReturn();  		
		
		map.put("second", "601");
		map.put("roomId", "ROOM0000000000000001");
		map.put("userId", "00000000000000000001");
		this.service.updateCreateDate(map); 		

		this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
        		.header("X-ROOM-ID", "ROOM0000000000000001")
        		.header("X-USER-ID", "00000000000000000003")        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.finishSpray))
            	.andReturn();  		

	}
}
