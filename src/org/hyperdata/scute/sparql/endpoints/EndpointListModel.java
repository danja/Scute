/**
 * 
 */
package org.hyperdata.scute.sparql.endpoints;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

import com.hp.hpl.jena.rdf.model.Model;
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
 */
public class EndpointListModel extends DefaultComboBoxModel {

	private List<Endpoint> targets = new ArrayList<Endpoint>();
	
	private JPanel editBox;
	private Model model = null;

	public EndpointListModel() {
		makeSpecialCases();
		loadEndpoints();
	}

	@Override
	public Object getElementAt(int i) {
		return targets.get(i);
	}

	private void makeSpecialCases() {
		Endpoint workingModelEndpoint = new Endpoint("Working Model",
				new WorkingModelEndpointAction());
		targets.add(workingModelEndpoint);
		Endpoint dbPediaEndpoint = new Endpoint("dbPedia",
				"http://dbpedia.org/sparql");
		targets.add(dbPediaEndpoint);
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
	
//	[ a void:Dataset;
//    void:sparqlEndpoint <http://dbpedia.org/sparql>;
//    dc:title "dbPedia" ] .

	private void loadEndpoints(){
		try {
			model = RdfUtils.load(Config.ENDPOINTS_MODEL, "N3");
		} catch (IOException exception) {
			// TODO error
			exception.printStackTrace();
		}
		
		ResIterator datasets = model.listSubjectsWithProperty(RDF.type, VOID.Dataset);
		while(datasets.hasNext()){
			Resource dataset = datasets.next();
			String endpointURI = null;
			try {
				endpointURI = dataset.getProperty(VOID.sparqlEndpoint).getObject().asResource().getURI();
			} catch (Exception exception){
				exception.printStackTrace();
			}
			String title = null;
			try {
				title = dataset.getProperty(DCTerms.title).getObject().asLiteral().toString();
			} catch (Exception exception){
				exception.printStackTrace();
			}
			Endpoint endpoint = new Endpoint(title,endpointURI);
			targets.add(endpoint);
		}
	}

	public void showEditBox(){
		if(editBox == null){
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
