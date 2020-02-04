package aqr.loadcsv;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.beanio.BeanReader;
import org.beanio.StreamFactory;

public class BeanFileReader {

	Reader reader;
	BeanReader beanReader;
	String beanIoFileName;
	String beanIoConfigResource;
	String beanIoStreamName;
	SimpleRecord record = null;
	int 		currentlineNUm;
	int skipRow=0;
	public void open() {
		StreamFactory factory = StreamFactory.newInstance();
		factory.loadResource(getBeanIoFileName());
		beanReader=	factory.createReader("TOPOFDAY",getReader());
		/* 		currentlineNUm=0;
		if(skipRow>0) {
			beanReader.skip(skipRow);
			currentlineNUm=skipRow;
		}*/
	}


	public List<SimpleRecord> next(){
		List<SimpleRecord> simpleRecord = new ArrayList<>();
		Object parsedLine=null;
		while((parsedLine=beanReader.read())!=null) {
			Map<String,Object> line = (Map<String, Object>) parsedLine;
			if(parsedLine!=null) {
				record=new SimpleRecord(line);
				simpleRecord.add(record);
				
			}
		}
		
		
		return simpleRecord;
	}

	
	public void close() throws IOException {
		if(reader!=null) {
			try {
				reader.close();
			} catch (IOException e) {
				throw new IOException();
			}
		}
	}
	public Reader getReader() {
		return reader;
	}



	public void setReader(Reader reader) {
		this.reader = reader;
	}



	public BeanReader getBeanReader() {
		return beanReader;
	}



	public void setBeanReader(BeanReader beanReader) {
		this.beanReader = beanReader;
	}



	public String getBeanIoFileName() {
		return beanIoFileName;
	}



	public void setBeanIoFileName(String beanIoFileName) {
		this.beanIoFileName = beanIoFileName;
	}



	public String getBeanIoConfigResource() {
		return beanIoConfigResource;
	}



	public void setBeanIoConfigResource(String beanIoConfigResource) {
		this.beanIoConfigResource = beanIoConfigResource;
	}



	public String getBeanIoStreamName() {
		return beanIoStreamName;
	}



	public void setBeanIoStreamName(String beanIoStreamName) {
		this.beanIoStreamName = beanIoStreamName;
	}



	
}
