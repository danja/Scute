package org.hyperdata.scute.swing.rdftree;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.hyperdata.scute.rdf.RdfUtils;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

// extends DefaultMutableTreeNode 

public abstract class AbstractRdfTreeNode implements RdfTreeNode {

	private boolean dirty = true;
	private Model model;
	private String modelFilename;
	private final RdfNodeMap nodeMap;
	private Statement parentStatement;

	public AbstractRdfTreeNode(RdfNodeMap nodeMap, Statement parentStatement) {
		this.nodeMap = nodeMap;
		this.parentStatement = parentStatement;
		model = nodeMap.getCurrentModel();
		modelFilename = nodeMap.getCurrentModelFilename();
	}

	public boolean deleteDialog() {
		final int n = JOptionPane.showConfirmDialog(null, "Delete "
				+ toString() + "?", "Delete", JOptionPane.YES_NO_OPTION);
		return n == 0;
	}

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

	public Model getModel() {
		// return nodeMap.getCurrentModel();
		return model;
	}

	public String getModelFilename() {
		return modelFilename;
	}

	public RdfNodeMap getNodeMap() {
		return nodeMap;
	}

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

	public Statement getParentStatement() {
		return parentStatement;
	}

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

	public Statement getStatement() {
		// dummy
		return null;
	}

	public boolean isDirty() {
		return dirty;
	}

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

	public void removeStatement(Statement statement) {
		try {
			getModel().remove(statement);
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
	}

	public String renameDialog(String oldName) {
		System.out.println("RENAME");
		final String input = JOptionPane.showInputDialog(null, "Rename "
				+ oldName, "Rename", JOptionPane.PLAIN_MESSAGE);
		System.out.println("INPUT = " + input);
		return input;
	}

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
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public void setModel(Model model) {
		// System.out.println("setting model = " + model);
		this.model = model;
	}

	public void setModelFilename(String string) {
		modelFilename = string;
	}

	public void setParentStatement(Statement statement) {
		parentStatement = statement;
	}

	public void setRdfValue(String string) {
		// dummy - it can't be done with all types
	}
}
