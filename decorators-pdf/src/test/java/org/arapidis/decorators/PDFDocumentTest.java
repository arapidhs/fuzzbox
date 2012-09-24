package org.arapidis.decorators;

import static org.junit.Assert.*;

import org.junit.Test;

import com.itextpdf.text.DocumentException;

public class PDFDocumentTest {

	private static final String PDF_FILE = 
			"/home/charalampos/downloads/resign-patterns.pdf";

	@Test
	public void testRead() throws DocumentException {		
		PDFDocument document = new PDFDocument(PDF_FILE);
		document.read();		
		assertNotNull(document.getContent());
	}

	@Test
	public void testPaging() throws DocumentException {		
		IDocument document = new PagingDecorator( 
				new PDFDocument(PDF_FILE));
		document.read();
		document.decorate();
		document.save(PDF_FILE + ".tmp");
		assertNotNull(document.getContent());
	}
	
	@Test
	public void testPagingANDHeader() throws DocumentException {		
		IDocument document = new PagingDecorator( 
				new HeaderDecorator( 
						new PDFDocument(PDF_FILE)));
		document.read();
		document.decorate();
		document.save(PDF_FILE + ".tmp");
		assertNotNull(document.getContent());
	}
	
	@Test
	public void testPagingANDWatermark() throws DocumentException {		
		IDocument document = new PagingDecorator( 
				new WatermarkDecorator( 
						new PDFDocument(PDF_FILE)));
		document.read();
		document.decorate();
		document.save(PDF_FILE + ".tmp");
		assertNotNull(document.getContent());
	}
	
	@Test
	public void testHeaderANDWatermark() throws DocumentException {		
		IDocument document = new HeaderDecorator( 
				new WatermarkDecorator( 
						new PDFDocument(PDF_FILE)));
		document.read();
		document.decorate();
		document.save(PDF_FILE + ".tmp");
		assertNotNull(document.getContent());
	}	

}
