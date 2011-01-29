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
	@SuppressWarnings("unchecked")// javax.swing.tree.TreeNode is old-fashioned
	public Enumeration<?> children() {
		return (new Vector(getChildren())).elements();
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#delete()
	 */
	public void delete() {
		System.out.println("MUST NOT DELETE ROOT NODE");
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
		// System.out.println("root.getChildAt "+childIndex);
		// System.out.println(".getChildAt = "+(TreeNode)
		// getChildren().get(childIndex));
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
				exception.printStackTrace();
			}
		}

		return children;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#getIcon()
	 */
	public ImageIcon getIcon() {
		return TreeIcons.rootIcon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getIndex(TreeNode)
	 */
	public int getIndex(TreeNode node) {
		return getChildren().indexOf(node);
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#getNodeType()
	 */
	public int getNodeType() {
		return RdfTreeNode.ROOT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getParent()
	 */
	public TreeNode getParent() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#getRdfNode()
	 */
	public RDFNode getRdfNode() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#isLeaf()
	 */
	public boolean isLeaf() {
		// System.out.println("rootkids = " + getChildren().size());
		return getChildren().size() == 0;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#rename(java.lang.String)
	 */
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
