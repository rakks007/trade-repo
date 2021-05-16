package com.trade.app.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TradeDisplayVO {

	private int version;

	@JsonProperty("trade_id")
	private String tradeIdentifier;

	@JsonProperty("country_party_id")
	private String countryPartyIdentifier;

	@JsonProperty("maturity_date")
	private LocalDate maturityDate;

	@JsonProperty("created_date")
	private LocalDate createdDate;

	@JsonProperty("lastupdated_user")
	private String lastupdateUser;

	@JsonProperty("is_expired")
	private String isExpired;

	@JsonProperty("booked_id")
	private String bookingIdentifier;

	@JsonProperty("lastupdated_time")
	private LocalDateTime lastupdateTime;
	
	@JsonProperty("id")
	private long id;
}
