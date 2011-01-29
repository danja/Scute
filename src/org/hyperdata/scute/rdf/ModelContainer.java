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

import com.hp.hpl.jena.rdf.model.Model;

/**
 * The Interface ModelContainer.
 */
public interface ModelContainer {
	
	/**
	 * Gets the model.
	 * 
	 * @return the model
	 */
	public Model getModel();

	/**
	 * Gets the model filename.
	 * 
	 * @return the model filename
	 */
	public String getModelFilename();

	/**
	 * Gets the model name.
	 * 
	 * @return the model name
	 */
	public String getModelName();
}
