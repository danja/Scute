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

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

/**
 * The Class Models.
 */
public class Models {

	/** The working model. */
	public static Model workingModel = ModelFactory.createDefaultModel();
	
	/** The temp model. */
	public static Model tempModel = ModelFactory.createDefaultModel();
	// public static Model configModel = getConfigModel();
	/** The sample model. */
	public static Model sampleModel = getSampleModel();

	/** The dataset. */
	private final Dataset dataset = null;

	/**
	 * Close.
	 */
	public void close() {
		dataset.close();
	}

	/**
	 * Clear working model.
	 */
	public static void clearWorkingModel() {
		workingModel = ModelFactory.createDefaultModel();
	}

	/**
	 * Gets the sample model.
	 * 
	 * @return the sample model
	 */
	private static Model getSampleModel() {
		if (sampleModel == null) {
			sampleModel = ModelFactory.createDefaultModel();
			Resource r1 = sampleModel
					.createResource("http://purl.org/stuff/andy");
			Property p1 = sampleModel
					.createProperty("http://xmlns.com/foaf/0.1/knows");
			Resource r2 = sampleModel
					.createResource("http://purl.org/stuff/brian");
			Statement st1 = sampleModel.createStatement(r1, p1, r2);
			sampleModel.add(st1);

			Resource r3 = sampleModel
					.createResource("http://purl.org/stuff/carl");
			sampleModel.createResource("http://purl.org/stuff/david");
			Statement st2 = sampleModel.createStatement(r1, p1, r2);
			sampleModel.add(st2);

			Property p2 = sampleModel
					.createProperty("http://xmlns.com/foaf/0.1/name");
			Literal l1 = sampleModel.createLiteral("carl");
			Statement st3 = sampleModel.createStatement(r3, p2, l1);
			sampleModel.add(st3);

			Statement st4 = sampleModel.createStatement(r2, p1, r1);
			sampleModel.add(st4);
		}
		return sampleModel;
	}

}
