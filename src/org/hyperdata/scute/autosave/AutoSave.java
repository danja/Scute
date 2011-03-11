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
package org.hyperdata.scute.autosave;

import java.awt.event.FocusEvent;

import java.awt.event.WindowEvent;
import java.util.EventObject;
import java.util.Timer;

import javax.swing.event.ChangeEvent;

import org.hyperdata.scute.cards.Card;
import org.hyperdata.scute.cards.CardsPanel;
import org.hyperdata.scute.main.Config;
import org.hyperdata.scute.main.ScratchPad;
import org.hyperdata.scute.main.Scute;
import org.hyperdata.scute.rdf.ModelContainer;
import org.hyperdata.scute.source.TextContainer;

/**
 * see http://esw.w3.org/IntegrityIsJobOne
 * 
 * Currently just saves stuff afresh every 6 seconds TODO could do with being
 * intelligent about when things have changed
 * 
 * TODO rationalize listeners
 * 
 * @author danny
 * 
 */
public class AutoSave extends UserActivityAdapter { //

	/** The text saver. */
	private TextSaver textSaver;
	private TextSaver scratchTextSaver;
	private TextContainer scratchTextContainer;

	/** The model timer. */
	private final Timer timer = new Timer();

	private ModelSaver modelSaver;

	private ModelContainer workingModelContainer;

	private TextContainer currentTextContainer;

	/**
	 * Inits the model saver.
	 * 
	 * @param container
	 *            the container
	 */
	public void setWorkingModelContainer(ModelContainer container) {
		workingModelContainer = container;
		// reschedule();
	}

	public void setCurrentTextContainer(TextContainer container) {
		currentTextContainer = container;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.hyperdata.scute.main.UserActivityListener#activityOccurred(java.util
	 * .EventObject)
	 */
	@Override
	public void activityOccurred(EventObject object) {
		reschedule();
	}

	/*
	 * java.awt.event.FocusListener
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusGained(FocusEvent event) {
		// scratchTextSaver - maybe..?
		textSaver.save(); // save the old one
		reschedule(); // start over
	}

	/**
	 * Effectively resets clock.
	 */
	public void reschedule() {
		System.out.println("RESCHEDULE!!!");

		if (modelSaver != null) {
			modelSaver.cancel();
		}
		if (textSaver != null) {
			textSaver.cancel();
		}
		if (scratchTextSaver != null) {
			scratchTextSaver.cancel();
		}
		
		timer.purge(); // clean queue

		modelSaver = new ModelSaver(workingModelContainer);
		timer.schedule(modelSaver, Config.self.getModelSaveDelay());

		if (currentTextContainer != null) {
			textSaver = new TextSaver(currentTextContainer);
			timer.schedule(textSaver, Config.self.getTextSaveDelay());
		}
		
		scratchTextSaver = new TextSaver(scratchTextContainer);
		timer.schedule(scratchTextSaver, Config.self.getTextSaveDelay());
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
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
	public void restorePreviousState(Scute scute) {
//		System.out.println("Config.self.getSelectedView() ="
//				+ Config.self.getSelectedView());

		scute.setSelectedCard(Config.self.getSelectedView());
		// rdfEditor.setSelectedTab(Config.self.getSelectedTab());
		// rdfEditor.setSourceText(getSavedText());

	}

	/**
	 * Gets the saved text.
	 * 
	 * @return the saved text
	 */
//	private String getSavedText() {
//		StringBuffer buffer = new StringBuffer("");
//		int ch;
//		try {
//			FileInputStream fis = new FileInputStream(Config.TEXT_FILENAME);
//			while ((ch = fis.read()) != -1) {
//				buffer.append((char) ch);
//			}
//			fis.close();
//		} catch (Exception e) {
//			// error popup
//			Log.exception(exception);;
//			return "";
//		}
//		return buffer.toString();
//	}

	/**
	 * Finish up.
	 */
	private void finishUp() {
		if (modelSaver != null) {
			modelSaver.cancel(); // clean shutdown of timed operations
			modelSaver.doSave(); // final saving
		}
		if (textSaver != null) {
			textSaver.cancel();
			textSaver.save();
		}
		if (scratchTextSaver != null) {
			scratchTextSaver.cancel();
			scratchTextSaver.save();
		}
		Config.self.setSync(true);
		Config.self.saveNow();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusLost(FocusEvent arg0) {

	}

	/* javax.swing.event.ChangeListener */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent
	 * )
	 */
	@Override
	public void stateChanged(ChangeEvent event) {

		CardsPanel cardsPanel = (CardsPanel) event.getSource();
		String current = cardsPanel.getCurrentCardName();
		Config.self.setSelectedView(current);
		Card currentCard = cardsPanel.getCard(current);
		if (currentCard instanceof TextContainer) {
			setCurrentTextContainer((TextContainer) currentCard);
		} else {
			setCurrentTextContainer(null);
		}
	}

	/**
	 * @param scratchPad
	 */
	public void setScratchTextContainer(TextContainer textContainer) {
		scratchTextContainer = textContainer;
	}

}
