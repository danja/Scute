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
package org.hyperdata.scute.rdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.hp.hpl.jena.rdf.model.Model;

import org.hyperdata.scute.system.Log;
import org.hyperdata.scute.system.panels.LogPane;

/**
 * ModelContainer - wrapper for models
 */
public class ModelContainer implements Runnable {

	/**
	 * Save.
	 */
	public void saveModelToFile() {
		try {
		// System.out.println("saving Model to file " + getModelFilename());
			RdfUtils.save(getModel(), getModelFilename());
			setSaved(true);
		} catch (IOException exception) {
			// TODO popup for file error
			Log.exception(exception);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		saveModelToFile();
	}

	public String getModelURI() {
		return this.modelURI;
	}

	public void setModelURI(String modelURI) {
		this.modelURI = modelURI;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void setModelFilename(String modelFilename) {
		this.modelFilename = modelFilename;
	}

	private Model model;
	private String modelFilename;
	private String modelURI;
	private boolean savedToFile;

	/**
	 * Gets the model.
	 * 
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * Gets the model filename.
	 * 
	 * @return the model filename
	 */
	public String getModelFilename() {
		return modelFilename;
	}

	/**
	 * Gets the model name.
	 * 
	 * @return the model name
	 */
	public String getModelName() {
		return modelURI;
	}

	public boolean isSaved() {
		return savedToFile;
	}

	/**
	 * @param b
	 */
	public void setSaved(boolean saved) {
		this.savedToFile = saved;
	}

	public void storeNamedModel() {
		System.out.println("STORE MODEL (only file bit in use) - implement me!");
		saveModelToFile();
	}

	public void loadNamedModel() {
		System.out.println("LOAD MODEL from URI - implement me!");
	}

	public void loadModelFromFile() {
		File file = new File(modelFilename);
		LogPane.println("Opening: " + file.getName());

		String syntax = "Turtle";
		if (file.getPath().toLowerCase().endsWith(".rdf")) {
			syntax = "RDF/XML";
		}

		try {
			final InputStream stream = new FileInputStream(file);
			Models.clearWorkingModel();
			Models.workingModel.read(new FileInputStream(file), "", syntax);
			stream.close();
		} catch (final Exception exception) {
			LogPane.println(exception.getMessage());
			Log.exception(exception);
		}
	}
}
