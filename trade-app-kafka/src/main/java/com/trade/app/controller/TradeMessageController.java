package com.trade.app.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trade.app.entity.Trade;
import com.trade.app.repo.TradeRepository;
import com.trade.app.stream.TradeEventListener;
import com.trade.app.vo.TradeMessageVO;

@Component
public class TradeMessageController {
	
	@Autowired
	private TradeRepository tradeRepository;
	
	@Autowired
	private TradeEventListener listner;

	/**
	 * 
	 * @param tradeMessageVO
	 */
	public void saveTrade(TradeMessageVO tradeMessageVO) {
		Trade t = new Trade();
		t.setCountryPartyIdentifier("A");
		t.setVersion(1);
		t.setLastupdateUser("test");
		t.setCreatedDate(new Date(679797));
		t.setTradeIdentifier(tradeMessageVO.getTradeIdentifier());
		t.setBookingIdentifier("B1");
		t.setLastupdateTime(Timestamp.from(ZonedDateTime.now().toInstant()));
		tradeRepository.save(t);
	}
	
	/**
	 * 
	 * @param tradeMessageVO
	 */
	public void publishTradeMessage(TradeMessageVO tradeMessageVO) {
		listner.publishTrade(tradeMessageVO);		
	}
}
