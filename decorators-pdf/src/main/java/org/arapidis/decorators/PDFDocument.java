package org.arapidis.decorators;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

import com.itextpdf.text.DocumentException;

public class PDFDocument implements IDocument{

	static Logger LOG = Logger.getLogger(
			PDFDocument.class.getName());
	
	private String path;
	private byte[] data;
	
	public PDFDocument(String src) {
		this.path = src;
	}
	
	public void read() throws DocumentException {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(path);
			out = new ByteArrayOutputStream();
		
			byte[] buffer = new byte[1024];		
			for (int readNum; (readNum = in.read(buffer)) != -1;) {
			    out.write(buffer, 0, readNum);            
			}
			setContent(((ByteArrayOutputStream) out).toByteArray()); 
		
		} catch (FileNotFoundException ex) {
			throw new DocumentException(ex);
		} catch (IOException ex) {
			throw new DocumentException(ex);
		} finally { close(in); close(out); }
						
	}

	public void decorate() {
		//		
	}

	public void save(String dest) throws DocumentException {		
		OutputStream out = null;		
			try {
				out = new FileOutputStream(dest);
				out.write(getContent());
			} catch (FileNotFoundException ex) {
				throw new DocumentException(ex);
			} catch (IOException ex) {
				throw new DocumentException(ex);
			}finally { close(out); }	
	}
	
	public byte[] getContent(){
		return this.data;
	}
	
	private static void close(Closeable stream){
		if (stream != null )
			try {
				stream.close();
			} catch (IOException e) {
				LOG.info("Failed to close stream: " 
			+ stream.getClass());
				
			}
	}

	public void setContent(final byte[] data) {
		this.data = data;
	}

}
