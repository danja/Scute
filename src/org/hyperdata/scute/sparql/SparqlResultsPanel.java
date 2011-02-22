/**
 * 
 */
package org.hyperdata.scute.sparql;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * @author danny
 *
 */
public class SparqlResultsPanel extends JPanel {

	public SparqlResultsPanel(){
		super(new BorderLayout());
		
		TextResultsView textView = new TextResultsView ();
		TableResultsView tableView = new TableResultsView();
		HTTPView httpView = new HTTPView();
		
		JTabbedPane tabs = new JTabbedPane(JTabbedPane.BOTTOM);
		tabs.addTab("Text", textView);
		tabs.addTab("Table", tableView);
		tabs.addTab("HTTP", httpView);
		
		add(tabs, BorderLayout.CENTER);
	}
}
