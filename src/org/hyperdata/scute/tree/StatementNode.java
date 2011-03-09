/**
 * PropertyTreeNode.java
 * Idea
 * @version version 1.0
 *
 * @author     Danny Ayers
 * @created    04-Dec-2002
 *
 * Copyright (c) 2002 D.Ayers
 * All rights reserved.
 *
 * For license details see http://ideagraph.net/licenses
 */
package org.hyperdata.scute.tree;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.tree.TreeNode;

import org.hyperdata.resources.tree.TreeIcons;
import org.hyperdata.scute.rdf.RdfUtils;
import org.hyperdata.scute.system.Log;

import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;

/**
 * The Class StatementNode.
 */
public class StatementNode extends AbstractRdfTreeNode implements RdfTreeNode {
	
	/** The children. */
	private List<TreeNode> children;
	// private Property property;
	/** The statement. */
	private final Statement statement;

	/**
	 * Instantiates a new statement node.
	 * 
	 * @param nodeMap
	 *            the node map
	 * @param statement
	 *            the statement
	 */
	public StatementNode(RdfNodeMap nodeMap, Statement statement) {
		super(nodeMap, statement);
		this.statement = statement;

		// setTreeModel(treeModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#children()
	 */
	@Override
	@SuppressWarnings("unchecked")// javax.swing.tree.TreeNode is old-fashioned
	public Enumeration<?> children() {
		return (new Vector(getChildren())).elements();
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#delete()
	 */
	@Override
	public void delete() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getAllowsChildren()
	 */
	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getChildAt(int)
	 */
	@Override
	public TreeNode getChildAt(int childIndex) {
		// System.out.println("\nstate.getChildAt " + childIndex);
		// System.out.println(".getChildAt = " + getChildren().get(childIndex));

		return getChildren().get(childIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getChildCount()
	 */
	@Override
	public int getChildCount() {
		return getChildren().size();
	}

	/**
	 * Gets the children.
	 * 
	 * @return the children
	 */
	private List<TreeNode> getChildren() {
		if ((children == null) || isDirty()) {
			children = new ArrayList<TreeNode>();

			try {
				/*
				 * NodeIterator iterator =
				 * nodeMap.getModel().listObjectsOfProperty(
				 * statement.getPredicate()); while (iterator.hasNext()) {
				 * children.add( nodeMap.getTreeNode((RDFNode)
				 * iterator.next())); }
				 */
				children.add(getNodeMap().getTreeNode(statement.getObject()));
			} catch (final Exception exception) {
				Log.exception(exception);
			}
		}

		return children;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#getIcon()
	 */
	@Override
	public ImageIcon getIcon() {
		return TreeIcons.propertyIcon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getIndex(TreeNode)
	 */
	@Override
	public int getIndex(TreeNode node) {
		return getChildren().indexOf(node);
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#getNodeType()
	 */
	@Override
	public int getNodeType() {
		return RdfTreeNode.STATEMENT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getParent()
	 */
	@Override
	public TreeNode getParent() {
		return getNodeMap().getTreeNode(
				RdfUtils.getParent(getModel(), statement.getSubject()));
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#getRdfNode()
	 */
	@Override
	public RDFNode getRdfNode() {
		// return (RDFNode)statement.asResource(); // ?? is ok? cast added -
		// change from Jena
		// 1.6
		return statement.getResource();
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.AbstractRdfTreeNode#getStatement()
	 */
	@Override
	public Statement getStatement() {
		return statement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#isLeaf()
	 */
	@Override
	public boolean isLeaf() {
		return getChildren().size() == 0;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#rename(java.lang.String)
	 */
	@Override
	public void rename(String newName) {
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return statement.getPredicate().getLocalName();
	}
}
