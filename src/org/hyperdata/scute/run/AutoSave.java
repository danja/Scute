package org.hyperdata.scute.run;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.util.Timer;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.hyperdata.scute.Config;
import org.hyperdata.scute.io.ModelSaver;
import org.hyperdata.scute.io.TextSaver;
import org.hyperdata.scute.rdf.ModelContainer;
import org.hyperdata.scute.swing.source.TextContainer;

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

	private final Timer textTimer = new Timer();
	private TextSaver textSaver;
	private final Timer modelTimer = new Timer();
	private ModelSaver modelSaver;

	@Override
	public void windowClosing(WindowEvent we) {
		finishUp();
		we.getWindow().dispose();
		System.exit(0);
	}

	public void restorePreviousState(RdfEditor rdfEditor) {
		System.out.println("Config.self.getSelectedTab()) ="
				+ Config.self.getSelectedTab());
		rdfEditor.setSelectedTab(Config.self.getSelectedTab());
		rdfEditor.setSourceText(getSavedText());
	}

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

	public void initModelSaver(ModelContainer container) {
		modelSaver = new ModelSaver(container);
		modelTimer.scheduleAtFixedRate(modelSaver, Config.self
				.getModelSaveDelay(), Config.self.getModelSavePeriod());
	}

	@Override
	public void focusGained(FocusEvent event) {
		TextContainer container = (TextContainer) event.getSource();
		textSaver = new TextSaver(container);
		textTimer
				.scheduleAtFixedRate(textSaver, Config.self.getTextSaveDelay(),
						Config.self.getTextSavePeriod());
	}

	@Override
	public void focusLost(FocusEvent arg0) {

	}

	@Override
	public void stateChanged(ChangeEvent event) { // from tabs
		int tabIndex = ((JTabbedPane) event.getSource()).getSelectedIndex();
		Config.self.setSelectedTab(tabIndex);
	}
}
