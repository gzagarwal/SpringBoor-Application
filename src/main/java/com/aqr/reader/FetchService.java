package com.aqr.reader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aqr.model.DataMessage;

@Service
public class FetchService {

	@Autowired
	RepostitoryDao dao;

	public List<DataMessage> fetchTopRecords(String symbol) {
		List<DataMessage> message = new ArrayList<DataMessage>();
		List<TopOfTheDay> days = dao.fetchRecords(symbol);
		if (days == null || days.size() < 0) {
			return new ArrayList<>();
		}
		Map<Double, Integer> bestBid = new TreeMap<>(Collections.reverseOrder());
		Map<Double, Integer> bestOfferPrice = new TreeMap<>();

		for (TopOfTheDay day : days) {
			if (day.getBest_bid_Price()!=null && bestBid.containsKey(day.getBest_bid_Price())) {
				Integer size = bestBid.get(day.getBest_bid_Price());
				bestBid.put(day.getBest_bid_Price(), day.getBest_bid_size() + size);
			} else {
				if (day.getBest_bid_Price() != null) {
					bestBid.put(day.getBest_bid_Price(), day.getBest_bid_size());
				}
			}

			if (day.getBest_Offer_Price()!=null && bestOfferPrice.containsKey(day.getBest_Offer_Price())) {
				Integer size = bestOfferPrice.get(day.getBest_Offer_Price());
				bestOfferPrice.put(day.getBest_Offer_Price(), day.getBest_Offer_size() + size);
			} else {
				if (day.getBest_Offer_Price() != null) {
					bestOfferPrice.put(day.getBest_Offer_Price(), day.getBest_Offer_size());
				}
			}
		}

		List<Double> bidKeySet = new ArrayList<>(bestBid.keySet());
		List<Double> bidOfferPrice = new ArrayList<>(bestOfferPrice.keySet());

		int index = 0;
		if (bidKeySet.size() > bidOfferPrice.size()) {
			for (Double d : bidKeySet) {

				DataMessage dataMessage = new DataMessage();
				dataMessage.setBest_bid_Price(d);
				dataMessage.setBest_bid_size(bestBid.get(d));
				if (bidOfferPrice.size() > index) {
					dataMessage.setBest_Offer_Price(bidOfferPrice.get(index));
					if(bidOfferPrice.get(index)!=null) {
					dataMessage.setBest_Offer_size(bestOfferPrice.get(bidOfferPrice.get(index)));
				}}
				index++;
				dataMessage.setSymbol(symbol);
				message.add(dataMessage);
				if(index>4) {
					break;
				}
			}
		} else {
			for (Double d : bidOfferPrice) {

				DataMessage dataMessage = new DataMessage();
				dataMessage.setBest_Offer_Price(d);
				dataMessage.setBest_Offer_size(bestOfferPrice.get(d));
				if (bidOfferPrice.size() > index) {
					dataMessage.setBest_bid_Price(bidKeySet.get(index));
					if(bidKeySet.get(index)!=null) {
					dataMessage.setBest_bid_size(bestBid.get(bidKeySet.get(index)));
				}}
				index++;
				dataMessage.setSymbol(symbol);
				message.add(dataMessage);
				if(index>4) {
					break;
				}
			}
		}
		return message;
	}

}
