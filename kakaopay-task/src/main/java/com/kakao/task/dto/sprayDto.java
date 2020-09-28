package com.kakao.task.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.kakao.task.model.ResultCode;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class sprayDto {
	
	@Min(value = 1, message = ResultCode.minTotalMoney)
	@NotNull(message = ResultCode.notNullTotalMoney)
	private int totalMoney;

	@Min(value = 2, message = ResultCode.minPersonAmount)
	@NotNull(message = ResultCode.notNullPersonAmount)
	private int personAmount;
	
	@Builder
	public sprayDto(int totalMoney, int personAmount) {
		this.totalMoney = totalMoney;
		this.personAmount = personAmount;
    }
}
