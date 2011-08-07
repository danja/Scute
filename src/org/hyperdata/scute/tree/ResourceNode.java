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
import org.hyperdata.scute.system.Log;

import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

/**
 * The Class ResourceNode.
 */
public class ResourceNode extends AbstractRdfTreeNode implements RdfTreeNode {
	
	/** The children. */
	private List<TreeNode> children;

	/** The resource. */
	private Resource resource;

	/**
	 * Instantiates a new resource node.
	 * 
	 * @param nodeMap
	 *            the node map
	 * @param parentStatement
	 *            the parent statement
	 * @param resource
	 *            the resource
	 */
	public ResourceNode(RdfNodeMap nodeMap, Statement parentStatement,
			Resource resource) {
		super(nodeMap, parentStatement);
		this.resource = resource;
		// setTreeModel(treeModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#children()
	 */
	@Override
	@SuppressWarnings("unchecked") // javax.swing.tree.TreeNode is old-fashioned
	public Enumeration<?> children() {
		return (new Vector(getChildren())).elements();
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#delete()
	 */
	@Override
	public void delete() {
		getNodeMap().removeResource(resource);
		if (getParentStatement() != null) {
			getNodeMap().removeStatement(getParentStatement());
			removeStatement(getParentStatement());
		}
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
		// System.out.println(".getChildAt = " +
		// (TreeNode)getChildren().get(childIndex));

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
	public List<TreeNode> getChildren() {
		if ((children == null) || isDirty()) {
			children = new ArrayList<TreeNode>();

			try {
				// System.out.println("GRET class= "+ getClass());
				// System.out.println("GRET MODEL = "+ getModel());

				final StmtIterator iterator = getModel().listStatements();
				Statement statement;

				while (iterator.hasNext()) {
					// System.out.println("*");
					statement = iterator.next();

					if (resource.equals(statement.getSubject())) {
						// System.out.println("\n+");
						// System.out.println(
						// "ST = " + getNodeMap().getTreeNode(statement));

						// (RDFNode)statement
						children.add(getNodeMap().getTreeNode(statement));
					}
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
		return TreeIcons.resourceIcon;
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
		return RdfTreeNode.RESOURCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getParent()
	 */
	@Override
	public TreeNode getParent() {
		return getNodeMap().getTreeNode(
				RdfUtils.getParent(getModel(), resource));
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#getRdfNode()
	 */
	@Override
	public RDFNode getRdfNode() {
		return resource;
	}

	/*
	 * Returns the resource.
	 * 
	 * @return Resource
	 */
	/**
	 * Gets the resource.
	 * 
	 * @return the resource
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * Checks if is anon.
	 * 
	 * @return true, if is anon
	 */
	public boolean isAnon() {
		return ((Resource) getRdfNode()).isAnon();
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
	 * @see org.hyperdata.scute.tree.AbstractRdfTreeNode#setRdfValue(java.lang.String)
	 */
	@Override
	public void setRdfValue(String value) {
		if (isAnon())
			return;
		try {
			final Resource newResource = getModel().createResource(value);
			RdfUtils.replaceResource(getModel(), resource, newResource);

		} catch (final Exception exception) {
			Log.exception(exception);
		}
	}

	/**
	 * Sets the resource.
	 * 
	 * @param resource
	 *            The resource to set
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return resource.getLocalName();
	}
}
