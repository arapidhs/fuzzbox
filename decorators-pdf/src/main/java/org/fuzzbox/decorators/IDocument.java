package org.fuzzbox.decorators;

import com.itextpdf.text.DocumentException;

public interface IDocument {

	void read() throws DocumentException;		
	
	void save(String path) throws DocumentException;
	
	byte[] getContent();
	
	void setContent(byte[] bytes);
	
	void decorate();
}
