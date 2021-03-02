package com.ss;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ss.dto.CurrencyConversion;


@FeignClient(name = "currency-exchange", url = "localhost:8000")
public interface CurrencyExchangeProxy {

	@GetMapping(value = "currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion exchange(@PathVariable String from, @PathVariable String to);
}
