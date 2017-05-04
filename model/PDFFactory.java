package model;

public class PDFFactory implements Factory{

	@Override
	public Format getFormat() {
		return new PDFFormat();
	}

}
