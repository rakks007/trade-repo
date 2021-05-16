package com.trade.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trade.app.controller.TradeMessageController;
import com.trade.app.vo.TradeListVO;
import com.trade.app.vo.TradeMessageVO;

@RestController
@RequestMapping(path="/trade")
public class TradeRestController {
	@Autowired
	private TradeMessageController controller;

	@PostMapping(path= "/save", consumes = "application/json", produces = "application/json")
	public void saveTrade(@RequestBody TradeMessageVO tradeMessageVO) {
		controller.publishTradeMessage(tradeMessageVO);
	}
	
	@GetMapping(path= "/list",produces = "application/json")
	public TradeListVO listTrades() {		
		return controller.findAllTrades();
	}
}
