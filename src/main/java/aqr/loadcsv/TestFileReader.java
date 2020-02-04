package aqr.loadcsv;

import java.util.List;

public class TestFileReader {
	public static void main(String[] args) {
		
		SimpleFileReader reader = new SimpleFileReader();
		reader.setBeanIoFileName("topOfTheDayBeanIO.xml");
		reader.setFilePath("C:\\assignment\\assignment1.csv");
		reader.open();
		List<SimpleRecord> record =  reader.load();
		System.out.println();

	}

}
