package org.arapidis.decorators;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PagingDecorator extends DocumentDecorator{

	public PagingDecorator(IDocument document) {
		super(document);		
	}
	
	@Override
	public void decorate() {		
		super.decorate();		
		OutputStream out = null;
		PdfReader reader = null;
		PdfStamper stamper = null;
		
		byte[] content = getDecoratedDocument().getContent();
		
        try {
        	out = new ByteArrayOutputStream();
            reader = new PdfReader(content);            
            stamper = new PdfStamper(reader, out);            
            
            final int pageCount = reader.getNumberOfPages();
            final BaseFont font = BaseFont.createFont();
            for ( int pageNum = 1; pageNum <= pageCount; ++pageNum){
            	final PdfContentByte page = stamper.getOverContent(pageNum);
            	page.beginText();            	            	
            	page.setFontAndSize(font, 11);            	
            	page.showTextAligned(
            			PdfContentByte.ALIGN_RIGHT, 
            			String.valueOf(pageNum), 50, 50, 50);
            	page.endText();
            }
            
            stamper.close();
                        
            getDecoratedDocument().setContent( 
            		((ByteArrayOutputStream) out).toByteArray());
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
				
	}

}
