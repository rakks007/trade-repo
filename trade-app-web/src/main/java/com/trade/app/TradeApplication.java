package com.trade.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.trade.*")
public class TradeApplication {

	public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }
}