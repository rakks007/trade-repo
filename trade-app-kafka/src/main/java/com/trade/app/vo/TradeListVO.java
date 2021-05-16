package com.trade.app.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TradeListVO implements Serializable {
	private List<TradeDisplayVO> trades;
	private List<TradeWithErrorVO> tradeErrors;
}
