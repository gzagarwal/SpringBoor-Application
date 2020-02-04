package com.aqr.reader;

import java.io.IOException;
import java.util.List;

import aqr.loadcsv.SimpleFileReader;
import aqr.loadcsv.SimpleRecord;

public class ExternalStream {

	SimpleFileReader reader;

	public ExternalStream() {
		// TODO Auto-generated constructor stub
	}

	public void open() {
		reader.open();
	}
	
	public List<SimpleRecord> readData(){
		return reader.next();
	}
	
	public void close()throws IOException {
		reader.close();
	}
}
