package com.ss.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.CurrencyExchangeProxy;
import com.ss.dto.CurrencyConversion;

@RestController
@RequestMapping(value = "currency-conversion")
public class CurrencyConversionController {

	@Autowired
	CurrencyExchangeProxy currencyExchangeProxy;

	@GetMapping(value = "/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion conversion(@PathVariable String from, @PathVariable String to,
			@PathVariable Integer quantity) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		CurrencyConversion currencyConversion = new RestTemplate()
				.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class,
						uriVariables)
				.getBody();
		currencyConversion.setId(1000L);
		currencyConversion.setQuantity(quantity);
		currencyConversion.setTotalCalculatedAmount(
				currencyConversion.getConversionMultiple().multiply(BigDecimal.valueOf(quantity)));
		return currencyConversion;
	}

	@GetMapping(value = "/feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion conversionUsingFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable Integer quantity) {
		CurrencyConversion currencyConversion = currencyExchangeProxy.exchange(from, to);
		currencyConversion.setId(1000L);
		currencyConversion.setQuantity(quantity);
		currencyConversion.setTotalCalculatedAmount(
				currencyConversion.getConversionMultiple().multiply(BigDecimal.valueOf(quantity)));
		return currencyConversion;
	}
}