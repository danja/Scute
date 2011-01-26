package org.hyperdata.scute.swing.graph;

import java.awt.BorderLayout;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;


import org.hyperdata.scute.swing.graph.actions.ToggleAction;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * Wraps the graph display panel, contains the buttons
 * 
 *  (there must be a better way, but for now it'll do)
 *  
 * @author danny
 *
 */
public class GraphPanel extends JPanel {

	private GraphDiagramPanel graphDiagramPanel;
	private JToolBar toolBar;

	public GraphPanel(Model model){
		super();
		graphDiagramPanel = new GraphDiagramPanel(model);
		setLayout(new BorderLayout());
		add(graphDiagramPanel, BorderLayout.CENTER);
		 toolBar = new JToolBar("Graph Tools");
		 addControls();
		 add(toolBar, BorderLayout.SOUTH);
		initialize();
	}
	
	private void addControls() {
		Action toggleAction = new ToggleAction(graphDiagramPanel);
		JButton toggle = new JButton(toggleAction);
		toolBar.add(toggle);
	}
	


	public void initialize() {
		graphDiagramPanel.initialize();
	}
	
	public synchronized void setRunning(boolean b){
		graphDiagramPanel.setRunning(b);
	}
}
