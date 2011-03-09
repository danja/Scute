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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;

import javax.swing.JPanel;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * The Class GraphDiagramPanel.
 * 
 * TODO add zoom & drag
 */
public class GraphDiagramPanel extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3002182807894335317L;

	/** The graph set. */
	private final GraphSet graphSet;

	/** The graph layout. */
	private final GraphLayout graphLayout;

	private RdfInterpreter rdfInterpreter;

	/**
	 * Instantiates a new graph diagram panel.
	 * 
	 * @param model
	 *            the model
	 */
	public GraphDiagramPanel(Model model) {
		super();
	//	setSize(800, 800);

		// get your data in!
		graphSet = new GraphSet();
		rdfInterpreter = new RdfInterpreter(this, graphSet);
		rdfInterpreter.interpret(model);

		setBackground(Color.white);
		
		graphLayout = new GraphLayout(this, graphSet);
		graphLayout.init();
		
		addMouseListener(new MouseHandler(this));
	}
	
	/**
	 * Checks if is running.
	 *
	 * @return true, if is running
	 */
	public boolean isRunning() {
		return graphLayout.isRunning();
	}

	/**
	 * Sets the running.
	 *
	 * @param run the new running
	 */
	public void setRunning(boolean run) {
		graphLayout.setRunning(run);
	}



	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image image = graphLayout.getImage();
		g.drawImage(image, 0, 0, null);
		// System.out.println("paint image");

	}

	/**
	 * Sets the pick fixed.
	 * 
	 * @param fixed
	 *            the new pick fixed
	 */
	public void setPickFixed(boolean fixed) {
		graphLayout.setPickFixed(fixed);
	}

	/**
	 * Checks if is pick fixed.
	 * 
	 * @return true, if is pick fixed
	 */
	public boolean isPickFixed() {
		return graphLayout.isPickFixed();
	}


	/**
	 * Gets the pick.
	 * 
	 * @return the pick
	 */
	public Node getPick() {
		return graphLayout.getPick();
	}

	/**
	 * List edges.
	 */
	public void listEdges() {
		graphSet.listEdges(); // for debugging
	}

	/**
	 * List nodes.
	 */
	public void listNodes() { // for debugging
		graphSet.listNodes();
	}

	
	/**
	 * Node iterator.
	 * 
	 * @return the iterator
	 */
	public Iterator<Node> nodeIterator() {
		return graphSet.nodeIterator();
	}
}