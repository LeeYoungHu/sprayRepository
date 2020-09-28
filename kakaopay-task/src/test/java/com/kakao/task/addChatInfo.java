package com.kakao.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class addChatInfo extends SpringTestSupport {
	
	@Test
	@DisplayName("임의의 채팅방 및 유저 생성")
	void addChatInfo() {
		this.service.addChatInfo();
	}
}
