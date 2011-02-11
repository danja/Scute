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
public class ModelSaver extends TimerTask {

	private ModelContainer modelContainer;
	/**
	 * @param container
	 */
	public ModelSaver(ModelContainer modelContainer) {
		this.modelContainer = modelContainer;
	}
	/* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {
		doSave();
	}
	/**
	 * 
	 */
	public void doSave() {
		modelContainer.saveModelToFile();	
	}
}
