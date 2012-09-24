package org.arapidis.decorators;

import com.itextpdf.text.DocumentException;

public class DocumentDecorator implements IDocument{

	private IDocument decoratedDocument;
	
	public DocumentDecorator(final IDocument document) {
		this.decoratedDocument = document;
	}
	
	public void read() throws DocumentException {
		getDecoratedDocument().read();		
	}

	public void decorate() {
		getDecoratedDocument().decorate();		
	}

	public void save(String path) throws DocumentException {		
		getDecoratedDocument().save(path);		
	}

	public IDocument getDecoratedDocument() {
		return decoratedDocument;
	}

	public byte[] getContent() {
		return getDecoratedDocument().getContent();
	}

	public void setContent(byte[] bytes) {
		getDecoratedDocument().setContent(bytes);		
	}

}
