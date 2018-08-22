package com.balvin.stock.dbservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balvin.stock.dbservice.model.Quote;
import com.balvin.stock.dbservice.model.Quotes;
import com.balvin.stock.dbservice.repository.QuoteRepository;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {
	
	
	private QuoteRepository quoteRepository;
	
	public DbServiceResource(QuoteRepository quoteRepository) {
		// TODO Auto-generated constructor stub
		this.quoteRepository = quoteRepository;
	}

	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable("username") final String username){
		
		return getUserName(username);		
	}
	
	private List<String> getUserName(String userName){
		return quoteRepository.findByUserName(userName)
		.stream()
		.map(Quote::getQuote)
		.collect(Collectors.toList());
	}
	
	@PostMapping("/add")
	public List<String> add(@RequestBody final Quotes quotes){
		System.out.println(quotes);
		quotes.getQuotes()
			.stream()
			.map(quote -> new Quote(quotes.getUserName(), quote))
			.forEach(quote -> quoteRepository.save(quote));
		
		return getUserName(quotes.getUserName());
	}
	
	@PostMapping("/delete/{username}")
	public List<String> delete(@PathVariable("username") final String username) {
		
		List<Quote> quotes = quoteRepository.findByUserName(username);
		
		quoteRepository.deleteAll(quotes);
		
		return getUserName(username);
	}
}
