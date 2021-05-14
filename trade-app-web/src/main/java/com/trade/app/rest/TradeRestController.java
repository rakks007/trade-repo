package com.trade.app.rest;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trade.app.controller.TradeMessageController;
import com.trade.app.vo.TradeMessageVO;

@RestController
@RequestMapping(path="/trade")
public class TradeRestController {
	@Autowired
	private TradeMessageController controller;

	@PostMapping(path= "/", consumes = "application/json", produces = "application/json")
	public void saveTrade(TradeMessageVO tradeMessageVO) {
		TradeMessageVO t = new TradeMessageVO();
		t.setCountryPartyIdentifier("A");
		t.setVersion(1);
		t.setLastupdateUser("test");
		t.setCreatedDate(String.valueOf(new Date(679797)));
		t.setTradeIdentifier("T1");
		t.setBookingIdentifier("B1");
		t.setLastupdateTime(String.valueOf(Timestamp.from(ZonedDateTime.now().toInstant())));
		controller.publishTradeMessage(t);
	}
	
	@GetMapping(path= "/",produces = "application/json")
	public String listTrades() {
		TradeMessageVO t = new TradeMessageVO();
		t.setCountryPartyIdentifier("A");
		t.setVersion(1);
		t.setLastupdateUser("test");
		t.setCreatedDate(String.valueOf(new Date(679797)));
		t.setTradeIdentifier("T1");
		t.setBookingIdentifier("B1");
		t.setLastupdateTime(String.valueOf(Timestamp.from(ZonedDateTime.now().toInstant())));
		controller.publishTradeMessage(t);
		return "Hello World";
	}
}
