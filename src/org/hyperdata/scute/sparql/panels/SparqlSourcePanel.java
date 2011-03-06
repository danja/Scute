/**
 * 
 */
package org.hyperdata.scute.sparql.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.ChangeEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.hyperdata.scute.main.Config;
import org.hyperdata.scute.rdf.RdfUtils;
import org.hyperdata.scute.source.TextContainerEditorPane;

/**
 * @author danny
 * 
 */
public class SparqlSourcePanel extends TextContainerEditorPane implements
		ActionListener {

	private JPopupMenu popup;
	private Map<String, String> prefixMap = RdfUtils.getCommonPrefixMap();


	public SparqlSourcePanel(String string) {
		super(string);
		// addUserActivityListener(autoSave);
		createPopUpMenu();
		PopupListener popupListener = new PopupListener(popup);
		addMouseListener(popupListener);
	}





	/**
	 * 
	 */
	private void createPopUpMenu() {
		popup = new JPopupMenu();

		Iterator<String> iterator = prefixMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			JMenuItem menuItem = new JMenuItem(key);
			menuItem.addActionListener(this);
			popup.add(menuItem);
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
		String label = ((JMenuItem) event.getSource()).getText();
		String insert = "PREFIX " + label + ": " + "<" + prefixMap.get(label)
				+ ">\n";
		Document doc = getDocument();
		try {
			doc.insertString(0, insert, null);
		} catch (BadLocationException exception) {
			// TODO error
			exception.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent
	 * )
	 */
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.source.TextContainer#getSyntax()
	 */
	@Override
	public String getSyntax() {
		return "SPARQL";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.source.TextContainer#getTextFilename()
	 */
	@Override
	public String getFilename() {
		return Config.SPARQL_FILENAME;
	}

}
