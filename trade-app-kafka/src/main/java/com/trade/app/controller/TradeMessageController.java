package com.trade.app.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.app.entity.Trade;
import com.trade.app.entity.TradeMessage;
import com.trade.app.repo.TradeMessageRepository;
import com.trade.app.repo.TradeRepository;
import com.trade.app.stream.TradeEventListener;
import com.trade.app.stream.helper.TradeEventHelper;
import com.trade.app.validator.TradeMessageValidator;
import com.trade.app.vo.ErrorVO;
import com.trade.app.vo.TradeDisplayVO;
import com.trade.app.vo.TradeListVO;
import com.trade.app.vo.TradeMessageVO;
import com.trade.app.vo.TradeWithErrorVO;

@Component
public class TradeMessageController {

	@Autowired
	private TradeRepository tradeRepository;
	@Autowired
	private TradeMessageRepository tradeMessageRepository;

	@Autowired
	private ObjectMapper objectMapper;
	public static final Logger LOGGER = LoggerFactory.getLogger(TradeMessageController.class);

	@Autowired
	private TradeEventListener listner;

	/**
	 * 
	 * @param tradeMessageVO
	 */
	public void saveTrade(TradeMessageVO tradeMessageVO, String payload) {
		List<ErrorVO> errors = TradeMessageValidator.validate(tradeMessageVO);
		if (errors == null || errors.isEmpty()) {
			populateTradeAndSave(tradeMessageVO);
			
			
		}
		populateTradeMessageAndSave(tradeMessageVO.getTradeIdentifier(), payload, errors);
	}

	/**
	 * 
	 * @param tradeMessageVO
	 * @param payload
	 * @param errors
	 */
	private void populateTradeMessageAndSave(String tradeId, String payload, List<ErrorVO> errors) {
		TradeMessage message = new TradeMessage();
		message.setMessage(payload);
		message.setTradeId(tradeId);
		message.setTradeMessageStatus("S");
		if (errors != null && !errors.isEmpty()) {
			message.setTradeMessageError(getString(errors));
			message.setTradeMessageStatus("F");
		}
		tradeMessageRepository.save(message);
	}

	/**
	 * 
	 * @param list
	 * @return
	 */
	public String getString(List<ErrorVO> list) {
		String payload = null;
		try {
			payload = objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			LOGGER.error(e.getMessage());
		}
		return payload;
	}

	/**
	 * 
	 * @param tradeMessageVO
	 */
	private void populateTradeAndSave(TradeMessageVO tradeMessageVO) {
		Trade trade = populateTrade(tradeMessageVO);
		tradeRepository.save(trade);
	}
	
	findby three record == 1
			
			compare all the fields in message
			

	/**
	 * 
	 * @param tradeMessageVO
	 * @return
	 */
	private Trade populateTrade(TradeMessageVO tradeMessageVO) {
		Trade trade = new Trade();
		trade.setTradeIdentifier(tradeMessageVO.getTradeIdentifier());
		trade.setBookingIdentifier(tradeMessageVO.getBookingIdentifier());
		trade.setCountryPartyIdentifier(tradeMessageVO.getCountryPartyIdentifier());
		DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
		LocalDate createDate = LocalDate.now();
		if (tradeMessageVO.getCreatedDate() != null) {
			createDate = LocalDate.parse(tradeMessageVO.getCreatedDate(), dateFormatter);
		}
		trade.setCreatedDate(createDate);
		LocalDate matureDate = LocalDate.now();
		if (tradeMessageVO.getMaturityDate() != null) {
			matureDate = LocalDate.parse(tradeMessageVO.getMaturityDate(), dateFormatter);
		}
		trade.setMaturityDate(matureDate.plusDays(5));
		trade.setIsExpired(tradeMessageVO.getIsExpired());
		trade.setLastupdateUser("TEST");
		return trade;
	}

	/**
	 * 
	 * @param tradeMessageVO
	 */
	public void publishTradeMessage(TradeMessageVO tradeMessageVO) {
		listner.publishTrade(tradeMessageVO);
	}

	/**
	 * 
	 * @return
	 */
	public TradeListVO findAllTrades() {
		TradeListVO tradeListVO = new TradeListVO();			
		List<Trade> trades = (List<Trade>) tradeRepository.findAll();
		if (trades != null) {
			tradeListVO.setTrades(new ArrayList<>());
			trades.forEach(t -> {
				tradeListVO.getTrades().add(populateTradeDisplayVO(t));
			});
		}

		List<TradeMessage> tradeMessages = tradeMessageRepository.findByStatus("F");
		if (tradeMessages != null && !tradeMessages.isEmpty()) {
			tradeListVO.setTradeErrors(new ArrayList<>());
			tradeMessages.forEach(t -> {
				tradeListVO.getTradeErrors().add(populateTradeErrorVO(t));
			});
		}

		return tradeListVO;
	}

	/**
	 * 
	 * @param t
	 * @return
	 */
	private TradeWithErrorVO populateTradeErrorVO(TradeMessage trade) {
		TradeWithErrorVO errorVO = new TradeWithErrorVO();
		TradeMessageVO message = getPayload(trade.getMessage());
		errorVO.setMessage(message);
		errorVO.setTradeMessageError(trade.getTradeMessageError());
		errorVO.setTradeMessageStatus(trade.getTradeMessageStatus());
		return errorVO;
	}

	/**
	 * @param t
	 * @return
	 */
	private TradeDisplayVO populateTradeDisplayVO(Trade trade) {
		TradeDisplayVO displayVO = new TradeDisplayVO();
		displayVO.setBookingIdentifier(trade.getBookingIdentifier());
		displayVO.setCountryPartyIdentifier(trade.getCountryPartyIdentifier());
		displayVO.setCreatedDate(trade.getCreatedDate());
		displayVO.setMaturityDate(trade.getMaturityDate());
		displayVO.setIsExpired(trade.getIsExpired());
		displayVO.setLastupdateUser(trade.getLastupdateUser());
		displayVO.setLastupdateUser(trade.getLastupdateUser());
		displayVO.setTradeIdentifier(trade.getTradeIdentifier());
		return displayVO;
	}

	/**
	 * 
	 * @param eventMessage
	 * @return
	 */
	private TradeMessageVO getPayload(String eventMessage) {
		TradeMessageVO payload = null;
		try {
			payload = objectMapper.readValue(eventMessage, TradeMessageVO.class);
		} catch (JsonProcessingException e) {
			LOGGER.error(e.getMessage());
		}
		return payload;
	}
}
