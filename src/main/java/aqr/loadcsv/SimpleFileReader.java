package aqr.loadcsv;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SimpleFileReader extends BeanFileReader {

	String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void open() {
		
			setReader(new InputStreamReader(this.getClass().getResourceAsStream(getFilePath())));
		
		super.open();
	}

	public List<SimpleRecord> load() {

		return next();
	}

	public void close() throws IOException {
		super.close();

	}

}
