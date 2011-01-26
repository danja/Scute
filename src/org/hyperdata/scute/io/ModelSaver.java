package org.hyperdata.scute.io;

import java.io.IOException;
import java.util.TimerTask;

import org.hyperdata.scute.rdf.ModelContainer;
import org.hyperdata.scute.rdf.RdfUtils;

public class ModelSaver extends TimerTask {

	private final ModelContainer container;

	public ModelSaver(ModelContainer container) {
		this.container = container;
	}

	@Override
	public void run() {
		save();
	}

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
