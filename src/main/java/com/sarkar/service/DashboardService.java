package com.sarkar.service;

import org.springframework.stereotype.Service;

import com.sarkar.dto.QuoteApiResponseDTO;

@Service
public interface DashboardService {
	
	public QuoteApiResponseDTO getQuote();

}
