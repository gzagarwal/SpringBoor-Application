package com.aqr.reader;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import aqr.model.DataMessage;

@Component
public class RepositoryDaoImpl implements RepostitoryDao {

	@Autowired
	Repository repository;

	@Override
	@Transactional
	public void insert(DataMessage message) {
		TopOfTheDay day = new TopOfTheDay();

		day.setOrderId(message.getOrderId());
		day.setOrderType((message.getOrderType()));
		day.setBest_bid_Price(message.getBest_bid_Price());
		day.setBest_Offer_Price(message.getBest_Offer_Price());
		day.setBest_bid_size(message.getBest_bid_size());
		day.setBest_Offer_size(message.getBest_Offer_size());
		day.setSymbol(message.getSymbol());
		repository.save(day);

	}

	@Override
	@Transactional
	public void delete(DataMessage message) {
		Query query =entityManager.createNativeQuery("Select Id from TOP_DAY where order_id =:orderId");
		query.setParameter("orderId", message.getOrderId());
		List<Object> list = query.getResultList();
		TopOfTheDay day = new TopOfTheDay();
		day.setOrderId(message.getOrderId());
		day.setId((Integer)list.get(0));
		repository.delete(day);

	}

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void update(DataMessage message) {
		TopOfTheDay day = new TopOfTheDay();
		day.setOrderId(message.getOrderId());
		day.setSymbol(message.getSymbol());
		Query query =entityManager.createNativeQuery("Select Id from TOP_DAY where order_id =:orderId");
		query.setParameter("orderId", message.getOrderId());
		List<Object> list = query.getResultList();
		if (message.getBest_bid_Price() != null) {
			day.setBest_bid_size(message.getBest_bid_size());
			day.setId((Integer)list.get(0));
			
			repository.save(day);
		} else {
			day.setBest_Offer_size(message.getBest_Offer_size());
			day.setId((Integer)list.get(0));
			repository.save(day);

		}

	}

	@Override
	public List<TopOfTheDay> fetchRecords(String symbol) {

		Query query = entityManager.createNativeQuery(
				"SELECT best_bid_Price,best_bid_size,best_Offer_Price,best_Offer_size,symbol FROM  TOP_DAY where symbol =:symbol");
		query.setParameter("symbol", symbol);
		List<TopOfTheDay> topOfDay = new ArrayList<TopOfTheDay>();
		List<Object> list = query.getResultList();
		for (int i = 0; i < list.size(); i++) {
			TopOfTheDay d = new TopOfTheDay();
			Object[] obj = (Object[]) list.get(i);
			d.setBest_bid_Price((Double) obj[0]);
			d.setBest_bid_size((Integer) obj[1]);

			d.setBest_Offer_Price((Double) obj[2]);
			d.setBest_Offer_size((Integer) obj[3]);
			d.setSymbol((String) obj[4]);
			topOfDay.add(d);
		}

		return topOfDay;
	}

}
