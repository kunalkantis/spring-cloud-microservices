package com.test.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeVale, Long>{
	
	public ExchangeVale findByFromAndTo(String from,String to); 

}
