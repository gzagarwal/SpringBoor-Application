package com.aqr.reader;

import java.util.List;

import aqr.model.DataMessage;

public class ExchangeResponse<T> {

	private String type;
	private List<TopOfTheDay> value;
	List<DataMessage> dataNessage;
	public List<DataMessage> getDataNessage() {
		return dataNessage;
	}
	public void setDataNessage(List<DataMessage> dataNessage) {
		this.dataNessage = dataNessage;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<TopOfTheDay> getValue() {
		return value;
	}
	public void setValue(List<TopOfTheDay> value) {
		this.value = value;
	}
	
	
	}
