/**
 * 
 */
package org.hyperdata.scute.source.popup;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.hyperdata.scute.rdf.RdfUtils;
import org.hyperdata.scute.source.EditorPane;
import org.hyperdata.scute.system.Log;

/**
 * @author danny
 * 
 */
public class SourcePopupMenu extends JPopupMenu implements ActionListener {

	private static Map<String, String> prefixMap = RdfUtils.getAllPrefixes();
	private static Map<String, String> snippetsMap;
	private EditorPane editorPane;

	public SourcePopupMenu(EditorPane editorPane) {
		super();
		this.editorPane = editorPane;
		init();
	}

	private void init() {

		Snippets snippets = new Snippets(this, editorPane.getSyntax());
		snippetsMap = snippets.getMap();
		Iterator<String> snippetIterator = snippetsMap.keySet().iterator();
		while (snippetIterator.hasNext()) {
			String key = snippetIterator.next();
			JMenuItem menuItem = new JMenuItem(key);
			menuItem.addActionListener(this);
			snippets.add(menuItem);
		}

		JMenu prefixes = new JMenu("Prefix");

		Iterator<String> iterator = prefixMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			JMenuItem menuItem = new JMenuItem(key);
			menuItem.addActionListener(this);
			prefixes.add(menuItem);
		}
		if (!editorPane.getSyntax().equals("RDF/XML")) { // don't think it's the
															// best approach for
															// XML
			add(prefixes);
		}
		if (editorPane.getSyntax().equals("RDF/XML")) { // can't think of any useful snippets for Turtle
		add(snippets);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		MenuElement[] path = MenuSelectionManager.defaultManager()
        .getSelectedPath();
		JMenuItem source = (JMenuItem) event.getSource();
String name = source.getText();

		if(snippetsMap.containsKey(name)){ // not ideal, potential for conflicts
			insertText(snippetsMap.get(name) + "\n");
			return;
		}
		if (editorPane.getSyntax().equals("SPARQL")) {
			insertText("PREFIX " + name + ":\t\t" + "<" + prefixMap.get(name)
					+ ">\n");
			return;
		}
		if (editorPane.getSyntax().equals("Turtle")) {
			insertText("@PREFIX " + name + ":\t\t" + "<" + prefixMap.get(name)
					+ "> .\n");
			return;
		}
		// RDF/XML version not nice - and not sure it's useful
	}

	private void insertText(String insert) {
		Document doc = editorPane.getDocument();
		try {
			doc.insertString(0, insert, null);
		} catch (BadLocationException exception) {
			Log.exception(exception);
		}
	}
}
