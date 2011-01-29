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

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.AnonId;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.RDFVisitor;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

/**
 * The Class Edge.
 */
class Edge extends VisibleEdge implements Property {

	/** The property. */
	private Property property;
	
	/** The from. */
	public org.hyperdata.scute.graph.Node from;
	
	/** The to. */
	public org.hyperdata.scute.graph.Node to;

	/**
	 * Instantiates a new edge.
	 */
	public Edge() { // @TODO get rid
		super();
	}

	/**
	 * Instantiates a new edge.
	 * 
	 * @param model
	 *            the model
	 * @param namespace
	 *            the namespace
	 * @param localName
	 *            the local name
	 */
	public Edge(Model model, String namespace, String localName) {
		property = model.createProperty(namespace, localName);
	}

	/**
	 * Gets the string.
	 * 
	 * @return the string
	 */
	public String getString() {
		// System.out.println("Property in getString() = "+this.property);

		return property.getLocalName();
	}

	// // delegate to Property

	/**
	 * Instantiates a new edge.
	 * 
	 * @param p
	 *            the p
	 */
	public Edge(Property p) {
		property = p;
		// System.out.println("Property in little constructoir = "+this.property);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Property#getLocalName()
	 */
	@Override
	public String getLocalName() {
		return property.getLocalName();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Property#getNameSpace()
	 */
	@Override
	public String getNameSpace() {
		return property.getNameSpace();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Property#getOrdinal()
	 */
	@Override
	public int getOrdinal() {
		return property.getOrdinal();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Property#inModel(com.hp.hpl.jena.rdf.model.Model)
	 */
	@Override
	public Property inModel(Model m) {
		return property.inModel(m);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Property#isProperty()
	 */
	@Override
	public boolean isProperty() {
		return property.isProperty();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#abort()
	 */
	@Override
	public Resource abort() {
		return property.abort();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#addLiteral(com.hp.hpl.jena.rdf.model.Property, boolean)
	 */
	@Override
	public Resource addLiteral(Property p, boolean o) {
		return property.addLiteral(p, o);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#addLiteral(com.hp.hpl.jena.rdf.model.Property, long)
	 */
	@Override
	public Resource addLiteral(Property p, long o) {
		return property.addLiteral(p, o);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#addLiteral(com.hp.hpl.jena.rdf.model.Property, char)
	 */
	@Override
	public Resource addLiteral(Property p, char o) {
		return property.addLiteral(p, o);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#addLiteral(com.hp.hpl.jena.rdf.model.Property, double)
	 */
	@Override
	public Resource addLiteral(Property p, double o) {
		return property.addLiteral(p, o);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#addLiteral(com.hp.hpl.jena.rdf.model.Property, float)
	 */
	@Override
	public Resource addLiteral(Property p, float o) {
		return property.addLiteral(p, o);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#addLiteral(com.hp.hpl.jena.rdf.model.Property, java.lang.Object)
	 */
	@Override
	public Resource addLiteral(Property p, Object o) {
		return property.addLiteral(p, o);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#addLiteral(com.hp.hpl.jena.rdf.model.Property, com.hp.hpl.jena.rdf.model.Literal)
	 */
	@Override
	public Resource addLiteral(Property p, Literal o) {
		return property.addLiteral(p, o);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#addProperty(com.hp.hpl.jena.rdf.model.Property, java.lang.String)
	 */
	@Override
	public Resource addProperty(Property p, String o) {
		return property.addLiteral(p, o);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#addProperty(com.hp.hpl.jena.rdf.model.Property, com.hp.hpl.jena.rdf.model.RDFNode)
	 */
	@Override
	public Resource addProperty(Property p, RDFNode o) {
		return property.addLiteral(p, o);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#addProperty(com.hp.hpl.jena.rdf.model.Property, java.lang.String, java.lang.String)
	 */
	@Override
	public Resource addProperty(Property p, String o, String l) {
		return property.addLiteral(p, o);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#addProperty(com.hp.hpl.jena.rdf.model.Property, java.lang.String, com.hp.hpl.jena.datatypes.RDFDatatype)
	 */
	@Override
	public Resource addProperty(Property p, String lexicalForm,
			RDFDatatype datatype) {
		return property.addProperty(p, lexicalForm, datatype);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#begin()
	 */
	@Override
	public Resource begin() {
		return property.begin();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#commit()
	 */
	@Override
	public Resource commit() {
		return property.commit();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#getId()
	 */
	@Override
	public AnonId getId() {
		return getId();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#getProperty(com.hp.hpl.jena.rdf.model.Property)
	 */
	@Override
	public Statement getProperty(Property p) {
		return getProperty(p);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#getPropertyResourceValue(com.hp.hpl.jena.rdf.model.Property)
	 */
	@Override
	public Resource getPropertyResourceValue(Property p) {
		return property.getPropertyResourceValue(p);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#getRequiredProperty(com.hp.hpl.jena.rdf.model.Property)
	 */
	@Override
	public Statement getRequiredProperty(Property p) {
		return property.getRequiredProperty(p);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#getURI()
	 */
	@Override
	public String getURI() {
		return property.getURI();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#hasLiteral(com.hp.hpl.jena.rdf.model.Property, boolean)
	 */
	@Override
	public boolean hasLiteral(Property p, boolean o) {
		return property.hasLiteral(p, o);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#hasLiteral(com.hp.hpl.jena.rdf.model.Property, long)
	 */
	@Override
	public boolean hasLiteral(Property p, long o) {
		return property.hasLiteral(p, o);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#hasLiteral(com.hp.hpl.jena.rdf.model.Property, char)
	 */
	@Override
	public boolean hasLiteral(Property p, char o) {
		return property.hasLiteral(p, o);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#hasLiteral(com.hp.hpl.jena.rdf.model.Property, double)
	 */
	@Override
	public boolean hasLiteral(Property p, double o) {
		return property.hasLiteral(p, o);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#hasLiteral(com.hp.hpl.jena.rdf.model.Property, float)
	 */
	@Override
	public boolean hasLiteral(Property p, float o) {
		return property.hasLiteral(p, o);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#hasLiteral(com.hp.hpl.jena.rdf.model.Property, java.lang.Object)
	 */
	@Override
	public boolean hasLiteral(Property p, Object o) {
		return property.hasLiteral(p, o);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#hasProperty(com.hp.hpl.jena.rdf.model.Property)
	 */
	@Override
	public boolean hasProperty(Property p) {
		return property.hasProperty(p);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#hasProperty(com.hp.hpl.jena.rdf.model.Property, java.lang.String)
	 */
	@Override
	public boolean hasProperty(Property p, String o) {
		return property.hasProperty(p, o);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#hasProperty(com.hp.hpl.jena.rdf.model.Property, com.hp.hpl.jena.rdf.model.RDFNode)
	 */
	@Override
	public boolean hasProperty(Property p, RDFNode o) {
		return property.hasProperty(p, o);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#hasProperty(com.hp.hpl.jena.rdf.model.Property, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean hasProperty(Property p, String o, String l) {
		return property.hasProperty(p, o, l);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#hasURI(java.lang.String)
	 */
	@Override
	public boolean hasURI(String uri) {
		return property.hasURI(uri);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#listProperties()
	 */
	@Override
	public StmtIterator listProperties() {
		return property.listProperties();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#listProperties(com.hp.hpl.jena.rdf.model.Property)
	 */
	@Override
	public StmtIterator listProperties(Property p) {
		return property.listProperties(p);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#removeAll(com.hp.hpl.jena.rdf.model.Property)
	 */
	@Override
	public Resource removeAll(Property p) {
		return property.removeAll(p);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.Resource#removeProperties()
	 */
	@Override
	public Resource removeProperties() {
		return property.removeProperties();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#as(java.lang.Class)
	 */
	@Override
	public <T extends RDFNode> T as(Class<T> view) {
		return property.as(view);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#asLiteral()
	 */
	@Override
	public Literal asLiteral() {
		return property.asLiteral();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#asResource()
	 */
	@Override
	public Resource asResource() {
		return property.asResource();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#canAs(java.lang.Class)
	 */
	@Override
	public <T extends RDFNode> boolean canAs(Class<T> view) {
		return property.canAs(view);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#getModel()
	 */
	@Override
	public Model getModel() {
		return property.getModel();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#isAnon()
	 */
	@Override
	public boolean isAnon() {
		return isAnon();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#isLiteral()
	 */
	@Override
	public boolean isLiteral() {
		return isLiteral();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#isResource()
	 */
	@Override
	public boolean isResource() {
		return isResource();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#isURIResource()
	 */
	@Override
	public boolean isURIResource() {
		return property.isURIResource();
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.rdf.model.RDFNode#visitWith(com.hp.hpl.jena.rdf.model.RDFVisitor)
	 */
	@Override
	public Object visitWith(RDFVisitor rv) {
		return property.visitWith(rv);
	}

	/* (non-Javadoc)
	 * @see com.hp.hpl.jena.graph.FrontsNode#asNode()
	 */
	@Override
	public Node asNode() {
		return property.asNode();
	}

}