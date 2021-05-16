package com.trade.app.entity;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

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
@SequenceGenerator(name="trade_sequence", initialValue=1, allocationSize=10000)
public class Trade {

	@Column(name = "version" ,nullable=false,updatable = false)
	private int version;

	@Column(name = "trade_id", length=20, columnDefinition="TINYTEXT")
	private String tradeIdentifier;

	@Column(name = "country_party_id",nullable=false,length=20, columnDefinition="TINYTEXT")
	private String countryPartyIdentifier;

	@Column(name = "maturity_date",nullable=false)
	private LocalDate maturityDate;

	@Column(name = "created_date",nullable=false)
	private LocalDate createdDate;

	@Column(name = "lastupdated_user",nullable=false, length=20, columnDefinition="TINYTEXT")
	private String lastupdateUser;

	@Column(name = "is_expired", nullable=false, length=1, columnDefinition="TINYTEXT")
	private String isExpired;

	@Column(name = "booked_id",nullable=false, length=20, columnDefinition="TINYTEXT")
	private String bookingIdentifier;

	@Column(name = "lastupdated_time")
	@Version
	private Timestamp lastupdateTime;
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="trade_sequence")
	private long id;
}
