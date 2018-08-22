package com.balvin.stock.stockservice.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yahoofinance.quotes.stock.StockQuote;

@RestController
@RequestMapping("/rest/stock")
public class StockResource {
	@GetMapping("{username}")
	public List<StockQuote> getStock(@PathVariable("username") final String userName){
		return null;
	}
}
