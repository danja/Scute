/**
 * 
 */
package org.hyperdata.scute.triples;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import org.hyperdata.scute.rdf.RdfUtils;

public class TripleTableModel extends AbstractTableModel {

	private Model model;
	private List<Statement> statements = new ArrayList<Statement>();

	private static final String[] columnNames = { "Subject", "Property",
			"Object" };

	public TripleTableModel(Model model) {
		this.model = model;
		RdfUtils.setPrefixes(model); // should already have happened
		readStatements(null, null, null);
	}

	private void readStatements(Resource subject, Property property,
			RDFNode object) {
		statements.clear();
		StmtIterator iterator = model.listStatements(subject, property, object);
		while (iterator.hasNext()) {
			statements.add(iterator.next());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return 3;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int index) {
		return columnNames[index];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return statements.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int row, int column) {
		Statement statement = statements.get(row);
		switch (column) {
		case 0:
			return toDisplayString(statement.getSubject());
		case 1:
			return toDisplayString(statement.getPredicate());
		case 2:
			return toDisplayString(statement.getObject());
		}
		return null;
	}
	
	private String toDisplayString(RDFNode node){
		if(node.isAnon()){
			return node.asResource().getId().getLabelString();
		}
		if(node.isURIResource()){
			Resource resource = node.asResource();
			String prefix = model.getNsURIPrefix(resource.getNameSpace());
			if(prefix == null){
				return resource.getURI();
			}
			return prefix+":"+resource.getLocalName();
		}
		if(node.isLiteral()){
			Literal literal = node.asLiteral();
			// TODO datatype??
			return literal.toString();
		}
		return "?error!?";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO make true later
		return false;
	}
}
