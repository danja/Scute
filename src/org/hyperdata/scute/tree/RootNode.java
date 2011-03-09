/**
 * RootNode.java
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
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.tree.TreeNode;

import org.hyperdata.resources.tree.TreeIcons;
import org.hyperdata.scute.system.Log;

import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * The Class RootNode.
 * 
 * @author danny
 * 
 *         created : 04-Dec-2002
 * 
 *         (c) D.Ayers 2002
 */
public class RootNode extends AbstractRdfTreeNode implements RdfTreeNode {

	/** The children. */
	private List<ResourceNode> children;

	/**
	 * Instantiates a new root node.
	 * 
	 * @param nodeMap
	 *            the node map
	 */
	public RootNode(RdfNodeMap nodeMap) {
		super(nodeMap, null);
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
		System.out.println("MUST NOT DELETE ROOT NODE");
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
		// System.out.println("root.getChildAt "+childIndex);
		// System.out.println(".getChildAt = "+(TreeNode)
		// getChildren().get(childIndex));
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
	protected List<ResourceNode> getChildren() {
		// System.out.println("GET children base root node");
		if (children == null) {
			try {
				children = new ArrayList<ResourceNode>();

				getNodeMap().getTopModel();
				final Iterator<Resource> iterator = getNodeMap().getResources()
						.iterator();
				Resource resource;

				while (iterator.hasNext()) {
					resource =  iterator.next();

					children.add(getNodeMap().getResourceNode(resource));
				}
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
		return TreeIcons.rootIcon;
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
		return RdfTreeNode.ROOT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getParent()
	 */
	@Override
	public TreeNode getParent() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#getRdfNode()
	 */
	@Override
	public RDFNode getRdfNode() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#isLeaf()
	 */
	@Override
	public boolean isLeaf() {
		// System.out.println("rootkids = " + getChildren().size());
		return getChildren().size() == 0;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#rename(java.lang.String)
	 */
	@Override
	public void rename(String newName) {
		System.out.println("MUST NOT RENAME ROOT NODE");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "";
	}
}
