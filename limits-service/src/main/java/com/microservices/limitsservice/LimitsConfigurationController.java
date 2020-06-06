package com.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.limitsservice.bean.LimitConfiguration;

@RestController
public class LimitsConfigurationController {
	
	@Autowired
	Configuration configuration;
	
	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitesFromConfigurations(){
		
		return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
	}

	@GetMapping("/fault-tolerance")
	@HystrixCommand(fallbackMethod="fallBackRetrieveConfiguration")
    public LimitConfiguration retrieveConfiguration(){
		
		throw new RuntimeException("Not Available");
	}
	
	
    public LimitConfiguration fallBackRetrieveConfiguration(){
		
		return new LimitConfiguration(9,999);
	}
	
}
