package com.aqr.reader;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Top_Day")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TopOfTheDay {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false)
	private String symbol;

	private Double best_bid_Price;

	private Integer best_bid_size;

	private Double best_Offer_Price;

	private Integer best_Offer_size;

	private String orderType;

	private String orderId;

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getBest_bid_Price() {
		return best_bid_Price;
	}

	public void setBest_bid_Price(Double best_bid_Price) {
		this.best_bid_Price = best_bid_Price;
	}

	public Integer getBest_bid_size() {
		return best_bid_size;
	}

	public void setBest_bid_size(Integer best_bid_size) {
		this.best_bid_size = best_bid_size;
	}

	public Double getBest_Offer_Price() {
		return best_Offer_Price;
	}

	public void setBest_Offer_Price(Double best_Offer_Price) {
		this.best_Offer_Price = best_Offer_Price;
	}

	public Integer getBest_Offer_size() {
		return best_Offer_size;
	}

	public void setBest_Offer_size(Integer best_Offer_size) {
		this.best_Offer_size = best_Offer_size;
	}

}
