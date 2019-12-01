package com.test.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	@Autowired
	private CurrencyExchangeProxyService proxy;
	@GetMapping("/currency-converter/from/{from}/to/{to}/qty/{qty}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal qty) {
		// Feign - problem -1
		Map<String, String> uriVariable = new HashMap<String, String>();
		uriVariable.put("from", from);
		uriVariable.put("to", to);
		ResponseEntity<CurrencyConversionBean> response = new RestTemplate().getForEntity(
				"http://localhost:8001/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriVariable);
		CurrencyConversionBean conversionBean = response.getBody();
		return new CurrencyConversionBean(conversionBean.getId(), from, to, conversionBean.getConversionFactor(), qty,
				qty.multiply(conversionBean.getConversionFactor()), conversionBean.getPort());

	}
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/qty/{qty}")
	public CurrencyConversionBean convertCurrencyFein(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal qty) {
		
		CurrencyConversionBean conversionBean = proxy.getExchangeVale(from, to);
		return new CurrencyConversionBean(conversionBean.getId(), from, to, conversionBean.getConversionFactor(), qty,
				qty.multiply(conversionBean.getConversionFactor()), conversionBean.getPort());

	}

}
