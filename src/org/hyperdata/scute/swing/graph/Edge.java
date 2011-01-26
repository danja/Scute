package org.hyperdata.scute.swing.graph;

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

class Edge extends VisibleEdge implements Property {

	private Property property;
	public org.hyperdata.scute.swing.graph.Node from;
	public org.hyperdata.scute.swing.graph.Node to;

	public Edge() { // @TODO get rid
		super();
	}

	public Edge(Model model, String namespace, String localName) {
		property = model.createProperty(namespace, localName);
	}

	public String getString() {
		// System.out.println("Property in getString() = "+this.property);

		return property.getLocalName();
	}

	// // delegate to Property

	public Edge(Property p) {
		property = p;
		// System.out.println("Property in little constructoir = "+this.property);
	}

	@Override
	public String getLocalName() {
		return property.getLocalName();
	}

	@Override
	public String getNameSpace() {
		return property.getNameSpace();
	}

	@Override
	public int getOrdinal() {
		return property.getOrdinal();
	}

	@Override
	public Property inModel(Model m) {
		return property.inModel(m);
	}

	@Override
	public boolean isProperty() {
		return property.isProperty();
	}

	@Override
	public Resource abort() {
		return property.abort();
	}

	@Override
	public Resource addLiteral(Property p, boolean o) {
		return property.addLiteral(p, o);
	}

	@Override
	public Resource addLiteral(Property p, long o) {
		return property.addLiteral(p, o);
	}

	@Override
	public Resource addLiteral(Property p, char o) {
		return property.addLiteral(p, o);
	}

	@Override
	public Resource addLiteral(Property p, double o) {
		return property.addLiteral(p, o);
	}

	@Override
	public Resource addLiteral(Property p, float o) {
		return property.addLiteral(p, o);
	}

	@Override
	public Resource addLiteral(Property p, Object o) {
		return property.addLiteral(p, o);
	}

	@Override
	public Resource addLiteral(Property p, Literal o) {
		return property.addLiteral(p, o);
	}

	@Override
	public Resource addProperty(Property p, String o) {
		return property.addLiteral(p, o);
	}

	@Override
	public Resource addProperty(Property p, RDFNode o) {
		return property.addLiteral(p, o);
	}

	@Override
	public Resource addProperty(Property p, String o, String l) {
		return property.addLiteral(p, o);
	}

	@Override
	public Resource addProperty(Property p, String lexicalForm,
			RDFDatatype datatype) {
		return property.addProperty(p, lexicalForm, datatype);
	}

	@Override
	public Resource begin() {
		return property.begin();
	}

	@Override
	public Resource commit() {
		return property.commit();
	}

	@Override
	public AnonId getId() {
		return getId();
	}

	@Override
	public Statement getProperty(Property p) {
		return getProperty(p);
	}

	@Override
	public Resource getPropertyResourceValue(Property p) {
		return property.getPropertyResourceValue(p);
	}

	@Override
	public Statement getRequiredProperty(Property p) {
		return property.getRequiredProperty(p);
	}

	@Override
	public String getURI() {
		return property.getURI();
	}

	@Override
	public boolean hasLiteral(Property p, boolean o) {
		return property.hasLiteral(p, o);
	}

	@Override
	public boolean hasLiteral(Property p, long o) {
		return property.hasLiteral(p, o);
	}

	@Override
	public boolean hasLiteral(Property p, char o) {
		return property.hasLiteral(p, o);
	}

	@Override
	public boolean hasLiteral(Property p, double o) {
		return property.hasLiteral(p, o);
	}

	@Override
	public boolean hasLiteral(Property p, float o) {
		return property.hasLiteral(p, o);
	}

	@Override
	public boolean hasLiteral(Property p, Object o) {
		return property.hasLiteral(p, o);
	}

	@Override
	public boolean hasProperty(Property p) {
		return property.hasProperty(p);
	}

	@Override
	public boolean hasProperty(Property p, String o) {
		return property.hasProperty(p, o);
	}

	@Override
	public boolean hasProperty(Property p, RDFNode o) {
		return property.hasProperty(p, o);
	}

	@Override
	public boolean hasProperty(Property p, String o, String l) {
		return property.hasProperty(p, o, l);
	}

	@Override
	public boolean hasURI(String uri) {
		return property.hasURI(uri);
	}

	@Override
	public StmtIterator listProperties() {
		return property.listProperties();
	}

	@Override
	public StmtIterator listProperties(Property p) {
		return property.listProperties(p);
	}

	@Override
	public Resource removeAll(Property p) {
		return property.removeAll(p);
	}

	@Override
	public Resource removeProperties() {
		return property.removeProperties();
	}

	@Override
	public <T extends RDFNode> T as(Class<T> view) {
		return property.as(view);
	}

	@Override
	public Literal asLiteral() {
		return property.asLiteral();
	}

	@Override
	public Resource asResource() {
		return property.asResource();
	}

	@Override
	public <T extends RDFNode> boolean canAs(Class<T> view) {
		return property.canAs(view);
	}

	@Override
	public Model getModel() {
		return property.getModel();
	}

	@Override
	public boolean isAnon() {
		return isAnon();
	}

	@Override
	public boolean isLiteral() {
		return isLiteral();
	}

	@Override
	public boolean isResource() {
		return isResource();
	}

	@Override
	public boolean isURIResource() {
		return property.isURIResource();
	}

	@Override
	public Object visitWith(RDFVisitor rv) {
		return property.visitWith(rv);
	}

	@Override
	public Node asNode() {
		return property.asNode();
	}

}