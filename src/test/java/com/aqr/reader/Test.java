package com.aqr.reader;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.jms.Queue;

import org.junit.Assert;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.aqr.reader.Message.OrderType;
import com.aqr.reader.Message.Type;

import aqr.model.DataMessage;

public class Test extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Autowired
	private Queue queue;

	@Autowired
	private JmsTemplate jmsTemplate;

	@GetMapping("publish/{msg}")
	public String publish(@PathVariable("msg") final Message message) {

		jmsTemplate.convertAndSend(queue, message);
		return "Your message <b>" + message + "</b> published successfully";
	}

	@org.junit.Test
	public void aqrUpdateTradesInfo() throws Exception {
		String uri = "/api23/test";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		ExchangeResponse exchangeResponse = super.mapFromJson(content, ExchangeResponse.class);
		List<TopOfTheDay> days = exchangeResponse.getValue();
		assertEquals(15, days.size());
		
		
		Message order;
		{
			order = new Message("123", "ASP", Type.NEW, OrderType.BUY, 123.00, 24);
			publish(order);
			Thread.sleep(1000);

			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();

			content = mvcResult.getResponse().getContentAsString();
			exchangeResponse = super.mapFromJson(content, ExchangeResponse.class);
			days = exchangeResponse.getValue();
			assertEquals(16, days.size());
		}
		{
			order = new Message("123", "ASP", Type.MODIFY, OrderType.BUY, 123.00, 243);
			publish(order);
			Thread.sleep(1000);

			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();
			// assertEquals(16, days.size());

			content = mvcResult.getResponse().getContentAsString();
			exchangeResponse = super.mapFromJson(content, ExchangeResponse.class);
			days = exchangeResponse.getValue();
			assertEquals(16, days.size());
		}
		{
			order = new Message("123", "IBM", Type.CANCEL, OrderType.BUY, 123.00, 24);
			publish(order);
			Thread.sleep(1000);
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();
			// assertEquals(16, days.size());

			content = mvcResult.getResponse().getContentAsString();
			exchangeResponse = super.mapFromJson(content, ExchangeResponse.class);
			days = exchangeResponse.getValue();
			assertEquals(15, days.size());
		}

		{
			order = new Message("12445", "IBMS", Type.NEW, OrderType.BUY, 12233.00, 24);
			publish(order);

			Thread.sleep(1000);
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();
			// assertEquals(16, days.size());

			content = mvcResult.getResponse().getContentAsString();
			exchangeResponse = super.mapFromJson(content, ExchangeResponse.class);
			days = exchangeResponse.getValue();
			assertEquals(16, days.size());
		}

		{
			order = new Message("14562", "ASSP", Type.NEW, OrderType.SELL, 12.00, 200);
			publish(order);

			Thread.sleep(1000);
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();
			// assertEquals(16, days.size());

			content = mvcResult.getResponse().getContentAsString();
			exchangeResponse = super.mapFromJson(content, ExchangeResponse.class);
			days = exchangeResponse.getValue();
			assertEquals(17, days.size());

		}
		{
			order = new Message("14562", "IBM", Type.MODIFY, OrderType.SELL, 123.00, 243);
			publish(order);
			Thread.sleep(1000);
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();
			// assertEquals(16, days.size());

			content = mvcResult.getResponse().getContentAsString();
			exchangeResponse = super.mapFromJson(content, ExchangeResponse.class);
			days = exchangeResponse.getValue();
			assertEquals(17, days.size());

		}

		{
			order = new Message("14562", "IBM", Type.CANCEL, OrderType.BUY, 123.00, 24);
			publish(order);
			Thread.sleep(1000);
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();
			// assertEquals(16, days.size());

			content = mvcResult.getResponse().getContentAsString();
			exchangeResponse = super.mapFromJson(content, ExchangeResponse.class);
			days = exchangeResponse.getValue();
			assertEquals(16, days.size());

		}

		{
			order = new Message("12445", "IBM", Type.NEW, OrderType.BUY, 123.00, 24);
			publish(order);
			System.out.println(days);
			Thread.sleep(1000);
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();
			// assertEquals(16, days.size());

			content = mvcResult.getResponse().getContentAsString();
			exchangeResponse = super.mapFromJson(content, ExchangeResponse.class);
			days = exchangeResponse.getValue();
			assertEquals(17, days.size());

		}
		{
			order = new Message("900", "IBM", Type.NEW, OrderType.SELL, 500.00, 249);
			publish(order);
			System.out.println(days);
			Thread.sleep(1000);
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();
			// assertEquals(16, days.size());

			content = mvcResult.getResponse().getContentAsString();
			exchangeResponse = super.mapFromJson(content, ExchangeResponse.class);
			days = exchangeResponse.getValue();
			assertEquals(18, days.size());

		}
		{
			order = new Message("901", "IBM", Type.NEW, OrderType.SELL, 900.00, 428);
			publish(order);
			System.out.println(days);
			Thread.sleep(1000);
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();
			// assertEquals(16, days.size());

			content = mvcResult.getResponse().getContentAsString();
			exchangeResponse = super.mapFromJson(content, ExchangeResponse.class);
			days = exchangeResponse.getValue();
			assertEquals(19, days.size());

		}
		{
			order = new Message("902", "IBM", Type.NEW, OrderType.BUY, 782.00, 789);
			publish(order);
			System.out.println(days);
			Thread.sleep(1000);
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();
			// assertEquals(16, days.size());

			content = mvcResult.getResponse().getContentAsString();
			exchangeResponse = super.mapFromJson(content, ExchangeResponse.class);
			days = exchangeResponse.getValue();
			assertEquals(20, days.size());

		}
		{
			order = new Message("903", "IBM", Type.NEW, OrderType.SELL, 930.00, 12000);
			publish(order);
			
		
			order = new Message("904", "IBM", Type.NEW, OrderType.SELL, 1900.00, 45000);
			publish(order);
			
			order = new Message("905", "IBM", Type.NEW, OrderType.BUY, 2900.00, 1100);
			publish(order);
			
			order = new Message("906", "IBM", Type.NEW, OrderType.BUY, 2910.00, 49000);
			publish(order);
			order = new Message("907", "IBM", Type.NEW, OrderType.SELL, 29.00, 10000);
			publish(order);
			Thread.sleep(1000);
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();
			// assertEquals(16, days.size());

			content = mvcResult.getResponse().getContentAsString();
			exchangeResponse = super.mapFromJson(content, ExchangeResponse.class);
			days = exchangeResponse.getValue();
			assertEquals(25, days.size());

		}
	}
	
	/*
	 * It will retrieve all the 5 records .
	 */
	
	@org.junit.Test
	public void getTrades() throws Exception{
		String uri = "/api23/test/top5Records?symbol=IBM";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		ExchangeResponse exchangeResponse = super.mapFromJson(content, ExchangeResponse.class);
		
		assertEquals(5, exchangeResponse.getDataNessage().size());
		DataMessage dataMessage = (DataMessage) exchangeResponse.getDataNessage().get(0);
		DataMessage dataMessage1 = (DataMessage) exchangeResponse.getDataNessage().get(1);
		DataMessage dataMessage2 = (DataMessage) exchangeResponse.getDataNessage().get(2);
		DataMessage dataMessage3 = (DataMessage) exchangeResponse.getDataNessage().get(3);
		DataMessage dataMessage4 = (DataMessage) exchangeResponse.getDataNessage().get(4);
		
		/**
		 * This is the sorted Record*/
		 */
		List<Double> bestBidPrice  = new ArrayList<>();
		bestBidPrice.add(dataMessage.getBest_bid_Price());
		bestBidPrice.add(dataMessage1.getBest_bid_Price());
		bestBidPrice.add(dataMessage2.getBest_bid_Price());
		bestBidPrice.add(dataMessage3.getBest_bid_Price());
		bestBidPrice.add(dataMessage4.getBest_bid_Price());
		Object[] a = bestBidPrice.toArray();
		
		/**
		 * This is sorted Intentionally.If some issue is there in sorting , ASsert will fail.
		 */
		CopyOnWriteArrayList<Double> writeArray = new CopyOnWriteArrayList<>();
		writeArray.add(dataMessage.getBest_bid_Price());
		writeArray.add(dataMessage1.getBest_bid_Price());
		writeArray.add(dataMessage2.getBest_bid_Price());
		writeArray.add(dataMessage3.getBest_bid_Price());
		writeArray.add(dataMessage4.getBest_bid_Price());
		Object[] sortedArray = writeArray.toArray();
		Assert.assertArrayEquals(sortedArray,a);
		
		
		
		
	}

}
