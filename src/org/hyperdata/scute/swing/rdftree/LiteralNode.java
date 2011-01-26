package org.hyperdata.scute.swing.rdftree;

import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.tree.TreeNode;

import org.hyperdata.resources.tree.TreeIcons;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;

public class LiteralNode extends AbstractRdfTreeNode implements RdfTreeNode {
	private Literal literal;

	public LiteralNode(RdfNodeMap nodeMap, Statement parentStatement,
			Literal literal) {
		super(nodeMap, parentStatement);
		this.literal = literal;
	}

	public Enumeration children() {
		return null;
	}

	public void delete() {
		getNodeMap().removeLiteral(literal);

		// getNodeMap().removeStatement(getParentStatement());
		// removeStatement(getParentStatement());
		// super.removeFromParent();
	}

	public boolean getAllowsChildren() {
		return false;
	}

	public TreeNode getChildAt(int childIndex) {
		// System.out.println("no kids Literal");
		return null;
	}

	public int getChildCount() {
		return 0;
	}

	public ImageIcon getIcon() {
		return TreeIcons.literalIcon;
	}

	public int getIndex(TreeNode node) {
		return 0;
	}

	public int getNodeType() {
		return RdfTreeNode.LITERAL;
	}

	public TreeNode getParent() {
		// return
		// getNodeMap().getTreeNode(RdfUtils.getParent(getNodeMap().getModel(),
		// literal));
		return getNodeMap().getTreeNode((RDFNode) getParentStatement());
	}

	public RDFNode getRdfNode() {
		return literal;
	}

	public boolean isLeaf() {
		return true;
	}

	public void rename(String newName) {
	}

	@Override
	public void setRdfValue(String value) {
		try {
			literal = getModel().createLiteral(value);
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return literal.toString();
	}
}
