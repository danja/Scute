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

import org.hyperdata.scute.autosave.UserActivityListener;
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
	private GraphDiagramPanel graphDiagramPanel = null;

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
		loadModel(model);
		toolBar = new JToolBar("Graph Tools");
		addControls();
		add(toolBar, BorderLayout.SOUTH);
	}

	public void loadModel(Model model) {
		graphDiagramPanel = new GraphDiagramPanel(model);
		setLayout(new BorderLayout());
		add(graphDiagramPanel, BorderLayout.CENTER);

	}

	/**
	 * Adds the user activity listener.
	 * 
	 * @param listener
	 *            the listener
	 */
	public void addUserActivityListener(UserActivityListener listener) {
		// TODO to implement when GraphPanel supports editing
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
	 * @param workingModel
	 */

}
