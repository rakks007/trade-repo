package com.trade.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "trade_msg_store")
@Getter
@Setter
@NoArgsConstructor
@ToString
@SequenceGenerator(name="trade_msg_sequence", initialValue=1, allocationSize=10000)

public class TradeMessage {
	
	@Column(name="message_id")
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="trade_sequence")
	private long messageId;	
	
	@Column(name="org_message",columnDefinition="TEXT")
	private String message;
	
	@Column(name="trade_id")
	private String tradeId;
	
	@Column(name="message_status", length=1, columnDefinition="TINYTEXT")
	private String tradeMessageStatus;
	
	@Column(name="message_errors",columnDefinition="TEXT")
	private String tradeMessageError;
	
	
	
}
