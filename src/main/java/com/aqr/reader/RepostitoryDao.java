package com.aqr.reader;

import java.util.List;

import aqr.model.DataMessage;

public interface RepostitoryDao {
	
	
	public void insert(DataMessage message);
	
	public void delete(DataMessage message);
	
	public void update(DataMessage message);
	
	public List<TopOfTheDay> fetchRecords(String symbol);
	

}
