package com.trade.app.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.trade.app.entity.Trade;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TradeRepository extends CrudRepository<Trade, Integer> {
	@Query("select t from Trade t where t.tradeIdentifier = ?1")
	public Trade findByTradeId(String tradeId);
	
	@Query("select t from Trade t where t.bookingIdentifier = ?1")
	public Trade findByBookingId(String bookingId);

}