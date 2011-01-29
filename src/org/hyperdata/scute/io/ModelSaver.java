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

import java.io.IOException;
import java.util.TimerTask;

import org.hyperdata.scute.rdf.ModelContainer;
import org.hyperdata.scute.rdf.RdfUtils;

/**
 * The Class ModelSaver.
 */
public class ModelSaver extends TimerTask {

	/** The container. */
	private final ModelContainer container;

	/**
	 * Instantiates a new model saver.
	 * 
	 * @param container
	 *            the container
	 */
	public ModelSaver(ModelContainer container) {
		this.container = container;
	}

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
		try {
			System.out.println("ModelSaver saving " + container.getModelName());
			RdfUtils.save(container.getModel(), container.getModelFilename());
		} catch (IOException e) {
			// TODO popup for file error
			e.printStackTrace();
		}

	}
}
