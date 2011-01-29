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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hp.hpl.jena.rdf.model.RDFNode;

/**
 * The Class GraphSet.
 * 
 * @author danny
 * 
 * @TODO give nodes weights L/R according to inlinks/outlinks (stick literals on the right!) - sort GraphSet?
 * 
 */
public class GraphSet {
	
	/** The nodes. */
	List<Node> nodes = new ArrayList<Node>();
	
	/** The edges. */
	List<Edge> edges = new ArrayList<Edge>();
	

	/**
	 * Instantiates a new graph set.
	 */
	public GraphSet() {

	}

	/**
	 * Adds the node.
	 * 
	 * @param node
	 *            the node
	 * @return the node
	 */
	public Node addNode(Node node) {
		nodes.add(node);
		return node;
	}

	/**
	 * Adds the edge.
	 * 
	 * @param edge
	 *            the edge
	 * @return the edge
	 */
	public Edge addEdge(Edge edge) {
		edges.add(edge);
		return edge;
	}

	/**
	 * Node iterator.
	 * 
	 * @return the iterator
	 */
	public Iterator<Node> nodeIterator() {
		return nodes.iterator();
	}
	
	/**
	 * Edge iterator.
	 * 
	 * @return the iterator
	 */
	public Iterator<Edge> edgeIterator() {
		return edges.iterator();
	}

//	public int getNedges() {
//		return edges.size();
//	}

	/**
 * Gets the node containing.
 * 
 * @param object
 *            the object
 * @return the node containing
 */
public org.hyperdata.scute.graph.Node getNodeContaining(RDFNode object) {

		org.hyperdata.scute.graph.Node node = null;

		for (int i = 0; i < nodes.size(); i++) {
			node = nodes.get(i);
			// RDFNode existing = node.getRDFNode();
			if (node.equalsRDF(object))
				return node;
		}
		return null;
	}

	/**
	 * List nodes.
	 */
	public void listNodes() { // for debugging
		for (int i = 0; i < nodes.size(); i++) {
			System.out.println(i + "  noddy   " + nodes.get(i));
		}

	}

	/**
	 * List edges.
	 */
	public void listEdges() { // for debugging
		for (int i = 0; i < edges.size(); i++) {
			// System.out.println(i+"  edgy   "+edges.get(i));
			System.out.println();
			System.out.println(i + "  from   " + edges.get(i).from);
			System.out.println(i + "  to   " + edges.get(i).to);
			System.out.println(i + "  to   " + edges.get(i).getString());
		}
	}

	/**
	 * Gets the random node.
	 * 
	 * @return the random node
	 */
	public Node getRandomNode() {
		return nodes.get((int) (Math.random() * nodes.size()));
	}


}
