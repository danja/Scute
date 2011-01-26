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
package org.hyperdata.scute.swing.rdftree;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.tree.TreeNode;

import org.hyperdata.resources.tree.TreeIcons;
import org.hyperdata.scute.rdf.RdfUtils;

import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;

public class StatementNode extends AbstractRdfTreeNode implements RdfTreeNode {
	private List children;
	// private Property property;
	private final Statement statement;

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
	public Enumeration children() {
		return (new Vector(getChildren())).elements();
	}

	public void delete() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getAllowsChildren()
	 */
	public boolean getAllowsChildren() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getChildAt(int)
	 */
	public TreeNode getChildAt(int childIndex) {
		// System.out.println("\nstate.getChildAt " + childIndex);
		// System.out.println(".getChildAt = " + getChildren().get(childIndex));

		return (TreeNode) getChildren().get(childIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getChildCount()
	 */
	public int getChildCount() {
		return getChildren().size();
	}

	private List getChildren() {
		if ((children == null) || isDirty()) {
			children = new ArrayList();

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
				exception.printStackTrace();
			}
		}

		return children;
	}

	public ImageIcon getIcon() {
		return TreeIcons.propertyIcon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getIndex(TreeNode)
	 */
	public int getIndex(TreeNode node) {
		return getChildren().indexOf(node);
	}

	public int getNodeType() {
		return RdfTreeNode.STATEMENT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getParent()
	 */
	public TreeNode getParent() {
		return getNodeMap().getTreeNode(
				RdfUtils.getParent(getModel(), statement.getSubject()));
	}

	public RDFNode getRdfNode() {
		// return (RDFNode)statement.asResource(); // ?? is ok? cast added -
		// change from Jena
		// 1.6
		return statement.getResource();
	}

	@Override
	public Statement getStatement() {
		return statement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#isLeaf()
	 */
	public boolean isLeaf() {
		return getChildren().size() == 0;
	}

	public void rename(String newName) {
	}

	@Override
	public String toString() {
		return statement.getPredicate().getLocalName();
	}
}
