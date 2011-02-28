/**
 * 
 */
package org.hyperdata.scute.sparql;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import org.hyperdata.scute.sparql.actions.WorkingModelEndpointAction;

/**
 * @author danny
 * 
 */
public class EndpointListModel extends DefaultComboBoxModel {

	private List<Endpoint> targets = new ArrayList<Endpoint>();

	public EndpointListModel() {
		makeSamples();
	}

	@Override
	public Object getElementAt(int i) {
		return targets.get(i);
	}

	private void makeSamples() {
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

}
