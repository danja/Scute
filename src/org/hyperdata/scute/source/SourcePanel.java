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
public class SourcePanel extends JEditorPane implements TextContainer,
		ChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3186641002083966387L;
	// String base = "http://hyperdata.org/base";
	private Model model;
	private JButton refreshButton;
	protected JScrollPane scroll;
	private String syntax;

	public SourcePanel(FocusListener focusListener, String syntax) {
		super();
		addFocusListener(focusListener);
		this.syntax = syntax;
		// addChangeListener(this);
	}

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

	public String getSyntax() {
		return syntax;
	}

	public void loadModel(Model workingModel) {
		model = workingModel;
		// System.out.println("SYNTAX " + getSyntax());
		final String text = RdfUtils.modelToString(model, getSyntax());
		setText(text);
		setCaretPosition(0); // scroll to top
		repaint();

	}

	public void refreshFromText() {
		refreshFromString(getText());
	}

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

	public void setSyntax(String s) {
		syntax = s;
	}

	// called when tabs clicked
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

	@Override
	public String getTextFilename() {
		return Config.TEXT_FILENAME;
	}

}