/**
 * 
 */
package org.hyperdata.scute.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hp.hpl.jena.rdf.model.RDFNode;

/**
 * @author danny
 * 
 */
public class GraphSet {

	// Node nodes[] = new Node[100];
	// Edge edges[] = new Edge[200];

	List<Node> nodes = new ArrayList<Node>();
	List<Edge> edges = new ArrayList<Edge>();

	public GraphSet() {

	}

	public Node getNode(int i) {
		// System.out.println("size = "+nodes.size()+"  i = "+i);
		return nodes.get(i);
	}

	// public void setNode(int nnodes, Node n) {
	// nodes.set(nnodes,n);
	// }

	// public void setEdge(int i, Edge e) {
	// edges.set(i,e);
	// }

	public Edge getEdge(int i) {
		return edges.get(i);
	}

	public Node addNode(Node node) {
		nodes.add(node);
		return node;
	}

	public Edge addEdge(Edge edge) {
		edges.add(edge);
		return edge;
	}

	public Iterator<Node> nodeIterator() {
		return nodes.iterator();
	}
	
	public Iterator<Edge> edgeIterator() {
		return edges.iterator();
	}

//	public int getNedges() {
//		return edges.size();
//	}

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

	public void listNodes() { // for debugging
		for (int i = 0; i < nodes.size(); i++) {
			System.out.println(i + "  noddy   " + nodes.get(i));
		}

	}

	public void listEdges() { // for debugging
		for (int i = 0; i < edges.size(); i++) {
			// System.out.println(i+"  edgy   "+edges.get(i));
			System.out.println();
			System.out.println(i + "  from   " + edges.get(i).from);
			System.out.println(i + "  to   " + edges.get(i).to);
			System.out.println(i + "  to   " + edges.get(i).getString());
		}
	}

	public Node getRandomNode() {
		return nodes.get((int) (Math.random() * nodes.size()));
	}


}
