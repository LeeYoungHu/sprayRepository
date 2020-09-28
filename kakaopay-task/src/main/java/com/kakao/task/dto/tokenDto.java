package com.kakao.task.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.kakao.task.model.ResultCode;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class tokenDto {
	
	@NotEmpty(message = ResultCode.notEmptyToken)
	@Size(min = 3, max = 3, message = ResultCode.tokenLengthValidation)
	private String token;
	
	public tokenDto(String token) {
		this.token = token;
    }
}
