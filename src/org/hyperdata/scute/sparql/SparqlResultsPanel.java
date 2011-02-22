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
		JTabbedPane tabs = new JTabbedPane(JTabbedPane.BOTTOM);
		TextResultsView textView = new TextResultsView ();
		TableResultsView tableView = new TableResultsView();
		tabs.addTab("Text", textView);
		tabs.addTab("Table", tableView);
		add(tabs, BorderLayout.CENTER);
	}
}
