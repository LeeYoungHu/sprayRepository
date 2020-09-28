package com.kakao.task.util;

import org.springframework.beans.factory.annotation.Value;

public class CommonUtils {
	
	@Value("kakao.permit-chars")
	protected static String permitChars;

	public static String generateToken() {
		char[] chars = permitChars.toCharArray();
        String token = "";
        for (int i = 0; i < 3; i++) {
        	token += chars[(int) (Math.random() * (chars.length - 1))];
        }
        return token;
	}
	
}
