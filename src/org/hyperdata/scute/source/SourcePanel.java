/*
 * Scute
 * 
 * Homepage: http://hyperdata.org/scute
 * 
 * License : http://www.apache.org/licenses/LICENSE-2.0
 * See also license.txt or http://hyperdata.org/wiki/Scute:License
 * 
 * Danny Ayers 2011
 */
package org.hyperdata.scute.source;

import java.awt.Component;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.hyperdata.scute.main.Config;
import org.hyperdata.scute.rdf.RdfUtils;

import com.hp.hpl.jena.rdf.model.Model;

// JPanel
/**
 * The Class SourcePanel.
 */
public class SourcePanel extends JEditorPane implements TextContainer,
		ChangeListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3186641002083966387L;
	// String base = "http://hyperdata.org/base";
	/** The model. */
	private Model model;
	
	/** The refresh button. */
	private JButton refreshButton;
	
	/** The scroll. */
	protected JScrollPane scroll;
	
	/** The syntax. */
	private String syntax;

	/**
	 * Instantiates a new source panel.
	 * 
	 * @param focusListener
	 *            the focus listener
	 * @param syntax
	 *            the syntax
	 */
	public SourcePanel(FocusListener focusListener, String syntax) {
		super();
		addFocusListener(focusListener);
		this.syntax = syntax;
		// addChangeListener(this);
	}

	/**
	 * Gets the refresh button.
	 * 
	 * @return the refresh button
	 */
	public JButton getRefreshButton() {
		return refreshButton;
	}

	/*
	 * public void stateChanged(ChangeEvent event) {
	 * parentListener.stateChanged(event); System.out.println("STATECHANGED IN
	 * TURTLE SOURCE PANE"); System.out.println(event.getSource()); }
	 */

	/*
	 * 
	 * public void actionPerformed(ActionEvent event) {
	 * System.out.println("ACTIONPERFORMED IN TURTLE SOURCE PANE");
	 * System.out.println(event.getSource()); if
	 * (event.getSource().equals(refreshButton)) {
	 * System.out.println("REFRESHING!!"); refreshFromText(); }
	 * parentListener.stateChanged(null); }
	 */

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.source.TextContainer#getSyntax()
	 */
	public String getSyntax() {
		return syntax;
	}

	/**
	 * Load model.
	 * 
	 * @param workingModel
	 *            the working model
	 */
	public void loadModel(Model workingModel) {
		model = workingModel;
		// System.out.println("SYNTAX " + getSyntax());
		final String text = RdfUtils.modelToString(model, getSyntax());
		setText(text);
		setCaretPosition(0); // scroll to top
		repaint();

	}

	/**
	 * Refresh from text.
	 */
	public void refreshFromText() {
		refreshFromString(getText());
	}

	/**
	 * Refresh from string.
	 * 
	 * @param string
	 *            the string
	 */
	public void refreshFromString(String string) {
		final Model modelCopy = model; // er - that'll be a pointer?
		try {
			model = RdfUtils.stringToModel(string, Config.baseUri, getSyntax());
		} catch (final Exception exception) {
			exception.printStackTrace();
			model = modelCopy;
			loadModel(model);
		}
	}

	/**
	 * Sets the syntax.
	 * 
	 * @param s
	 *            the new syntax
	 */
	public void setSyntax(String s) {
		syntax = s;
	}

	// called when tabs clicked
	/* (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	public void stateChanged(ChangeEvent e) {
		// System.out.println("Change "+e);
		final Component c = ((JTabbedPane) e.getSource())
				.getSelectedComponent();
		// System.out.println(c);
		if (this == c) {
			// System.out.println("refreshing "+c);
			// refreshFromModel();
			// repaint();
			System.out.println("TAB changed");
			System.out.println("current window = " + getSyntax());
		}
		System.out.println("refreshing " + c);
		loadModel(model);
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.source.TextContainer#getTextFilename()
	 */
	@Override
	public String getTextFilename() {
		return Config.TEXT_FILENAME;
	}

}