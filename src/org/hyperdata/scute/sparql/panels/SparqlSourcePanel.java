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
import org.hyperdata.scute.source.EditorPane;

/**
 * @author danny
 * 
 */
public class SparqlSourcePanel extends EditorPane {

	private JPopupMenu popupMenu;
	private Map<String, String> prefixMap = RdfUtils.getCommonPrefixMap();


	public SparqlSourcePanel(String string) {
		super(string);
		// addUserActivityListener(autoSave);

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
