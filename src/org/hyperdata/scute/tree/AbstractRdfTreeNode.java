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
package org.hyperdata.scute.tree;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.hyperdata.scute.rdf.RdfUtils;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

// extends DefaultMutableTreeNode 

/**
 * The Class AbstractRdfTreeNode.
 */
public abstract class AbstractRdfTreeNode implements RdfTreeNode {

	/** The dirty. */
	private boolean dirty = true;
	
	/** The model. */
	private Model model;
	
	/** The model filename. */
	private String modelFilename;
	
	/** The node map. */
	private final RdfNodeMap nodeMap;
	
	/** The parent statement. */
	private Statement parentStatement;

	/**
	 * Instantiates a new abstract rdf tree node.
	 * 
	 * @param nodeMap
	 *            the node map
	 * @param parentStatement
	 *            the parent statement
	 */
	public AbstractRdfTreeNode(RdfNodeMap nodeMap, Statement parentStatement) {
		this.nodeMap = nodeMap;
		this.parentStatement = parentStatement;
		model = nodeMap.getCurrentModel();
		modelFilename = nodeMap.getCurrentModelFilename();
	}

	/**
	 * Delete dialog.
	 * 
	 * @return true, if successful
	 */
	public boolean deleteDialog() {
		final int n = JOptionPane.showConfirmDialog(null, "Delete "
				+ toString() + "?", "Delete", JOptionPane.YES_NO_OPTION);
		return n == 0;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#deleteRequest()
	 */
	@Override
	public void deleteRequest() {
		if (deleteDialog()) {
			// getNodeMap().deleteNode(this);
			delete();
			try {
				RdfUtils.save(model, modelFilename);
			} catch (IOException e) {
				// TODO error popup
				e.printStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#getModel()
	 */
	@Override
	public Model getModel() {
		// return nodeMap.getCurrentModel();
		return model;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#getModelFilename()
	 */
	@Override
	public String getModelFilename() {
		return modelFilename;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#getNodeMap()
	 */
	@Override
	public RdfNodeMap getNodeMap() {
		return nodeMap;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#getNodeTypeName()
	 */
	@Override
	public String getNodeTypeName() {
		switch (getNodeType()) {
		case RdfTreeNode.LITERAL:
			return "Literal";
		case RdfTreeNode.RESOURCE:
			return "Resource";
		case RdfTreeNode.STATEMENT:
			return "Property";
		case RdfTreeNode.MODEL:
			return "Model";
		case RdfTreeNode.ROOT:
			return "Model";
		}
		return "Unknown";
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#getParentStatement()
	 */
	@Override
	public Statement getParentStatement() {
		return parentStatement;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#getRdfValue()
	 */
	@Override
	public String getRdfValue() {
		switch (getNodeType()) {
		case RdfTreeNode.LITERAL:
			return getRdfNode().toString();
		case RdfTreeNode.RESOURCE:
			return getRdfNode().toString();
		case RdfTreeNode.STATEMENT:
			return (getStatement()).getPredicate().toString();
		case RdfTreeNode.MODEL:
			return getModelFilename();
		case RdfTreeNode.ROOT:
			return getModelFilename();
		}
		return "Unknown";
	}

	/**
	 * Gets the statement.
	 * 
	 * @return the statement
	 */
	public Statement getStatement() {
		// dummy
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#isDirty()
	 */
	@Override
	public boolean isDirty() {
		return dirty;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#isValueEditable()
	 */
	@Override
	public boolean isValueEditable() {
		switch (getNodeType()) {
		case RdfTreeNode.LITERAL:
			return true;
		case RdfTreeNode.RESOURCE:
			if (!((Resource) getRdfNode()).isAnon())
				return true;
			break;
		case RdfTreeNode.STATEMENT:
			return false;
		case RdfTreeNode.MODEL:
			return false;
		case RdfTreeNode.ROOT:
			return false;
		}
		return false;
	}

	/**
	 * Removes the statement.
	 * 
	 * @param statement
	 *            the statement
	 */
	public void removeStatement(Statement statement) {
		try {
			getModel().remove(statement);
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Rename dialog.
	 * 
	 * @param oldName
	 *            the old name
	 * @return the string
	 */
	public String renameDialog(String oldName) {
		System.out.println("RENAME");
		final String input = JOptionPane.showInputDialog(null, "Rename "
				+ oldName, "Rename", JOptionPane.PLAIN_MESSAGE);
		System.out.println("INPUT = " + input);
		return input;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#renameRequest()
	 */
	@Override
	public void renameRequest() {
		final String newName = renameDialog(toString());
		if ((newName == null) || newName.equals(toString()))
			return;
		// getNodeMap().renameNode(this, newName);
		rename(newName);
	}

	/**
	 * Sets the dirty.
	 * 
	 * @param dirty
	 *            The dirty to set
	 */
	@Override
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#setModel(com.hp.hpl.jena.rdf.model.Model)
	 */
	@Override
	public void setModel(Model model) {
		// System.out.println("setting model = " + model);
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#setModelFilename(java.lang.String)
	 */
	@Override
	public void setModelFilename(String string) {
		modelFilename = string;
	}

	/**
	 * Sets the parent statement.
	 * 
	 * @param statement
	 *            the new parent statement
	 */
	public void setParentStatement(Statement statement) {
		parentStatement = statement;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#setRdfValue(java.lang.String)
	 */
	@Override
	public void setRdfValue(String string) {
		// dummy - it can't be done with all types
	}
}
