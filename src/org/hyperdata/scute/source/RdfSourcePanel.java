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

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import org.hyperdata.scute.autosave.UserActivityListener;
import org.hyperdata.scute.cards.CardsPanel;
import org.hyperdata.scute.main.Config;
import org.hyperdata.scute.rdf.RdfUtils;
import org.hyperdata.scute.system.Log;

import com.hp.hpl.jena.rdf.model.Model;

// JPanel
/**
 * The Class SourcePanel.
 */
public class RdfSourcePanel extends EditorPane {

	/**
	 * Adds the user activity listener.
	 * 
	 * @param listener
	 *            the listener
	 */
	public void addUserActivityListener(UserActivityListener listener) {
		getDocument().addDocumentListener(listener);
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3186641002083966387L;
	// String base = "http://hyperdata.org/base";
	/** The model. */
	private Model model;

	/** The refresh button. */
	private JButton refreshButton;

	/** The scroll. */
	protected JScrollPane scroll;

	/**
	 * Instantiates a new source panel.
	 * 
	 * @param syntax
	 *            the syntax
	 */
	public RdfSourcePanel(String syntax) {
		super(syntax);
	}

	/**
	 * Gets the refresh button.
	 * 
	 * @return the refresh button
	 */
	public JButton getRefreshButton() {
		return refreshButton;
	}


	/**
	 * Load model.
	 * 
	 * @param workingModel
	 *            the working model
	 *            XXXMODEL
	 */
	public void loadModel(Model workingModel) {
		model = workingModel;
		// System.out.println("SYNTAX " + getSyntax());
		final String text = RdfUtils.modelToString(model, getSyntax());
		setText(text);
		setCaretPosition(0); // scroll to top
		repaint();
	}

	public void saveModel() { // XXXMODEL
		try {
			RdfUtils.save(model, Config.WORKING_MODEL_FILENAME);
		} catch (IOException exception) {
			Log.exception(exception);
		}
	}
	
	/**
	 * Refresh from text.
	 */
	public void refreshModelFromText() {
		refreshModelFromString(getText());
	}

	/**
	 * Refresh from string.
	 * 
	 * @param string
	 *            the string
	 */
	public void refreshModelFromString(String string) {
		final Model modelCopy = model; // er - that'll be a pointer?
		try {
			model = RdfUtils.stringToModel(string, Config.baseUri, getSyntax());
		} catch (Exception exception) {
			Log.exception(exception);
			model = modelCopy;
			loadModel(model);
		}
	}


	// called when tabs clicked
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent
	 * )
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		
		// String currentView = getSyntax();
		String view = ((CardsPanel) e.getSource()).getCurrentCardName();
		// System.out.println("VIEW="+view);
	//	System.out.println("RdfSoucePanel view = "+view);
// save();
		if (this.getSyntax().equals(view)) {
			// load();
			loadModel(model);
		}
	}
}