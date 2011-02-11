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

import java.io.IOException;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * ModelContainer - wrapper for models
 */
public class ModelContainer implements Runnable {
	
	/**
	 * Save.
	 */
	public void save() {
		try {
			System.out.println("ModelSaver saving " + getModelURI());
			RdfUtils.save(getModel(), getModelFilename());
			setSaved(true);
		} catch (IOException e) {
			// TODO popup for file error
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		save();
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
	private boolean saved;

	/**
	 * Gets the model.
	 * 
	 * @return the model
	 */
	public Model getModel(){
		return this.model;
	}

	/**
	 * Gets the model filename.
	 * 
	 * @return the model filename
	 */
	public String getModelFilename(){
		return this.modelFilename;
	}

	/**
	 * Gets the model name.
	 * 
	 * @return the model name
	 */
	public String getModelName(){
		return this.modelURI;
	}
	
	public boolean isSaved(){
		return this.saved;
	}

	/**
	 * @param b
	 */
	public void setSaved(boolean saved){
		this.saved = saved;
	}


}
