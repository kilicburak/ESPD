import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.xml.utils.NodeVector;
import java.util.UUID;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Main {
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		
	
			UUID idOne = UUID.randomUUID();
		  
	

		try {
			if (args.length != 4) {
				System.out.println("The Arguments are  not properly placed");
			}
			File sourceFile   =  new File(args[0]); // source file
			File templateFile =  new File(args[1]); // template file
			String value 	  =  new String(args[2]); // xsltParamName
			File resultedFile =  new File(args[3]); // result file
			if(!sourceFile.exists()){
				System.out.println("The File does not exist in the directory !");
			}
				if(!templateFile.exists()){
					System.out.println("The File does not exist in the directory !");
				}
			// Checking if the files have desired extensions
			String extentionOfSourceFile = FilenameUtils.getExtension(sourceFile.getName());
			String extentionOfTemplateFile = FilenameUtils.getExtension(templateFile.getName());
				
			if (!extentionOfSourceFile.equals("ods")) {
				System.out.println("Please choose a proper ODS  file");
			}
			if (!extentionOfTemplateFile.equals("xslt")) {
				System.out.println("Please choose a proper XSLT file");
			}

			ZipFile zip = new ZipFile(sourceFile);
			Enumeration<?> entries = zip.entries();
			while (entries.hasMoreElements()) {
				
				ZipEntry entry = (ZipEntry) entries.nextElement();
				
				
				if (entry.getName().equals("content.xml")) {
					
					
			

					 InputStream in = zip.getInputStream(entry);
					 
					
					 
					TransformerFactory file = TransformerFactory.newInstance();
					Transformer template = file.newTransformer(new StreamSource(templateFile));
					Source source = new StreamSource(in);
					Result result = new StreamResult(resultedFile);
					
					template.setParameter("inputTab" , value);
				     
				    template.transform(source, result);	
				            
				}     
				
				
			}
				
		
		}

		catch (TransformerConfigurationException e) {
			System.out.println(e.toString());
		} catch (TransformerException e) {
			System.out.println(e.toString());
		}

		
	}
}

