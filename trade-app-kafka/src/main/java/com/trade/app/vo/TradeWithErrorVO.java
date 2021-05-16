package com.trade.app.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TradeWithErrorVO {

	private TradeMessageVO message;
	@JsonProperty("message_status")
	private String tradeMessageStatus;
	@JsonProperty("message_error")
	private String tradeMessageError;

}
