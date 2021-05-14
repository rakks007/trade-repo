package com.trade.app.stream;

import java.io.Serializable;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.trade.app.controller.TradeMessageController;
import com.trade.app.stream.helper.TradeEventHelper;
import com.trade.app.vo.TradeMessageVO;

@Component("tradeEvent")
@EnableBinding(TradeEventListener.TradeEventsSink.class)
public class TradeEventListener {

	static final Logger logger = LoggerFactory.getLogger(TradeEventListener.class);

	private final TradeMessageController controller;

	private TradeEventsSink tradeEventsSink;

	@Autowired
	private TradeEventHelper tradeEventHelper;

	@Autowired
	public TradeEventListener(TradeMessageController controller, TradeEventsSink tradeEventsSink) {
		this.controller = controller;
		this.tradeEventsSink = tradeEventsSink;
	}

	@StreamListener(TradeEventsSink.INPUT)
	public void handleTradeEvent(@Payload String payload) {
		if (payload != null) {
			TradeMessageVO tradeMessageVO= tradeEventHelper.getPayload(payload);
			controller.saveTrade(tradeMessageVO);
		}
	}

	/**
	 * 
	 * @param response
	 */
	public void publishTrade(TradeMessageVO tradeMessageVO) {
		if (tradeMessageVO != null) {
			Optional<Message> message = tradeEventHelper.getMessage((Serializable) tradeMessageVO);
			message.ifPresent(m -> tradeEventsSink.tradeOutChannel().send(m));
		}
	}

	public interface TradeEventsSink {
		String INPUT = "TRADE_IN";
		String OUTPUT = "TRADE_OUT";

		@Input(INPUT)
		SubscribableChannel tradeInChannel();

		@Output(OUTPUT)
		MessageChannel tradeOutChannel();
	}

}