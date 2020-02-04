package aqr.entity.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aqr.reader.ExchangeResponse;
import com.aqr.reader.FetchService;
import com.aqr.reader.Repository;
import com.aqr.reader.TopOfTheDay;

import aqr.model.DataMessage;

@RestController
@RequestMapping("/api23")
public class ExchangeController {
	
	private static final String SUCESS = "SUCESS";
	private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeController.class);
	@Autowired
	FetchService service;
	@Autowired
	Repository repository;
	public ExchangeController() {
		
	}
	
	public ExchangeController(Repository respository) {
		this.repository=respository;
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public ExchangeResponse<TopOfTheDay> retrieveRecords() {
		ExchangeResponse<TopOfTheDay> response = new ExchangeResponse<>();
		@SuppressWarnings("unchecked")
		List<TopOfTheDay> result= (List<TopOfTheDay>) StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
		response.setType(SUCESS);
		response.setValue(result);
		return response;
		
	}
	
	@RequestMapping(value="/test/top5Records", method=RequestMethod.GET)
	public ExchangeResponse fetchTop5Records(@RequestParam("symbol") String symbol) {
		List<DataMessage> dataNessage =service.fetchTopRecords(symbol);
		ExchangeResponse response = new ExchangeResponse<>();
		response.setType(SUCESS);
		response.setDataNessage(dataNessage);
		return response;
		
	}
	
	

}
