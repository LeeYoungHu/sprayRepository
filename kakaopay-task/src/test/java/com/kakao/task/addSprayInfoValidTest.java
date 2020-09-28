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

/**
 * 뿌리기 API 로직 점검
 * 
 * @author leeyh
 *
 */
public class addSprayInfoValidTest extends SpringTestSupport {
	
	@Test
	@DisplayName("뿌리기 API 공통 부 체크")
    void addSprayInfoValidCheck() throws Exception {
		ObjectMapper mapper = new ObjectMapper(); 
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("totalMoney", 10000);
		map.put("personAmount", 2);
		
		String content = mapper.writeValueAsString(map);
		
        this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", "ROOM0000000000000001")
        		.header("X-USER-ID", "00000000000000000004")        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.notExistUser))
            	.andReturn();    
	}
}
