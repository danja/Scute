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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.TimerTask;

import org.hyperdata.scute.main.Config;
import org.hyperdata.scute.source.TextContainerEditorPane;

/**
 * The Class TextSaver.
 */
public class TextSaver extends TimerTask {

	/** The container. */
	private final TextContainerEditorPane container;
//	private String filename;

	/**
	 * Instantiates a new text saver.
	 * 
	 * @param container
	 *            the container
	 */
	public TextSaver(TextContainerEditorPane container) {
		this.container = container;
	}
	
//	public void setFilename(String filename){ // Config.TEXT_FILENAME
//		this.filename = filename;
//	}

	/* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {
		save();
	}

	/**
	 * Save.
	 */
	public void save() {
		container.save();
	}
}