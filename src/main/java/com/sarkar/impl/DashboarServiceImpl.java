package com.sarkar.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sarkar.dto.QuoteApiResponseDTO;
import com.sarkar.service.DashboardService;
@Service
public class DashboarServiceImpl implements DashboardService{
	
	private String quoteApiURl = "https://dummyjson.com/quotes/random";

	@Override
	public QuoteApiResponseDTO getQuote() {
		
		RestTemplate rt = new RestTemplate();
		
		ResponseEntity<QuoteApiResponseDTO> forEntity = rt.getForEntity(quoteApiURl, QuoteApiResponseDTO.class);
		
		QuoteApiResponseDTO body = forEntity.getBody();
		return body;
	}
	
	

}
