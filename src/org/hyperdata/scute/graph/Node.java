/**
 * wraps a Jena RDFNode
 * can be a Resource, Bnode or Literal
 * 
 */
package org.hyperdata.scute.graph;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.RDFVisitor;
import com.hp.hpl.jena.rdf.model.Resource;

// it might have been better to store the RDFNode in JComponent.getClientProperty(java.lang.Object) 
public class Node extends VisibleNode implements RDFNode {

	private RDFNode rdfNode;

	// new RoundButton("XXXX")

	public Node() {
		super();
	}

	// used when reading in data files
	public Node(RDFNode rdfNode, JButton component) {
		this.rdfNode = rdfNode;
		this.component = component;
		component.setBorder(new EmptyBorder(5, 5, 5, 5));
	}

	// for adding to graph
	public Node(Model model, String uri) { // Resource
		rdfNode = model.createResource(uri);
	}

	public Node(Model model) { // Bnode
		rdfNode = model.createResource();
	}

	public Node(Model model, String v, boolean wellFormed) { // Literal
		rdfNode = model.createLiteral(v, wellFormed);
	}

	public String getString() {
		return getString(rdfNode);
	}

	@Override
	public String toString() { // keep?
		return getString();
	}

	public static String getString(RDFNode rdfNode) {
		if (rdfNode.isURIResource())
			// return ((Resource)rdfNode).getURI();
			return ((Resource) rdfNode).getLocalName();
		else if (rdfNode.isResource())
			return "  ";
		// ((Resource) rdfNode).getId().toString(); //
		else if (rdfNode.isLiteral()) {
			String label = ((Literal) rdfNode).toString();
			// if(label.length() > 10){ // @TODO come back to this
			// label = "";
			// String[] split = label.split(" ");
			// for(int i=0;i<split.length;i++){
			// label = label + split[i] + " ";
			// if(label.length()>25) continue;
			// if(label.length() % 8 == 0) label = label +"\n";
			// }
			// }
			if (label.length() > 10) {
				label = label.substring(0, 9);
			}
			return label;
		}
		return null;
	}

	// delegate to RDFNode ///////////////////////////////////////

	@Override
	public <T extends RDFNode> T as(Class<T> view) {
		return rdfNode.as(view);
	}

	@Override
	public Literal asLiteral() {
		return rdfNode.asLiteral();
	}

	@Override
	public Resource asResource() {
		return rdfNode.asResource();
	}

	@Override
	public <T extends RDFNode> boolean canAs(Class<T> view) {
		return rdfNode.canAs(view);
	}

	@Override
	public Model getModel() {
		return rdfNode.getModel();
	}

	@Override
	public RDFNode inModel(Model m) {
		return rdfNode.inModel(m);
	}

	@Override
	public boolean isAnon() {
		return rdfNode.isAnon();
	}

	@Override
	public boolean isLiteral() {
		return rdfNode.isLiteral();
	}

	@Override
	public boolean isResource() {
		return rdfNode.isResource();
	}

	@Override
	public boolean isURIResource() {
		return rdfNode.isURIResource();
	}

	@Override
	public Object visitWith(RDFVisitor rv) {
		return rdfNode.visitWith(rv);
	}

	@Override
	public com.hp.hpl.jena.graph.Node asNode() {
		return rdfNode.asNode();
	}

	public String getURI() {
		if (rdfNode.isResource())
			return ((Resource) rdfNode).getURI();
		return null;
	}

	public RDFNode getRDFNode() {
		return rdfNode;
	}

	public boolean equalsRDF(RDFNode object) {
		// URI resources & properties
		if (object.isResource() && ((Resource) object).isURIResource()) {
			if (rdfNode.isResource() && ((Resource) rdfNode).isURIResource()) {
				if (((Resource) rdfNode).getURI().equals(
						((Resource) object).getURI()))
					return true;
				else
					return false;
			}
		}
		// bnode
		if (object.isAnon() && rdfNode.isAnon()) {
			if (((Resource) rdfNode).getId()
					.equals(((Resource) object).getId()))
				return true;
			else
				return false;
		}
		// literal
		if (object.isLiteral() && rdfNode.isLiteral()) {
			if (((Literal) object).equals((rdfNode))
					|| (((Literal) object).sameValueAs((Literal) rdfNode)))
				return true;
		}

		return false;
	}
}