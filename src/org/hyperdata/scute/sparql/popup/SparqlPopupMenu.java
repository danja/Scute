/**
 * 
 */
package org.hyperdata.scute.sparql.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JEditorPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.hyperdata.scute.rdf.RdfUtils;
import org.hyperdata.scute.system.Log;

/**
 * @author danny
 * 
 */
public class SparqlPopupMenu extends JPopupMenu implements ActionListener {

	private static Map<String, String> prefixMap = RdfUtils.getCommonPrefixMap();
	private static Map<String, String> snippetsMap = getSnippetsMap();
	private JEditorPane editorPane;

	public SparqlPopupMenu(JEditorPane editorPane) {
		super();
		this.editorPane = editorPane;
		init();
	}

	/**
	 * @return
	 */
	private static Map<String, String> getSnippetsMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("SELECT", "SELECT DISTINCT * WHERE {\n?s ?p ?o\n}");
		return snippetsMap;
	}

	private void init() {
		JMenu snippets = new JMenu("Snippets");

		
		JMenu prefixes = new JMenu("Prefix");

		Iterator<String> iterator = prefixMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			JMenuItem menuItem = new JMenuItem(key);
			menuItem.addActionListener(this);
			prefixes.add(menuItem);
		}
			add(prefixes);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		JMenuItem source = (JMenuItem) event.getSource();
		System.out.println("SOURCE "+source);
		String label = source.getText();
		
		String insert = "PREFIX " + label + ": " + "<" + prefixMap.get(label)
				+ ">\n";
		Document doc = editorPane.getDocument();
		try {
			doc.insertString(0, insert, null);
		} catch (BadLocationException exception) {
			// TODO error
			Log.exception(exception);
		}
	}
}
