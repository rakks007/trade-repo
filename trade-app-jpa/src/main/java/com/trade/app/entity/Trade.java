package com.trade.app.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "trade_store")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Trade {

	@Column(name = "version")
	private Integer version;

	@Column(name = "trade_id")
	private String tradeIdentifier;

	@Column(name = "country_party_id")
	private String countryPartyIdentifier;

	@Column(name = "maturity_date")
	private Date maturityDate;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "lastupdated_user")
	private String lastupdateUser;

	@Column(name = "is_expired")
	private String isExpired;

	@Column(name = "booked_id")
	private String bookingIdentifier;

	@Column(name = "lastupdated_time")
	@Id
	private Timestamp lastupdateTime;
}
