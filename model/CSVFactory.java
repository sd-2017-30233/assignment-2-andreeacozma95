package model;

public class CSVFactory implements Factory{

	@Override
	public Format getFormat() {
		return new CSVFormat();
	}

}
