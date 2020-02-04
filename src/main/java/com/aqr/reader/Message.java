package com.aqr.reader;

import java.io.Serializable;

public class Message implements Serializable{

	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

enum Type{NEW,CANCEL,MODIFY}
enum OrderType{SELL,BUY}



	

	private String orderId;
	
	private String symbol;
	
	private Type executionType;
	
	private OrderType orderType;
	
	private Double price;
	
	private Integer size;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Message(String orderId, String symbol, Type executionType,OrderType orderType, Double price, Integer size) {
		
		this.orderId = orderId;
		this.symbol = symbol;
		this.executionType = executionType;
		this.price = price;
		this.size = size;
		this.orderType=orderType;
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

	public Type getExecutionType() {
		return executionType;
	}

	public void setExecutionType(Type executionType) {
		this.executionType = executionType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	
	
	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	@Override
	public String toString() {
		
		return getExecutionType()+" "+getOrderId()+" "+ getPrice()+" "+getSymbol()+" " + getSize() +" " +getOrderType();
	}
	
	
	
}
