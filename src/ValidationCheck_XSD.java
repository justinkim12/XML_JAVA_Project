import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ValidationCheck_XSD {
	public static String xmlFile;
	public static String xsdFile;
	public static boolean validationCheck = false;

	public ValidationCheck_XSD(String xml_path,String xsd_path) {
		this.xmlFile=xml_path;
		this.xsdFile=xsd_path;
		this.validationCheck=true;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			SchemaFactory schemaFactory = null;
			if (validationCheck) {
				factory.setValidating(false);
				factory.setNamespaceAware(true);
				schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        
				factory.setSchema(schemaFactory.newSchema(new Source[] { new StreamSource(xsdFile) }));
			}
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
	    	builder.setErrorHandler(new SimpleErrorHandler());
			Document doc = builder.parse(xmlFile);
			JOptionPane.showMessageDialog(null, "Validation Done", "Validation", JOptionPane.INFORMATION_MESSAGE);
		} catch (ParserConfigurationException e) {
			JOptionPane.showMessageDialog(null, "Validation Error!", "Validation", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();

		}
		catch (SAXException e) { 
			JOptionPane.showMessageDialog(null, "Validation Error!", "Validation", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace(); } 
		catch (IOException e) { 
			JOptionPane.showMessageDialog(null, "Validation Error!", "Validation", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace(); }
	}

} 

