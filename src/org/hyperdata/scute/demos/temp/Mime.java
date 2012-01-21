/**
 * 
 */
package org.hyperdata.scute.demos.temp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author danny
 * 
 *         see also http://sourceforge.net/projects/jmimemagic
 * 
 */
public class Mime {

	public static String getType(String filename) {
		int dot = filename.lastIndexOf('.');
		//System.out.println(dot);
		if (dot != -1 && dot < filename.length() - 1) {
			String ext = filename.substring(dot + 1);
			//System.out.println(ext);
			String type = magic.get(ext);
			if (type != null) {
				return type;
			}
		}
		return "text/plain";
	}

	public static Map<String, String> magic = new HashMap<String, String>();

	static {
		// some of the types supported by jsyntaxpane
		magic.put("txt", "text/plain");
		magic.put("java", "text/java");
		magic.put("js", "text/javascript");
		magic.put("xml", "text/xml");
		magic.put("py", "text/python");
		magic.put("flex", "text/jflex");
		magic.put("scala", "text/scala");
		magic.put("sh", "text/bash");
		
		// some extras
		magic.put("html", "text/xml"); // worth a try :)
		magic.put("htm", "text/xml");
		
		magic.put("xsl", "text/xml");
		magic.put("xslt", "text/xml");
		
		magic.put("rdf", "text/xml");
		magic.put("ttl", "text/sparql");
		magic.put("turtle", "text/sparql");
		magic.put("n3", "text/sparql");
		magic.put("rq", "text/sparql");
		magic.put("sparql", "text/sparql");
	}

	public static void main(String[] args) {
		System.out.println(getType("werwer"));
		System.out.println(getType("werwer."));
		System.out.println(getType("."));
		System.out.println(getType(".wer"));
		System.out.println(getType("test.java"));
	}
}
