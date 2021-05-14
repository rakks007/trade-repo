package com.trade.app.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TradeMessageVO implements Serializable {

	@JsonProperty("version")
	private Integer version;

	@JsonProperty("trade_id")
	private String tradeIdentifier;

	@JsonProperty("country_party_id")
	private String countryPartyIdentifier;

	@JsonProperty("maturity_date")
	private String maturityDate;

	@JsonProperty("created_date")
	private String createdDate;

	@JsonProperty("lastupdated_user")
	private String lastupdateUser;

	@JsonProperty("is_expired")
	private String isExpired;

	@JsonProperty("booked_id")
	private String bookingIdentifier;

	@JsonProperty("lastupdated_time")	
	private String lastupdateTime;
	
	
}
