package com.balvin.stock.dbservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balvin.stock.dbservice.model.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Integer>{

	List<Quote> findByUserName(String userName);
	
	
}
