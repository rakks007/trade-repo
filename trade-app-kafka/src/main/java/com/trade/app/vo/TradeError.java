package com.trade.app.vo;

import java.time.format.DateTimeFormatter;

/**
 * A Enum Class to hold the errors in the system
 * 
 * @author
 *
 */
public enum TradeError {
	DATEFORMAT_INCORRECT("DATE FORMAT IN INCORRECT FORMAT"+DateTimeFormatter.ISO_LOCAL_DATE.toString() + "accepted"),
	DATECANNOTBEINPAST("Date cannot be in the past"),
	MATUREDATELESSTHANCREATEDDATE("Mature Date less than created date"),
	EXPIREDCANNOTBENULL("Expired Value is Empty or incorrect"),
	COUNTRYFORMATNOTCORRECT("Country Party id is Empty or Format incorrect (CP-mmmmm) possible"),
	BOOKINGIDISNULL("Booking-id is Empty or Booking format isincorrect (Bmmmmm) possible"),
	TRADEFORMATINCORRECT("Trading is empty of format is incorrect (Tmmmmm) possible");

	private String description;

	TradeError(String desc) {
		this.description = desc;
	}

	public String getDescription() {
		return this.description;
	}

}
