package com.trade.app;

import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.PropertyValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.trade.app.entity.Trade;
import com.trade.app.repo.TradeRepository;

/**
 * Unit test for Trade Repository App.
 */

@DataJpaTest
@AutoConfigureTestDatabase()
@DirtiesContext
@ContextConfiguration(classes=TradeRepository.class, loader=AnnotationConfigContextLoader.class)
@EnableJpaRepositories(basePackages = {"com.trade.*"})
@EntityScan("com.trade.app.entity")
public class TradeRepositoryTests {
 
    @Autowired
    private TradeRepository repo; 
    
    private Trade populateTrade() {
    	Trade t = new Trade();
    	String str = "2021-05-16";
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	LocalDate dateTime = LocalDate.parse(str, formatter);
		t.setCountryPartyIdentifier("A");
		t.setLastupdateUser("test");
		t.setCreatedDate(dateTime);
		t.setTradeIdentifier("T1");
		t.setBookingIdentifier("B1");
		t.setIsExpired("Y");
		t.setMaturityDate(dateTime.plusDays(5));
		t.setLastupdateUser("T");
		t.setLastupdateTime(Timestamp.from(ZonedDateTime.now().toInstant()));
		return t;
    }
    
    @Test
    //@Rollback(false)
    public void testCreateTrade() {
    	Trade t =populateTrade();
    	t.setIsExpired(null);
    	Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
    	    repo.save(t);
    	  });
    	 
    }
    
    @Test
    @Rollback(false)
    public void testCreateTradeSuccess() {
    	repo.save(populateTrade()); 
    	
    }
    
    @Test
    @Rollback(false)
    public void testFindAll() {    	
    	repo.save(populateTrade());
    	repo.save(populateTrade());
    	List<Trade> tlist=(List<Trade>) repo.findAll();        
        assertTrue(tlist.size()> 0);
    }
}
