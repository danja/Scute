/**
 * 
 */
package org.hyperdata.scute.source.popup;

import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JMenu;

/**
 * @author danny
 * 
 */
public class Snippets extends JMenu {

	private ActionListener listener;
	private Map<String, String> map = new TreeMap<String, String>();
	private String syntax;

	public Snippets(ActionListener listener, String syntax) {
		super("Snippets");
		this.listener = listener;
		this.syntax = syntax;
		if (syntax.equals("SPARQL")) {
			populateSparql();
		}
		if (syntax.equals("Turtle")) {
			populateTurtle();
		}
	}

	/**
	 * 
	 */
	private void populateTurtle() {

	}

	/**
	 * 
	 */
	private void populateSparql() {
		map.put("SELECT", "SELECT DISTINCT * WHERE {\n?s ?p ?o\n}");
	}

	/**
	 * @return
	 */
	public Map<String, String> getMap() {
		return map;
	}

}
