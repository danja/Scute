/**
 * 
 */
package org.hyperdata.scute.autosave;

import java.util.TimerTask;

import org.hyperdata.scute.rdf.ModelContainer;

/**
 * @author danny
 * 
 */
public class ModelSaver extends TimerTask implements Saveable {

	private ModelContainer modelContainer;

	/**
	 * @param container
	 */
	public ModelSaver(ModelContainer modelContainer) {
		this.modelContainer = modelContainer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {
		save();
		cancel(); // kill itself
	}

	/**
	 * 
	 */
	public void save() {
		if (modelContainer != null) { // get rid later - it's getting called
										// before the container's initialised
			modelContainer.saveModelToFile();
		}
	}
}
