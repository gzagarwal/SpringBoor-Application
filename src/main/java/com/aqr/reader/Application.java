package com.aqr.reader;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.aqr.reader.Message.OrderType;
import com.aqr.reader.Message.Type;

import aqr.entity.controller.Producer;
import aqr.loadcsv.SimpleFileReader;
import aqr.loadcsv.SimpleRecord;

@SpringBootApplication
@ComponentScan({ "aqr.entity.controller", "com.aqr.reader","aqr.entities" })
@EnableJms
public class Application implements ApplicationRunner {

	private static final String SYMBOL = "SYMBOL";
	private static final String BEST_OFFER_SIZE = "BEST_OFFER_SIZE";
	private static final String BEST_OFFER_PRICE = "BEST_OFFER_PRICE";
	private static final String BEST_BID_SIZE = "BEST_BID_SIZE";
	private static final String BEST_BID_PRICE = "BEST_BID_PRICE";
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}


	@Bean
	public CommandLineRunner saveSODData(Repository repository) {

		return args -> {
			SimpleFileReader reader = new SimpleFileReader();
			reader.setBeanIoFileName("topOfTheDayBeanIO.xml");
			
			reader.setFilePath("/assignment1.csv");
			reader.open();
			List<SimpleRecord> record = reader.load();

			for (SimpleRecord rec : record) {
				repository.save(createTop(rec));
			}

			for (TopOfTheDay day : repository.findAll()) {
				System.out.println(day);
			}
			
			
			
		};

	}

	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.BYTES);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	@Autowired
	private Producer producer;

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {

		
			
		
		
	}

	private TopOfTheDay createTop(SimpleRecord rec) {
		final TopOfTheDay top = new TopOfTheDay();
		top.setBest_bid_Price((Double.valueOf((String) rec.get(BEST_BID_PRICE))));
		top.setBest_bid_size(Integer.valueOf((String) rec.get(BEST_BID_SIZE)));
		top.setBest_Offer_Price((Double.valueOf((String) rec.get(BEST_OFFER_PRICE))));
		top.setBest_Offer_size(Integer.valueOf((String) rec.get(BEST_OFFER_SIZE)));
		top.setSymbol((String) rec.get(SYMBOL));
		return top;
	}

}
