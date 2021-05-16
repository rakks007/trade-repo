package com.trade.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.trade.app.entity.TradeMessage;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TradeMessageRepository extends CrudRepository<TradeMessage, Integer> {
	@Query("select t from TradeMessage t where t.tradeMessageStatus = ?1")
	public List<TradeMessage> findByStatus(String status);
}