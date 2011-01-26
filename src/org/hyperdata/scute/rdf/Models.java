package org.hyperdata.scute.rdf;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

public class Models {

	public static Model workingModel = ModelFactory.createDefaultModel();
	public static Model tempModel = ModelFactory.createDefaultModel();
	// public static Model configModel = getConfigModel();
	public static Model sampleModel = getSampleModel();

	private final Dataset dataset = null;

	public void close() {
		dataset.close();
	}

	public static void clearWorkingModel() {
		workingModel = ModelFactory.createDefaultModel();
	}

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
