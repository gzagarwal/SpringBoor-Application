package com.aqr.reader;

import javax.annotation.Resource;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@Resource(name="message")
	MessageService service;
	
	@JmsListener(destination = "simple-jms-queue")
	  public void receiveMessage(@Payload Message message) {
		service.processMessage(message);
	   
	  }
    
}