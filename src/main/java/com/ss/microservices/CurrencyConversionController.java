package com.ss.microservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.microservices.dto.CurrencyConversion;

@RestController
@RequestMapping(value = "currency-conversion")
public class CurrencyConversionController {

	@GetMapping(value = "/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion conversion(@PathVariable String from, @PathVariable String to, @PathVariable Integer quantity) {
		return new CurrencyConversion();
	}
}
