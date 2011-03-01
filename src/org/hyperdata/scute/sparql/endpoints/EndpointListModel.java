/**
 * 
 */
package org.hyperdata.scute.sparql.endpoints;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.RDF;

import org.hyperdata.scute.main.Config;
import org.hyperdata.scute.rdf.RdfUtils;
import org.hyperdata.scute.sparql.actions.WorkingModelEndpointAction;
import org.hyperdata.vocabs.VOID;

/**
 * @author danny
 * 
 * very sloppy right now
 * 
 * EndpointTableModel dangles off this
 * 
 * stuff is loaded from RDF at start, written back at end
 * 
 * proper RDF model-backed list/table models needed
 * 
 */
public class EndpointListModel extends DefaultComboBoxModel {

	private List<Endpoint> targets = new ArrayList<Endpoint>();

	private JPanel editBox;
	private Model model = null;

	public EndpointListModel() {
		makeSpecialCases();
		loadEndpointsFromFile();
	}

	@Override
	public Object getElementAt(int i) {
		return targets.get(i);
	}

	public void removeElementAt(int row) {
		Endpoint endpoint = targets.get(row);
		// super.removeElementAt(row);
		removeEndpoint(endpoint);
		targets.remove(row);
	}

	/**
	 * @param endpoint
	 */
	private void removeEndpoint(Endpoint endpoint) {

		String label = endpoint.getLabel();
		String uri = endpoint.getUri();

		ResIterator datasets = model.listSubjectsWithProperty(RDF.type,
				VOID.Dataset);
		while (datasets.hasNext()) {
			boolean match = false;
			Resource dataset = datasets.next();
			String endpointURI = null;
			try {
				endpointURI = dataset.getProperty(VOID.sparqlEndpoint)
						.getObject().asResource().getURI();
				if (uri.equals(endpointURI)) {
					match = true;
				} else {
					continue;
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			String title = null;
			try {
				title = dataset.getProperty(DCTerms.title).getObject()
						.asLiteral().toString();
				if (label.equals(title) && match) {
					model.removeAll(dataset, null, null);
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	private void makeSpecialCases() {
		Endpoint workingModelEndpoint = new Endpoint("Working Model",
				new WorkingModelEndpointAction());
		targets.add(workingModelEndpoint);
//		Endpoint dbPediaEndpoint = new Endpoint("dbPedia",
//				"http://dbpedia.org/sparql");
//		targets.add(dbPediaEndpoint);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.ListModel#getSize()
	 */
	@Override
	public int getSize() {
		return targets.size();
	}

	// [ a void:Dataset;
	// void:sparqlEndpoint <http://dbpedia.org/sparql>;
	// dc:title "dbPedia" ] .

	private void loadEndpointsFromFile() {
		try {
			model = RdfUtils.load(Config.ENDPOINTS_MODEL, "N3");
		} catch (IOException exception) {
			// TODO error
			exception.printStackTrace();
		}

		ResIterator datasets = model.listSubjectsWithProperty(RDF.type,
				VOID.Dataset);
		while (datasets.hasNext()) {
			Resource dataset = datasets.next();
			String endpointURI = null;
			try {
				endpointURI = dataset.getProperty(VOID.sparqlEndpoint)
						.getObject().asResource().getURI();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			String title = null;
			try {
				title = dataset.getProperty(DCTerms.title).getObject()
						.asLiteral().toString();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			Endpoint endpoint = new Endpoint(title, endpointURI);
			targets.add(endpoint);
		}
	}
	
	public void saveEndpointsToFile(){
		model = ModelFactory.createDefaultModel();
		for(int i=0;i<targets.size();i++){
			
			Endpoint endpoint = targets.get(i);
			String uri = endpoint.getUri();
			String label = endpoint.getLabel();
			
			if(uri == null) continue;
			if(!uri.startsWith("http://") && !uri.startsWith("file://")){ // crude!
				continue;
			}
			
			Resource subject = model.createResource();
			Resource endpointResource = model.createResource(uri);
			Literal labelLiteral = model.createLiteral(label);
			
			model.add(subject, RDF.type, VOID.Dataset);
			model.add(subject, DCTerms.title, labelLiteral);
			model.add(subject, VOID.sparqlEndpoint, endpointResource);
			
			RdfUtils.setCommonPrefixes(model);
		}
		try {
			RdfUtils.save(model, Config.ENDPOINTS_MODEL);
		} catch (IOException exception) {
			// TODO error
			exception.printStackTrace();
		}
	}

	public void showEditBox() {
		if (editBox == null) {
			editBox = new JPanel();
		}

	}

	/**
	 * @param endpoint
	 */
	public void addEndpoint(Endpoint endpoint) {
		targets.add(endpoint);
		// need to repaint??
	}
}
