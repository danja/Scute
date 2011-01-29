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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.hyperdata.scute.main.GraphDemo;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

/**
 * The Class GraphDiagramPanel.
 */
public class GraphDiagramPanel extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3002182807894335317L;

	/** The graph set. */
	private final GraphSet graphSet;

	/** The graph layout. */
	private final GraphLayout graphLayout;

	/** The running. */
	private boolean running = false;

	/**
	 * Instantiates a new graph diagram panel.
	 * 
	 * @param model
	 *            the model
	 */
	public GraphDiagramPanel(Model model) {
		super();
		setSize(800, 800);

		graphSet = new GraphSet();
		interpret(model);

		graphLayout = new GraphLayout(this, graphSet);
		setBackground(Color.white);
		addMouseListener(new MouseHandler(this));
	}

	/**
	 * Initialize.
	 */
	public void initialize() {
		// @TODO scramble on startup kills display!
		// interpret(model);
		// scramble();
	}

	/*
	 * this was originally lifted from RDFNodeMap - can the two be merged?
	 */
	/**
	 * Interpret.
	 * 
	 * @param sourceModel
	 *            the source model
	 */
	public void interpret(Model sourceModel) {
		try {
			final StmtIterator iterator = sourceModel.listStatements();
			Statement statement;
			RDFNode object;

			while (iterator.hasNext()) {
				statement = iterator.next();

				addResource(statement.getSubject());
				object = statement.getObject();

				if (object.isResource()) {
					addResource((Resource) object);
				} else {
					addLiteral((Literal) object);
				}
				addStatement(statement);
			}
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		// requestFocusInWindow(false); // takes the focus off nodes - doesn't
		// work!
	}

	/**
	 * Scramble.
	 */
	public synchronized void scramble() {
		Dimension d = getSize();
		Iterator<Node> nIterator = graphSet.nodeIterator();
		while (nIterator.hasNext()) {
			Node n = nIterator.next();
			if (!n.isFixed()) {
				n.setX(10 + (d.width - 20) * Math.random());
				n.setY(10 + (d.height - 20) * Math.random());
			}
		}
	}

	/**
	 * Adds the literal.
	 * 
	 * @param literal
	 *            the literal
	 * @return the node
	 */
	private Node addLiteral(Literal literal) {
		Node node = graphSet.getNodeContaining(literal);
		if (node == null) {
			JButton button = new JButton();
			node = new Node(literal, button);
			button.setText(node.getString());
			button.setBackground(Color.green);
			add(button);
			node.setX(getWidth() * Math.random());
			node.setY(getHeight() * Math.random());
			node.setLabel(node.getString());
		}
		return graphSet.addNode(node);
	}

	/**
	 * Adds the resource.
	 * 
	 * @param resource
	 *            the resource
	 * @return the node
	 */
	private Node addResource(Resource resource) {
		Node node = graphSet.getNodeContaining(resource);
		if (node == null) {

			JButton button = new RoundButton(); // ellipse for URI nodes
			node = new Node(resource, button);
			button.setText(node.getString());
			button.setBackground(Color.pink);
			add(button);
			node.setX(getWidth() * Math.random());
			node.setY(getHeight() * Math.random());
			node.setLabel(node.getString());
			if (resource.isAnon()) { // circular for bnodes
				((RoundButton) button).setCircular();
			}
		}
		graphSet.addNode(node);
		return node;
	}

	/**
	 * Adds the statement.
	 * 
	 * @param statement
	 *            the statement
	 * @return the edge
	 */
	private Edge addStatement(Statement statement) {
		Property p = statement.getPredicate();
		Edge edge = new Edge(p);
		edge.from = graphSet.getNodeContaining(statement.getSubject());
		edge.to = graphSet.getNodeContaining(statement.getObject());
		((JButton) edge.getComponent()).setText(edge.getString());
		add(edge.getComponent());
		return graphSet.addEdge(edge);
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image image = graphLayout.getImage();
		g.drawImage(image, 0, 0, null);
		System.out.println("paint image");

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
	 * Gets the node.
	 * 
	 * @param i
	 *            the i
	 * @return the node
	 */
	public Node getNode(int i) {
		return graphSet.getNode(i);
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
	 * Checks if is running.
	 * 
	 * @return true, if is running
	 */
	public synchronized boolean isRunning() {
		return running;
	}

	/**
	 * Sets the running.
	 * 
	 * @param b
	 *            the new running
	 */
	public synchronized void setRunning(boolean b) {
		if (b) {
			scramble();
			graphLayout.start();
			this.running = true;
		} else {
			graphLayout.stop();
			this.running = false;
			// repaint();
		}
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