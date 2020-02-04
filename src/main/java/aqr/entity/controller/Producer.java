package aqr.entity.controller;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.aqr.reader.Message;

@RestController
public class Producer {

	@Autowired
	private Queue queue;

	@Autowired
	private JmsTemplate jmsTemplate;

	@GetMapping("publish/{msg}")
	public String publish(@PathVariable("msg") final Message message) {
		
		
		jmsTemplate.convertAndSend(queue,message);
		return "Your message <b>" + message + "</b> published successfully";
	}
}