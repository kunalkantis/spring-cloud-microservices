package com.test.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.microservices.limitsservice.bean.LimitConfig;

@RestController
public class LimitsConfigurationController {
	
	@Autowired
	private Configuration config;  
	
	@GetMapping("/limits")
	public LimitConfig getLimitConfig() {
		return new LimitConfig(config.getMin(),config.getMax());
	}

}
