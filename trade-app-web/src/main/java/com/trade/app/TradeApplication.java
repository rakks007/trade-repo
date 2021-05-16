package com.trade.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.trade.*")
public class TradeApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }
}