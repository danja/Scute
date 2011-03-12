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
			return RdfUtils.nodeToDisplayString(statement.getSubject());
		case 1:
			return RdfUtils.nodeToDisplayString(statement.getPredicate());
		case 2:
			return RdfUtils.nodeToDisplayString(statement.getObject());
		}
		return null;
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
