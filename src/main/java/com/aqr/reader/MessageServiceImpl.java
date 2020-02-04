package com.aqr.reader;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import aqr.model.DataMessage;

@Component(value = "message")
public class MessageServiceImpl implements MessageService {

	private static final String SELL = "SELL";

	private static final String BUY = "BUY";

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Resource
	private RepostitoryDao repositoryDao;

	
	public void processMessage(Message message) {

		String executionType = message.getExecutionType().name();

		switch (executionType) {

		case "NEW":
			DataMessage dataMessage = new DataMessage();
			if(message.getOrderType().name().equals(BUY)) {
				dataMessage.setBest_bid_Price(message.getPrice());
				dataMessage.setBest_bid_size(message.getSize());
			}else {
				dataMessage.setBest_Offer_Price(message.getPrice());
				dataMessage.setBest_Offer_size(message.getSize());
			}
			dataMessage.setOrderId(message.getOrderId());
			dataMessage.setSymbol(message.getSymbol());
			
			repositoryDao.insert(dataMessage);

			break;
		case "CANCEL":
			if (message.getOrderId() != null) {
				 dataMessage = new DataMessage();
				 dataMessage.setOrderId(message.getOrderId());
				repositoryDao.delete(dataMessage);
			} else {
				LOGGER.error("TO Cancel the record OrderId cannot be null");
			}
			break;
		case "MODIFY":
			DataMessage dataM = new DataMessage();
			if(message.getOrderType().name().equals(BUY) && message.getOrderId() != null && message.getSize()!= null) {
				dataM.setBest_bid_Price(message.getPrice());
				dataM.setBest_bid_size(message.getSize());
				
			} else if (message.getOrderType().name().equals(SELL) && message.getOrderId() != null && message.getSize()!= null){
				dataM.setBest_Offer_Price(message.getPrice());
				dataM.setBest_Offer_size(message.getSize());
			}else {
				LOGGER.error("TO Modify the record OrderID and quantity cannot be null");
			}
			dataM.setOrderId(message.getOrderId());
			dataM.setSymbol(message.getSymbol());
			repositoryDao.update(dataM);
			break;
		default:
			LOGGER.error("Message should contain the execution Type");

		}

	}

}
