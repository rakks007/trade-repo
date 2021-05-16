package com.trade.app;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.trade.app.entity.TradeMessage;
import com.trade.app.repo.TradeMessageRepository;

/**
 * Unit test for Trade Repository App.
 */
@DataJpaTest
@AutoConfigureTestDatabase()
@DirtiesContext
@ContextConfiguration(classes=TradeMessageRepository.class, loader=AnnotationConfigContextLoader.class)
@EnableJpaRepositories(basePackages = {"com.trade.*"})
@EntityScan("com.trade.app.entity")
public class TradeMessageRepositoryTests {
 
    @Autowired
    private TradeMessageRepository repo; 
    
    @Test
    @Rollback(false)
    public void testCreateTradeMessage() {
    	TradeMessage tradeMessage=new TradeMessage();
    	tradeMessage.setMessage("{Test}");
    	tradeMessage.setTradeId("T1");
    	TradeMessage tradeId = repo.save(tradeMessage);         
        assertTrue(tradeId.getTradeId().equals("T1"));
    }
    
    @Test
    @Rollback(false)
    public void testFindAll() {
    	TradeMessage tradeMessage=new TradeMessage();
    	tradeMessage.setMessage("{Test}");
    	tradeMessage.setTradeId("T1");
    	repo.save(tradeMessage);
    	repo.save(tradeMessage);
    	List<TradeMessage> tlist=(List<TradeMessage>) repo.findAll();        
        assertTrue(tlist.size()> 0);
    }
    

    @Test
    @Rollback(false)
    public void testFindByTradeId() {
    	TradeMessage tradeMessage=new TradeMessage();
    	tradeMessage.setMessage("{Test}");
    	tradeMessage.setTradeId("T5");
    	tradeMessage.setTradeMessageStatus("S");
    	repo.save(tradeMessage);
    	List<TradeMessage> tradeId = repo.findByStatus("S");        
        assertTrue(tradeId.get(0).getTradeId().equals("T5"));
    }
}
