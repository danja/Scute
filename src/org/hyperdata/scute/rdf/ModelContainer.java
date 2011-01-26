package org.hyperdata.scute.rdf;

import com.hp.hpl.jena.rdf.model.Model;

public interface ModelContainer {
	public Model getModel();

	public String getModelFilename();

	public String getModelName();
}
