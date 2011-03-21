/**
 * 
 */
package org.hyperdata.scute.syntax;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hyperdata.scute.system.Log;

/**
 * @author danny
 *
 *  not yet used, but might be handy for RDF/XML
 */
public class XmlUtils {
	// xmlns:foaf="http://xmlns.com/foaf/0.1/"
	
//	Document doc = editorPane.getDocument();
//	String text = doc.getText(0, doc.getLength());
	
	public static org.w3c.dom.Document toXMLDocument(String text) {
		try {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
        dbf.setValidating(false);
		DocumentBuilder db = dbf.newDocumentBuilder(); 
		
			return db.parse(text);
			
		} catch (Exception exception) {
			Log.exception(exception);
		}
		return null;
	}
}
