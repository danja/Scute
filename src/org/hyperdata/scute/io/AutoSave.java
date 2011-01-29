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
package org.hyperdata.scute.io;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.util.Timer;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.hyperdata.scute.main.Config;
import org.hyperdata.scute.main.Scute;
import org.hyperdata.scute.rdf.ModelContainer;
import org.hyperdata.scute.source.TextContainer;

/**
 * see http://esw.w3.org/IntegrityIsJobOne
 * 
 * Currently just saves stuff afresh every 6 seconds could do with being more
 * intelligent about when things have changed
 * 
 * @author danny
 * 
 */
public class AutoSave extends WindowAdapter implements FocusListener,
		ChangeListener {

	/** The text timer. */
	private final Timer textTimer = new Timer();
	
	/** The text saver. */
	private TextSaver textSaver;
	
	/** The model timer. */
	private final Timer modelTimer = new Timer();
	
	/** The model saver. */
	private ModelSaver modelSaver;

	/* (non-Javadoc)
	 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent we) {
		finishUp();
		we.getWindow().dispose();
		System.exit(0);
	}

	/**
	 * Restore previous state.
	 * 
	 * @param rdfEditor
	 *            the rdf editor
	 */
	public void restorePreviousState(Scute rdfEditor) {
		System.out.println("Config.self.getSelectedTab()) ="
				+ Config.self.getSelectedTab());
		rdfEditor.setSelectedTab(Config.self.getSelectedTab());
		rdfEditor.setSourceText(getSavedText());
	}

	/**
	 * Gets the saved text.
	 * 
	 * @return the saved text
	 */
	private String getSavedText() {
		StringBuffer buffer = new StringBuffer("");
		int ch;
		try {
			FileInputStream fis = new FileInputStream(Config.TEXT_FILENAME);
			while ((ch = fis.read()) != -1) {
				buffer.append((char) ch);
			}
			fis.close();
		} catch (Exception e) {
			// error popup
			e.printStackTrace();
			return "";
		}
		return buffer.toString();
	}

	/**
	 * Finish up.
	 */
	private void finishUp() {
		if (modelSaver != null) {
			modelSaver.cancel(); // clean shutdown of timed operations
			modelSaver.save(); // final saving
		}
		if (textSaver != null) {
			textSaver.cancel();
			textSaver.save();
		}
		Config.self.setSync(true);
		Config.self.saveNow();
	}

	/**
	 * Inits the model saver.
	 * 
	 * @param container
	 *            the container
	 */
	public void initModelSaver(ModelContainer container) {
		modelSaver = new ModelSaver(container);
		modelTimer.scheduleAtFixedRate(modelSaver, Config.self
				.getModelSaveDelay(), Config.self.getModelSavePeriod());
	}

	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusGained(FocusEvent event) {
		TextContainer container = (TextContainer) event.getSource();
		textSaver = new TextSaver(container);
		textTimer
				.scheduleAtFixedRate(textSaver, Config.self.getTextSaveDelay(),
						Config.self.getTextSavePeriod());
	}

	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusLost(FocusEvent arg0) {

	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent event) { // from tabs
		int tabIndex = ((JTabbedPane) event.getSource()).getSelectedIndex();
		Config.self.setSelectedTab(tabIndex);
	}
}
