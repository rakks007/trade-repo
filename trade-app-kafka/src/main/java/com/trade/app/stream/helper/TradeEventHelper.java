package com.trade.app.stream.helper;

import java.io.Serializable;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.app.vo.TradeMessageVO;


@Component
public class TradeEventHelper {

	private ObjectMapper objectMapper;

	public static final Logger LOGGER = LoggerFactory.getLogger(TradeEventHelper.class);
	
	public static final String EVENT_TYPE = "EVENT_TYPE";

	@Autowired
	public TradeEventHelper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;

	}
	
	public TradeMessageVO getPayload(String eventMessage) {
		TradeMessageVO payload = null;
		try {
			payload = objectMapper.readValue(eventMessage,TradeMessageVO.class);
		} catch (JsonProcessingException e) {
			LOGGER.error(e.getMessage());
		}
		return payload;
	}
	

	public Optional<Message> getMessage(Serializable checksheetRespnse) {
		String eventMessage = null;
		try {
			eventMessage = objectMapper.writeValueAsString(checksheetRespnse);
		} catch (JsonProcessingException e) {
			LOGGER.error(e.getMessage());
			return Optional.empty();
		}
		return buildMessage(eventMessage);
	}

	public Optional<Message> buildMessage(String eventMessage) {
		return Optional.ofNullable(MessageBuilder.withPayload(eventMessage).setHeader("USER_ID", "TRADE")
				.setHeader(EVENT_TYPE, "tradeEventPush").build());
	}

}
