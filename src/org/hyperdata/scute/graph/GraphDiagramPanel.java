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

public class GraphDiagramPanel extends JPanel {

	private static final long serialVersionUID = 3002182807894335317L;

	private final GraphSet graphSet;

	private final GraphLayout graphLayout;

	private boolean running = false;

	public GraphDiagramPanel(Model model) {
		super();
		setSize(800, 800);

		graphSet = new GraphSet();
		interpret(model);

		graphLayout = new GraphLayout(this, graphSet);
		setBackground(Color.white);
		addMouseListener(new MouseHandler(this));
	}

	public void initialize() {
		// @TODO scramble on startup kills display!
		// interpret(model);
		// scramble();
	}

	/*
	 * this was originally lifted from RDFNodeMap - can the two be merged?
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

	private Edge addStatement(Statement statement) {
		Property p = statement.getPredicate();
		Edge edge = new Edge(p);
		edge.from = graphSet.getNodeContaining(statement.getSubject());
		edge.to = graphSet.getNodeContaining(statement.getObject());
		((JButton) edge.getComponent()).setText(edge.getString());
		add(edge.getComponent());
		return graphSet.addEdge(edge);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image image = graphLayout.getImage();
		g.drawImage(image, 0, 0, null);
		System.out.println("paint image");

	}

	public void setPickFixed(boolean fixed) {
		graphLayout.setPickFixed(fixed);
	}

	public boolean isPickFixed() {
		return graphLayout.isPickFixed();
	}

	public Node getNode(int i) {
		return graphSet.getNode(i);
	}

	public Node getPick() {
		return graphLayout.getPick();
	}

	public void listEdges() {
		graphSet.listEdges(); // for debugging
	}

	public void listNodes() { // for debugging
		graphSet.listNodes();
	}

	public synchronized boolean isRunning() {
		return running;
	}

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
	
	public Iterator<Node> nodeIterator() {
		return graphSet.nodeIterator();
	}
}