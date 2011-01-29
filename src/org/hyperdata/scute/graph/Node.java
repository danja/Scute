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
/**
 * The Class Node.
 */
public class Node extends VisibleNode implements RDFNode {

	/** The rdf node. */
	private RDFNode rdfNode;

	/**
	 * Instantiates a new node.
	 */
	public Node() {
		super();
	}

	// used when reading in data files
	/**
	 * Instantiates a new node.
	 * 
	 * @param rdfNode
	 *            the rdf node
	 * @param component
	 *            the component
	 */
	public Node(RDFNode rdfNode, JButton component) {
		this.rdfNode = rdfNode;
		this.component = component;
		component.setBorder(new EmptyBorder(5, 5, 5, 5));
	}

	// for adding to graph
	/**
	 * Instantiates a new node.
	 * 
	 * @param model
	 *            the model
	 * @param uri
	 *            the uri
	 */
	public Node(Model model, String uri) { // Resource
		rdfNode = model.createResource(uri);
	}

	/**
	 * Instantiates a new node.
	 * 
	 * @param model
	 *            the model
	 */
	public Node(Model model) { // Bnode
		rdfNode = model.createResource();
	}

	/**
	 * Instantiates a new node.
	 * 
	 * @param model
	 *            the model
	 * @param v
	 *            the v
	 * @param wellFormed
	 *            the well formed
	 */
	public Node(Model model, String v, boolean wellFormed) { // Literal
		rdfNode = model.createLiteral(v, wellFormed);
	}

	/**
	 * Gets the string.
	 * 
	 * @return the string
	 */
	public String getString() {
		return getString(rdfNode);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() { // keep?
		return getString();
	}

	/**
	 * Gets the string.
	 * 
	 * @param rdfNode
	 *            the rdf node
	 * @return the string
	 */
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

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#as(java.lang.Class)
	 */
	@Override
	public <T extends RDFNode> T as(Class<T> view) {
		return rdfNode.as(view);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#asLiteral()
	 */
	@Override
	public Literal asLiteral() {
		return rdfNode.asLiteral();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#asResource()
	 */
	@Override
	public Resource asResource() {
		return rdfNode.asResource();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#canAs(java.lang.Class)
	 */
	@Override
	public <T extends RDFNode> boolean canAs(Class<T> view) {
		return rdfNode.canAs(view);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#getModel()
	 */
	@Override
	public Model getModel() {
		return rdfNode.getModel();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#inModel(com.hp.hpl.jena.rdf.model.Model)
	 */
	@Override
	public RDFNode inModel(Model m) {
		return rdfNode.inModel(m);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#isAnon()
	 */
	@Override
	public boolean isAnon() {
		return rdfNode.isAnon();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#isLiteral()
	 */
	@Override
	public boolean isLiteral() {
		return rdfNode.isLiteral();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#isResource()
	 */
	@Override
	public boolean isResource() {
		return rdfNode.isResource();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#isURIResource()
	 */
	@Override
	public boolean isURIResource() {
		return rdfNode.isURIResource();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#visitWith(com.hp.hpl.jena.rdf.model.RDFVisitor)
	 */
	@Override
	public Object visitWith(RDFVisitor rv) {
		return rdfNode.visitWith(rv);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.graph.FrontsNode#asNode()
	 */
	@Override
	public com.hp.hpl.jena.graph.Node asNode() {
		return rdfNode.asNode();
	}

	/**
	 * Gets the uRI.
	 * 
	 * @return the uRI
	 */
	public String getURI() {
		if (rdfNode.isResource())
			return ((Resource) rdfNode).getURI();
		return null;
	}

	/**
	 * Gets the rDF node.
	 * 
	 * @return the rDF node
	 */
	public RDFNode getRDFNode() {
		return rdfNode;
	}

	/**
	 * Equals rdf.
	 * 
	 * @param object
	 *            the object
	 * @return true, if successful
	 */
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