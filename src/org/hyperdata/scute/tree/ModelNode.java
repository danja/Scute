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

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.tree.TreeNode;

import org.hyperdata.resources.tree.TreeIcons;
import org.hyperdata.scute.rdf.RdfUtils;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

/**
 * The Class ModelNode.
 */
public class ModelNode extends ResourceNode implements RdfTreeNode {
	
	/** The children. */
	private List<TreeNode> children;
	
	/** The model. */
	private final Model model;
	
	/** The node map. */
	private RdfNodeMap nodeMap;

	/** The resource. */
	private Resource resource;

	/**
	 * Instantiates a new model node.
	 * 
	 * @param nodeMap
	 *            the node map
	 * @param model
	 *            the model
	 * @param parentStatement
	 *            the parent statement
	 * @param resource
	 *            the resource
	 */
	public ModelNode(RdfNodeMap nodeMap, Model model,
			Statement parentStatement, Resource resource) {
		super(nodeMap, parentStatement, resource); // @@TODO
		this.resource = resource;
		this.nodeMap = nodeMap;
		this.model = model;

		// String filename = RdfUtils_.getFirstPropertyValue(resource,
		// FILESYSTEM.local).toString();
		// setTreeModel(treeModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#children()
	 * 
	 */
	@Override
	@SuppressWarnings("unchecked") // javax.swing.tree.TreeNode is old-fashioned
	public Enumeration<?> children() { // Can make Iterator<type>? what type?
		return (new Vector(getChildren())).elements(); // Can make Array<type>? what type? 
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.ResourceNode#delete()
	 */
	@Override
	public void delete() {
		/*
		 * String filename = RdfUtils .getFirstPropertyValue(resource,
		 * FILESYSTEM.local) .toString(); Model childModel =
		 * RdfUtils.load(filename);
		 * 
		 * getNodeMap().removeResource(resource); if (getParentStatement() !=
		 * null) { getNodeMap().removeStatement(getParentStatement());
		 * removeStatement(getParentStatement()); }
		 */
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
		// System.out.println("\nres.getChildAt " + childIndex);

		// System.out.println("MODEL.getChildAt = " +
		// (TreeNode)getChildren().get(childIndex));

		return (TreeNode) getChildren().get(childIndex);
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

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.ResourceNode#getChildren()
	 */
	@Override
	public List<TreeNode> getChildren() {
		if ((children == null) || isDirty()) {
			children = new ArrayList<TreeNode>();

			try {
				final StmtIterator iterator = model.listStatements();
				Statement statement;
				// RdfUtils_.show(model);
				while (iterator.hasNext()) {
					// System.out.println("*");
					statement = iterator.next();

					// if(resource.equals(statement.getSubject())) {
					// System.out.println("\n+");
					// System.out.println(
					// "ST = " + nodeMap.getTreeNode(statement));

					// @@ changed for Jena 2, was (RDFNode)statement
					children.add(nodeMap.getTreeNode(statement));
					// }
				}
			} catch (final Exception exception) {
				exception.printStackTrace();
			}
		}

		return children;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.ResourceNode#getIcon()
	 */
	@Override
	public ImageIcon getIcon() {
		return TreeIcons.modelIcon;
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

	/**
	 * Returns the nodeMap.
	 * 
	 * @return NodeMap
	 */
	@Override
	public RdfNodeMap getNodeMap() {
		return nodeMap;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.ResourceNode#getNodeType()
	 */
	@Override
	public int getNodeType() {
		return RdfTreeNode.MODEL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getParent()
	 */
	@Override
	public TreeNode getParent() {
		return nodeMap.getTreeNode(RdfUtils.getParent(getModel(), resource));
	}

	/**
	 * Returns the resource.
	 * 
	 * @return Resource
	 */
	@Override
	public Resource getResource() {
		return resource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#isLeaf()
	 */
	@Override
	public boolean isLeaf() {
		// return getChildren().size() > 0;
		return getChildren().size() == 0;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.ResourceNode#rename(java.lang.String)
	 */
	@Override
	public void rename(String newName) {
	}

	/**
	 * Sets the nodeMap.
	 * 
	 * @param nodeMap
	 *            The nodeMap to set
	 */
	public void setNodeMap(RdfNodeMap nodeMap) {
		this.nodeMap = nodeMap;
	}

	/**
	 * Sets the resource.
	 * 
	 * @param resource
	 *            The resource to set
	 */
	@Override
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.ResourceNode#toString()
	 */
	@Override
	public String toString() {
		return resource.getLocalName();
	}
}
