package org.hyperdata.scute.graph;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import org.hyperdata.scute.swing.RoundButton;

/**
 * @author danny
 * 
 * Reads the nodes & arcs in a Jena Model and 
 *
 */
public class RdfInterpreter {
	private JPanel displayPanel;
	private GraphSet graphSet;

	public RdfInterpreter(JPanel displayPanel, GraphSet graphSet){
		this.displayPanel = displayPanel;
		this.graphSet = graphSet;
	}
	
	/*
	 * this was originally lifted from RDFNodeMap in RdfTree - can the two be merged?
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
			displayPanel.add(button);
			node.setX(displayPanel.getWidth() * Math.random());
			node.setY(displayPanel.getHeight() * Math.random());
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
			displayPanel.add(button);
			node.setX(displayPanel.getWidth() * Math.random());
			node.setY(displayPanel.getHeight() * Math.random());
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
		displayPanel.add(edge.getComponent());
		return graphSet.addEdge(edge);
	}
}
