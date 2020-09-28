package com.kakao.task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.task.model.ResultCode;

/**
 * 병렬적 테스트 위해 만든 클래스
 * 뿌리는 대상은 공통적으로 userId 00000000000000000001 이라고 가정
 * @author leeyh
 *
 */
public class parallelTest extends SpringTestSupport {
	
	private String userId = "00000000000000000001";
	
	@Test
	void parallelTest1() throws Exception {
		HashMap<String, Object> map = this.service.getRandomChatIfno();
		ObjectMapper mapper = new ObjectMapper(); 
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
        content = result.getResponse().getContentAsString(); 
        HashMap<String, Object> resultMap = mapper.readValue(content, HashMap.class);
		resultMap.remove("resultCode");
		resultMap.remove("resultMessage");		
        
		map.put("userId", getUserId());
		ArrayList<HashMap<String, Object>> list = this.service.getSprayUserInfo(map);
		
		for (HashMap<String, Object> temp : list) {
			content = mapper.writeValueAsString(resultMap);
			this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
	        		.header("X-ROOM-ID", map.get("roomId"))
	        		.header("X-USER-ID", temp.get("userId"))        		
	            	.contentType(MediaType.APPLICATION_JSON_UTF8)
	            	.content(content))
	            	.andDo(print())
	            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
	            	.andReturn(); 
		}
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
	}
	
	@Test
	void parallelTest2() throws Exception {
		HashMap<String, Object> map = this.service.getRandomChatIfno();
		ObjectMapper mapper = new ObjectMapper(); 
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
        content = result.getResponse().getContentAsString(); 
        HashMap<String, Object> resultMap = mapper.readValue(content, HashMap.class);
		resultMap.remove("resultCode");
		resultMap.remove("resultMessage");		
        
		map.put("userId", getUserId());
		ArrayList<HashMap<String, Object>> list = this.service.getSprayUserInfo(map);
		
		for (HashMap<String, Object> temp : list) {
			content = mapper.writeValueAsString(resultMap);
			this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
	        		.header("X-ROOM-ID", map.get("roomId"))
	        		.header("X-USER-ID", temp.get("userId"))        		
	            	.contentType(MediaType.APPLICATION_JSON_UTF8)
	            	.content(content))
	            	.andDo(print())
	            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
	            	.andReturn(); 
		}
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
	}
	
	@Test
	void parallelTest3() throws Exception {
		HashMap<String, Object> map = this.service.getRandomChatIfno();
		ObjectMapper mapper = new ObjectMapper(); 
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
        content = result.getResponse().getContentAsString(); 
        HashMap<String, Object> resultMap = mapper.readValue(content, HashMap.class);
		resultMap.remove("resultCode");
		resultMap.remove("resultMessage");		
        
		map.put("userId", getUserId());
		ArrayList<HashMap<String, Object>> list = this.service.getSprayUserInfo(map);
		
		for (HashMap<String, Object> temp : list) {
			content = mapper.writeValueAsString(resultMap);
			this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
	        		.header("X-ROOM-ID", map.get("roomId"))
	        		.header("X-USER-ID", temp.get("userId"))        		
	            	.contentType(MediaType.APPLICATION_JSON_UTF8)
	            	.content(content))
	            	.andDo(print())
	            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
	            	.andReturn(); 
		}
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
	}
	
	@Test
	void parallelTest4() throws Exception {
		HashMap<String, Object> map = this.service.getRandomChatIfno();
		ObjectMapper mapper = new ObjectMapper(); 
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
        content = result.getResponse().getContentAsString(); 
        HashMap<String, Object> resultMap = mapper.readValue(content, HashMap.class);
		resultMap.remove("resultCode");
		resultMap.remove("resultMessage");		
        
		map.put("userId", getUserId());
		ArrayList<HashMap<String, Object>> list = this.service.getSprayUserInfo(map);
		
		for (HashMap<String, Object> temp : list) {
			content = mapper.writeValueAsString(resultMap);
			this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
	        		.header("X-ROOM-ID", map.get("roomId"))
	        		.header("X-USER-ID", temp.get("userId"))        		
	            	.contentType(MediaType.APPLICATION_JSON_UTF8)
	            	.content(content))
	            	.andDo(print())
	            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
	            	.andReturn(); 
		}
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
	}
	
	@Test
	void parallelTest5() throws Exception {
		HashMap<String, Object> map = this.service.getRandomChatIfno();
		ObjectMapper mapper = new ObjectMapper(); 
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
        content = result.getResponse().getContentAsString(); 
        HashMap<String, Object> resultMap = mapper.readValue(content, HashMap.class);
		resultMap.remove("resultCode");
		resultMap.remove("resultMessage");		
        
		map.put("userId", getUserId());
		ArrayList<HashMap<String, Object>> list = this.service.getSprayUserInfo(map);
		
		for (HashMap<String, Object> temp : list) {
			content = mapper.writeValueAsString(resultMap);
			this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
	        		.header("X-ROOM-ID", map.get("roomId"))
	        		.header("X-USER-ID", temp.get("userId"))        		
	            	.contentType(MediaType.APPLICATION_JSON_UTF8)
	            	.content(content))
	            	.andDo(print())
	            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
	            	.andReturn(); 
		}
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
	}
	
	@Test
	void parallelTest6() throws Exception {
		HashMap<String, Object> map = this.service.getRandomChatIfno();
		ObjectMapper mapper = new ObjectMapper(); 
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
        content = result.getResponse().getContentAsString(); 
        HashMap<String, Object> resultMap = mapper.readValue(content, HashMap.class);
		resultMap.remove("resultCode");
		resultMap.remove("resultMessage");		
        
		map.put("userId", getUserId());
		ArrayList<HashMap<String, Object>> list = this.service.getSprayUserInfo(map);
		
		for (HashMap<String, Object> temp : list) {
			content = mapper.writeValueAsString(resultMap);
			this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
	        		.header("X-ROOM-ID", map.get("roomId"))
	        		.header("X-USER-ID", temp.get("userId"))        		
	            	.contentType(MediaType.APPLICATION_JSON_UTF8)
	            	.content(content))
	            	.andDo(print())
	            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
	            	.andReturn(); 
		}
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
	}
	
	@Test
	void parallelTest7() throws Exception {
		HashMap<String, Object> map = this.service.getRandomChatIfno();
		ObjectMapper mapper = new ObjectMapper(); 
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
        content = result.getResponse().getContentAsString(); 
        HashMap<String, Object> resultMap = mapper.readValue(content, HashMap.class);
		resultMap.remove("resultCode");
		resultMap.remove("resultMessage");		
        
		map.put("userId", getUserId());
		ArrayList<HashMap<String, Object>> list = this.service.getSprayUserInfo(map);
		
		for (HashMap<String, Object> temp : list) {
			content = mapper.writeValueAsString(resultMap);
			this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
	        		.header("X-ROOM-ID", map.get("roomId"))
	        		.header("X-USER-ID", temp.get("userId"))        		
	            	.contentType(MediaType.APPLICATION_JSON_UTF8)
	            	.content(content))
	            	.andDo(print())
	            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
	            	.andReturn(); 
		}
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
	}
	
	@Test
	void parallelTest8() throws Exception {
		HashMap<String, Object> map = this.service.getRandomChatIfno();
		ObjectMapper mapper = new ObjectMapper(); 
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
        content = result.getResponse().getContentAsString(); 
        HashMap<String, Object> resultMap = mapper.readValue(content, HashMap.class);
		resultMap.remove("resultCode");
		resultMap.remove("resultMessage");		
        
		map.put("userId", getUserId());
		ArrayList<HashMap<String, Object>> list = this.service.getSprayUserInfo(map);
		
		for (HashMap<String, Object> temp : list) {
			content = mapper.writeValueAsString(resultMap);
			this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
	        		.header("X-ROOM-ID", map.get("roomId"))
	        		.header("X-USER-ID", temp.get("userId"))        		
	            	.contentType(MediaType.APPLICATION_JSON_UTF8)
	            	.content(content))
	            	.andDo(print())
	            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
	            	.andReturn(); 
		}
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
	}
	
	@Test
	void parallelTest9() throws Exception {
		HashMap<String, Object> map = this.service.getRandomChatIfno();
		ObjectMapper mapper = new ObjectMapper(); 
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
        content = result.getResponse().getContentAsString(); 
        HashMap<String, Object> resultMap = mapper.readValue(content, HashMap.class);
		resultMap.remove("resultCode");
		resultMap.remove("resultMessage");		
        
		map.put("userId", getUserId());
		ArrayList<HashMap<String, Object>> list = this.service.getSprayUserInfo(map);
		
		for (HashMap<String, Object> temp : list) {
			content = mapper.writeValueAsString(resultMap);
			this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
	        		.header("X-ROOM-ID", map.get("roomId"))
	        		.header("X-USER-ID", temp.get("userId"))        		
	            	.contentType(MediaType.APPLICATION_JSON_UTF8)
	            	.content(content))
	            	.andDo(print())
	            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
	            	.andReturn(); 
		}
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
	}
	
	@Test
	void parallelTest10() throws Exception {
		HashMap<String, Object> map = this.service.getRandomChatIfno();
		ObjectMapper mapper = new ObjectMapper(); 
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
        content = result.getResponse().getContentAsString(); 
        HashMap<String, Object> resultMap = mapper.readValue(content, HashMap.class);
		resultMap.remove("resultCode");
		resultMap.remove("resultMessage");		
        
		map.put("userId", getUserId());
		ArrayList<HashMap<String, Object>> list = this.service.getSprayUserInfo(map);
		
		for (HashMap<String, Object> temp : list) {
			content = mapper.writeValueAsString(resultMap);
			this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
	        		.header("X-ROOM-ID", map.get("roomId"))
	        		.header("X-USER-ID", temp.get("userId"))        		
	            	.contentType(MediaType.APPLICATION_JSON_UTF8)
	            	.content(content))
	            	.andDo(print())
	            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
	            	.andReturn(); 
		}
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
	}
	
	@Test
	void parallelTest11() throws Exception {
		HashMap<String, Object> map = this.service.getRandomChatIfno();
		ObjectMapper mapper = new ObjectMapper(); 
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
        content = result.getResponse().getContentAsString(); 
        HashMap<String, Object> resultMap = mapper.readValue(content, HashMap.class);
		resultMap.remove("resultCode");
		resultMap.remove("resultMessage");		
        
		map.put("userId", getUserId());
		ArrayList<HashMap<String, Object>> list = this.service.getSprayUserInfo(map);
		
		for (HashMap<String, Object> temp : list) {
			content = mapper.writeValueAsString(resultMap);
			this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
	        		.header("X-ROOM-ID", map.get("roomId"))
	        		.header("X-USER-ID", temp.get("userId"))        		
	            	.contentType(MediaType.APPLICATION_JSON_UTF8)
	            	.content(content))
	            	.andDo(print())
	            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
	            	.andReturn(); 
		}
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
	}
	
	@Test
	void parallelTest12() throws Exception {
		HashMap<String, Object> map = this.service.getRandomChatIfno();
		ObjectMapper mapper = new ObjectMapper(); 
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
        content = result.getResponse().getContentAsString(); 
        HashMap<String, Object> resultMap = mapper.readValue(content, HashMap.class);
		resultMap.remove("resultCode");
		resultMap.remove("resultMessage");		
        
		map.put("userId", getUserId());
		ArrayList<HashMap<String, Object>> list = this.service.getSprayUserInfo(map);
		
		for (HashMap<String, Object> temp : list) {
			content = mapper.writeValueAsString(resultMap);
			this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
	        		.header("X-ROOM-ID", map.get("roomId"))
	        		.header("X-USER-ID", temp.get("userId"))        		
	            	.contentType(MediaType.APPLICATION_JSON_UTF8)
	            	.content(content))
	            	.andDo(print())
	            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
	            	.andReturn(); 
		}
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
	}
	
	@Test
	void parallelTest13() throws Exception {
		HashMap<String, Object> map = this.service.getRandomChatIfno();
		ObjectMapper mapper = new ObjectMapper(); 
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
        content = result.getResponse().getContentAsString(); 
        HashMap<String, Object> resultMap = mapper.readValue(content, HashMap.class);
		resultMap.remove("resultCode");
		resultMap.remove("resultMessage");		
        
		map.put("userId", getUserId());
		ArrayList<HashMap<String, Object>> list = this.service.getSprayUserInfo(map);
		
		for (HashMap<String, Object> temp : list) {
			content = mapper.writeValueAsString(resultMap);
			this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
	        		.header("X-ROOM-ID", map.get("roomId"))
	        		.header("X-USER-ID", temp.get("userId"))        		
	            	.contentType(MediaType.APPLICATION_JSON_UTF8)
	            	.content(content))
	            	.andDo(print())
	            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
	            	.andReturn(); 
		}
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
	}
	
	@Test
	void parallelTest14() throws Exception {
		HashMap<String, Object> map = this.service.getRandomChatIfno();
		ObjectMapper mapper = new ObjectMapper(); 
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
        content = result.getResponse().getContentAsString(); 
        HashMap<String, Object> resultMap = mapper.readValue(content, HashMap.class);
		resultMap.remove("resultCode");
		resultMap.remove("resultMessage");		
        
		map.put("userId", getUserId());
		ArrayList<HashMap<String, Object>> list = this.service.getSprayUserInfo(map);
		
		for (HashMap<String, Object> temp : list) {
			content = mapper.writeValueAsString(resultMap);
			this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
	        		.header("X-ROOM-ID", map.get("roomId"))
	        		.header("X-USER-ID", temp.get("userId"))        		
	            	.contentType(MediaType.APPLICATION_JSON_UTF8)
	            	.content(content))
	            	.andDo(print())
	            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
	            	.andReturn(); 
		}
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
	}
	
	@Test
	void parallelTest15() throws Exception {
		HashMap<String, Object> map = this.service.getRandomChatIfno();
		ObjectMapper mapper = new ObjectMapper(); 
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
        content = result.getResponse().getContentAsString(); 
        HashMap<String, Object> resultMap = mapper.readValue(content, HashMap.class);
		resultMap.remove("resultCode");
		resultMap.remove("resultMessage");		
        
		map.put("userId", getUserId());
		ArrayList<HashMap<String, Object>> list = this.service.getSprayUserInfo(map);
		
		for (HashMap<String, Object> temp : list) {
			content = mapper.writeValueAsString(resultMap);
			this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
	        		.header("X-ROOM-ID", map.get("roomId"))
	        		.header("X-USER-ID", temp.get("userId"))        		
	            	.contentType(MediaType.APPLICATION_JSON_UTF8)
	            	.content(content))
	            	.andDo(print())
	            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
	            	.andReturn(); 
		}
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
	}
	
	@Test
	void parallelTest16() throws Exception {
		HashMap<String, Object> map = this.service.getRandomChatIfno();
		ObjectMapper mapper = new ObjectMapper(); 
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
        content = result.getResponse().getContentAsString(); 
        HashMap<String, Object> resultMap = mapper.readValue(content, HashMap.class);
		resultMap.remove("resultCode");
		resultMap.remove("resultMessage");		
        
		map.put("userId", getUserId());
		ArrayList<HashMap<String, Object>> list = this.service.getSprayUserInfo(map);
		
		for (HashMap<String, Object> temp : list) {
			content = mapper.writeValueAsString(resultMap);
			this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
	        		.header("X-ROOM-ID", map.get("roomId"))
	        		.header("X-USER-ID", temp.get("userId"))        		
	            	.contentType(MediaType.APPLICATION_JSON_UTF8)
	            	.content(content))
	            	.andDo(print())
	            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
	            	.andReturn(); 
		}
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
	}
	
	@Test
	void parallelTest17() throws Exception {
		HashMap<String, Object> map = this.service.getRandomChatIfno();
		ObjectMapper mapper = new ObjectMapper(); 
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
        content = result.getResponse().getContentAsString(); 
        HashMap<String, Object> resultMap = mapper.readValue(content, HashMap.class);
		resultMap.remove("resultCode");
		resultMap.remove("resultMessage");		
        
		map.put("userId", getUserId());
		ArrayList<HashMap<String, Object>> list = this.service.getSprayUserInfo(map);
		
		for (HashMap<String, Object> temp : list) {
			content = mapper.writeValueAsString(resultMap);
			this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
	        		.header("X-ROOM-ID", map.get("roomId"))
	        		.header("X-USER-ID", temp.get("userId"))        		
	            	.contentType(MediaType.APPLICATION_JSON_UTF8)
	            	.content(content))
	            	.andDo(print())
	            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
	            	.andReturn(); 
		}
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
	}
	
	@Test
	void parallelTest18() throws Exception {
		HashMap<String, Object> map = this.service.getRandomChatIfno();
		ObjectMapper mapper = new ObjectMapper(); 
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
        content = result.getResponse().getContentAsString(); 
        HashMap<String, Object> resultMap = mapper.readValue(content, HashMap.class);
		resultMap.remove("resultCode");
		resultMap.remove("resultMessage");		
        
		map.put("userId", getUserId());
		ArrayList<HashMap<String, Object>> list = this.service.getSprayUserInfo(map);
		
		for (HashMap<String, Object> temp : list) {
			content = mapper.writeValueAsString(resultMap);
			this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
	        		.header("X-ROOM-ID", map.get("roomId"))
	        		.header("X-USER-ID", temp.get("userId"))        		
	            	.contentType(MediaType.APPLICATION_JSON_UTF8)
	            	.content(content))
	            	.andDo(print())
	            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
	            	.andReturn(); 
		}
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
	}
	
	@Test
	void parallelTest19() throws Exception {
		HashMap<String, Object> map = this.service.getRandomChatIfno();
		ObjectMapper mapper = new ObjectMapper(); 
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
        content = result.getResponse().getContentAsString(); 
        HashMap<String, Object> resultMap = mapper.readValue(content, HashMap.class);
		resultMap.remove("resultCode");
		resultMap.remove("resultMessage");		
        
		map.put("userId", getUserId());
		ArrayList<HashMap<String, Object>> list = this.service.getSprayUserInfo(map);
		
		for (HashMap<String, Object> temp : list) {
			content = mapper.writeValueAsString(resultMap);
			this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
	        		.header("X-ROOM-ID", map.get("roomId"))
	        		.header("X-USER-ID", temp.get("userId"))        		
	            	.contentType(MediaType.APPLICATION_JSON_UTF8)
	            	.content(content))
	            	.andDo(print())
	            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
	            	.andReturn(); 
		}
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
	}
	
	@Test
	void parallelTest20() throws Exception {
		HashMap<String, Object> map = this.service.getRandomChatIfno();
		ObjectMapper mapper = new ObjectMapper(); 
		
		String content = mapper.writeValueAsString(map);
		
		MvcResult result = this.mockMvc.perform(post("/kakao/spray/addSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
		
        content = result.getResponse().getContentAsString(); 
        HashMap<String, Object> resultMap = mapper.readValue(content, HashMap.class);
		resultMap.remove("resultCode");
		resultMap.remove("resultMessage");		
        
		map.put("userId", getUserId());
		ArrayList<HashMap<String, Object>> list = this.service.getSprayUserInfo(map);
		
		for (HashMap<String, Object> temp : list) {
			content = mapper.writeValueAsString(resultMap);
			this.mockMvc.perform(post("/kakao/spray/addReceiveInfo")
	        		.header("X-ROOM-ID", map.get("roomId"))
	        		.header("X-USER-ID", temp.get("userId"))        		
	            	.contentType(MediaType.APPLICATION_JSON_UTF8)
	            	.content(content))
	            	.andDo(print())
	            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
	            	.andReturn(); 
		}
		
		this.mockMvc.perform(post("/kakao/spray/viewSprayInfo")
        		.header("X-ROOM-ID", map.get("roomId"))
        		.header("X-USER-ID", getUserId())        		
            	.contentType(MediaType.APPLICATION_JSON_UTF8)
            	.content(content))
            	.andDo(print())
            	.andExpect(jsonPath("$.resultCode").value(ResultCode.Success))
            	.andReturn();  
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
