/**
 * 
 */
package org.hyperdata.scute.cards;

import java.awt.CardLayout;

import javax.swing.JScrollPane;

import org.jdesktop.swingx.*;
import org.jdesktop.swingx.action.*;
import org.jdesktop.swingx.MultiSplitLayout.*;
//
//import org.hdesktop.swingx.JXTaskPane;
//import org.hdesktop.swingx.JXTaskPaneContainer;
//import org.hdesktop.swingx.JXTitledPanel;

import org.hyperdata.resources.scute.ScuteIcons;

public class TaskPanel extends JXTitledPanel {

	private JXTaskPaneContainer taskPaneContainer;
	CardsPanel cardPanel;
	CardLayout layout;

	public TaskPanel(CardsPanel cardPanel) {
		super("Activities");
		this.cardPanel = cardPanel;
		this.layout = (CardLayout) cardPanel.getLayout();
		taskPaneContainer = new JXTaskPaneContainer();
		this.setContentContainer(new JScrollPane(taskPaneContainer));
		addTaskPanes();
	}

	private void addTaskPanes() {
		addEditorTaskPane();
		addSparqlTaskPane();
		addDataManagerTaskPane();
		addSystemTaskPane();
	}


	@SuppressWarnings("deprecation")
	private void addEditorTaskPane() {
		JXTaskPane taskPane = new JXTaskPane();
		taskPane.setIcon(ScuteIcons.rdfIcon);
		taskPane.setTitle("View/Edit RDF");
		taskPaneContainer.add(taskPane);

		taskPane.add(new ChangeEditorPanelAction(this, "Turtle", "Turtle View"));
		taskPane.add(new ChangeEditorPanelAction(this, "RDF/XML", "RDF/XML View"));
		taskPane.add(new ChangeEditorPanelAction(this, "Triples", "Triples View"));
		taskPane.add(new ChangeEditorPanelAction(this, "Tree", "Tree View"));
		taskPane.add(new ChangeEditorPanelAction(this, "Graph", "Graph View"));
		
		taskPane.add(new DocPanelAction(this, "Editors", "Documentation")); 
	}

	@SuppressWarnings("deprecation")
	private void addSparqlTaskPane() {
		JXTaskPane taskPane = new JXTaskPane();
		taskPane.setIcon(ScuteIcons.sparqlIcon);
		taskPane.setTitle("Run SPARQL Queries");
		taskPaneContainer.add(taskPane);

		taskPane.add(new ChangeEditorPanelAction(this, "SPARQL", "SPARQL Editor"));
		
		taskPane.add(new DocPanelAction(this, "SPARQL", "Documentation")); 
	}
	

	private void addDataManagerTaskPane() {
		JXTaskPane taskPane = new JXTaskPane();
		taskPane.setIcon(ScuteIcons.rdfIcon);
		taskPane.setTitle("Manage Data");
		taskPaneContainer.add(taskPane);

		taskPane.add(new ChangeEditorPanelAction(this, "Graphs", "Graph Manager"));
		
		taskPane.add(new ChangeEditorPanelAction(this, "Files", "File Manager"));
		
		taskPane.add(new DocPanelAction(this, "Manage", "Documentation"));
	}


	private void addSystemTaskPane() {
		JXTaskPane taskPane = new JXTaskPane();
		taskPane.setIcon(ScuteIcons.rdfIcon);
		taskPane.setTitle("System Features");
		taskPaneContainer.add(taskPane);

		taskPane.add(new ChangeEditorPanelAction(this, "Settings", "Settings"));
		taskPane.add(new ChangeEditorPanelAction(this, "Log", "System Log"));
		
		taskPane.add(new DocPanelAction(this, "System", "Documentation")); 
	}



}
