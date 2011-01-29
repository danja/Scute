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
package org.hyperdata.scute.graph;

import java.awt.BorderLayout;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import org.hyperdata.scute.graph.actions.ToggleAction;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * Wraps the graph display panel, contains the buttons
 * 
 * (there must be a better way, but for now it'll do).
 * 
 * @author danny
 */
public class GraphPanel extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2907914679596311549L;
	
	/** The graph diagram panel. */
	private final GraphDiagramPanel graphDiagramPanel;
	
	/** The tool bar. */
	private final JToolBar toolBar;

	/**
	 * Instantiates a new graph panel.
	 * 
	 * @param model
	 *            the model
	 */
	public GraphPanel(Model model) {
		super();
		graphDiagramPanel = new GraphDiagramPanel(model);
		setLayout(new BorderLayout());
		add(graphDiagramPanel, BorderLayout.CENTER);
		toolBar = new JToolBar("Graph Tools");
		addControls();
		add(toolBar, BorderLayout.SOUTH);
		initialize();
	}

	/**
	 * Adds the controls.
	 */
	private void addControls() {
		Action toggleAction = new ToggleAction(graphDiagramPanel);
		JButton toggle = new JButton(toggleAction);
		toolBar.add(toggle);
	}

	/**
	 * Initialize.
	 */
	public void initialize() {
		graphDiagramPanel.initialize();
	}

	/**
	 * Sets the running.
	 * 
	 * @param b
	 *            the new running
	 */
	public synchronized void setRunning(boolean b) {
		graphDiagramPanel.setRunning(b);
	}

	// @Override
	// public void paintComponent(Graphics g) {
	// super.paintComponent(g);
	// graphDiagramPanel.paintComponent(g);
	// }
}
