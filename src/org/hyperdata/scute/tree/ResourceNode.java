package org.hyperdata.scute.tree;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.tree.TreeNode;

import org.hyperdata.resources.tree.TreeIcons;
import org.hyperdata.scute.rdf.RdfUtils;

import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class ResourceNode extends AbstractRdfTreeNode implements RdfTreeNode {
	private List children;

	private Resource resource;

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
	public Enumeration children() {
		return (new Vector(getChildren())).elements();
	}

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
	public boolean getAllowsChildren() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getChildAt(int)
	 */
	public TreeNode getChildAt(int childIndex) {
		// System.out.println("\nres.getChildAt " + childIndex);
		// System.out.println(".getChildAt = " +
		// (TreeNode)getChildren().get(childIndex));

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

	public List getChildren() {
		if ((children == null) || isDirty()) {
			children = new ArrayList();

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
				exception.printStackTrace();
			}
		}

		return children;
	}

	public ImageIcon getIcon() {
		return TreeIcons.resourceIcon;
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
		return RdfTreeNode.RESOURCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getParent()
	 */
	public TreeNode getParent() {
		return getNodeMap().getTreeNode(
				RdfUtils.getParent(getModel(), resource));
	}

	public RDFNode getRdfNode() {
		return resource;
	}

	/*
	 * Returns the resource.
	 * 
	 * @return Resource
	 */
	public Resource getResource() {
		return resource;
	}

	public boolean isAnon() {
		return ((Resource) getRdfNode()).isAnon();
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
	public void setRdfValue(String value) {
		if (isAnon())
			return;
		try {
			final Resource newResource = getModel().createResource(value);
			RdfUtils.replaceResource(getModel(), resource, newResource);

		} catch (final Exception exception) {
			exception.printStackTrace();
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

	@Override
	public String toString() {
		return resource.getLocalName();
	}
}
