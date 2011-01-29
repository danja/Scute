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

import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.tree.TreeNode;

import org.hyperdata.resources.tree.TreeIcons;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

/**
 * The Class LiteralNode.
 */
public class LiteralNode extends AbstractRdfTreeNode implements RdfTreeNode {
	
	/** The literal. */
	private Literal literal;

	/**
	 * Instantiates a new literal node.
	 * 
	 * @param nodeMap
	 *            the node map
	 * @param parentStatement
	 *            the parent statement
	 * @param literal
	 *            the literal
	 */
	public LiteralNode(RdfNodeMap nodeMap, Statement parentStatement,
			Literal literal) {
		super(nodeMap, parentStatement);
		this.literal = literal;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#children()
	 */
	public Enumeration<Resource> children() { // @TODO change to Iterator<Resource> ?
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#delete()
	 */
	public void delete() {
		getNodeMap().removeLiteral(literal);

		// getNodeMap().removeStatement(getParentStatement());
		// removeStatement(getParentStatement());
		// super.removeFromParent();
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getAllowsChildren()
	 */
	public boolean getAllowsChildren() {
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getChildAt(int)
	 */
	public TreeNode getChildAt(int childIndex) {
		// System.out.println("no kids Literal");
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getChildCount()
	 */
	public int getChildCount() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#getIcon()
	 */
	public ImageIcon getIcon() {
		return TreeIcons.literalIcon;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getIndex(javax.swing.tree.TreeNode)
	 */
	public int getIndex(TreeNode node) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#getNodeType()
	 */
	public int getNodeType() {
		return RdfTreeNode.LITERAL;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getParent()
	 */
	public TreeNode getParent() {
		// return
		// getNodeMap().getTreeNode(RdfUtils.getParent(getNodeMap().getModel(),
		// literal));
		return getNodeMap().getTreeNode((RDFNode) getParentStatement());
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#getRdfNode()
	 */
	public RDFNode getRdfNode() {
		return literal;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#isLeaf()
	 */
	public boolean isLeaf() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.RdfTreeNode#rename(java.lang.String)
	 */
	public void rename(String newName) {
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.tree.AbstractRdfTreeNode#setRdfValue(java.lang.String)
	 */
	@Override
	public void setRdfValue(String value) {
		try {
			literal = getModel().createLiteral(value);
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return literal.toString();
	}
}
