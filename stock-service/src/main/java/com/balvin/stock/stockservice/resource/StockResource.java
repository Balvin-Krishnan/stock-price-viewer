package com.balvin.stock.stockservice.resource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
@RequestMapping("/rest/stock")
public class StockResource {
	
	@Autowired
	RestTemplate restTemplate;
	
	private YahooFinance yahoofinance;
	public StockResource() {
		this.yahoofinance = new YahooFinance();
	}
	
	@GetMapping("{username}")
	public List<Stock> getStock(@PathVariable("username") final String userName){
		//List<String> quotes = restTemplate.getForObject("http://localhost:8300/restdb/"+userName, List.class);
		ResponseEntity<List<String>> quoteResponse =  restTemplate
				.exchange("http://localhost:8300/rest/db/"+userName, HttpMethod.GET, 
						null, new ParameterizedTypeReference<List<String>>() {} );
		
		List<String> quotes = quoteResponse.getBody();
		return quotes.stream()
			.map(quote -> {
				try {
					return YahooFinance.get(quote);
				} catch (IOException e) {
					e.printStackTrace();
					return new Stock(quote);
				}
				
				
			})
			.collect(Collectors.toList());
	}
}
