package com.test.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment env;
	@Autowired
	private ExchangeValueRepository repo;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeVale getExchangeVale(@PathVariable String from, @PathVariable String to) {
		//ExchangeVale exchangeVale = new ExchangeVale(1L, from, to, BigDecimal.valueOf(65));
		
		ExchangeVale exchangeVale = repo.findByFromAndTo(from, to);
		String property = env.getProperty("local.server.port");
		exchangeVale.setPort(Integer.parseInt(property));
		return exchangeVale;

	}
}
