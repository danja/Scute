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

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;

// extends  DynamicTreeTableModel implements TreeTableModel
/**
 * The Class RdfTreeModel.
 */
public class RdfTreeModel extends DefaultTreeModel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2322361720841873199L;
	// private final DefaultTreeModel defaultTreeModel;
	/** The model. */
	private final Model model;
	
	/** The node map. */
	private final RdfNodeMap nodeMap;

	/**
	 * Instantiates a new rdf tree model.
	 * 
	 * @param root
	 *            the root
	 * @param model
	 *            the model
	 * @param nodeMap
	 *            the node map
	 */
	public RdfTreeModel(TreeNode root, Model model, RdfNodeMap nodeMap) {
		super(root);
		// super();
		this.model = model;
		this.nodeMap = nodeMap;
		nodeMap.setTreeModel(this);
		// defaultTreeModel = new DefaultTreeModel(root); // added@@
	}

	/**
	 * Returns the column name passed into the constructor.
	 * 
	 * @param parent
	 *            the parent
	 * @param child
	 *            the child
	 * @return the index of child
	 */

	// @Override
	// public String getColumnName(int column) {
	// // System.out.println("get co.umn name " + column + columnNames[column]);
	// return columnNames[column];
	// }

	// was commented
	@Override
	public int getIndexOfChild(Object parent, Object child) {
		return ((RdfTreeNode) parent).getIndex((RdfTreeNode) child);
	}

	/**
	 * Gets the model.
	 * 
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * Gets the rdf type label.
	 * 
	 * @param node
	 *            the node
	 * @return the rdf type label
	 */
	public String getRdfTypeLabel(Object node) {
		return ((RdfTreeNode) node).getRdfValue();
	}

	/**
	 * Gets the rdf value label.
	 * 
	 * @param node
	 *            the node
	 * @return the rdf value label
	 */
	public String getRdfValueLabel(Object node) {
		return ((RdfTreeNode) node).getNodeTypeName();
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.DefaultTreeModel#getRoot()
	 */
	@Override
	public Object getRoot() {
		return nodeMap.getRoot();
	}

	/**
	 * Gets the tree node.
	 * 
	 * @param rdfNode
	 *            the rdf node
	 * @return the tree node
	 */
	public TreeNode getTreeNode(RDFNode rdfNode) {
		return nodeMap.getTreeNode(rdfNode);
	}

	/**
	 * Checks if is cell editable.
	 * 
	 * @param node
	 *            the node
	 * @param column
	 *            the column
	 * @return true, if is cell editable
	 */
	public boolean isCellEditable(Object node, int column) {
		if (column == 0)
			return true;
		return (column == 2) && ((RdfTreeNode) node).isValueEditable();
	}

	// //////////////// TreeTable Methods //////////////
	/*
	 * public int getColumnCount() { return 2; }
	 * 
	 * public String getColumnName(int column) { return "column" + column; }
	 */
	// ?????
	/*
	 * static protected Class[] cTypes = { TreeTableModel.class, Integer.class,
	 * String.class, Date.class };
	 * 
	 * public Class getColumnClass(int column) { // return
	 * java.lang.String.class; return cTypes[column]; }
	 */

	/*
	 * public Object getValueAt(Object node, int column) { switch (column) {
	 * case 1 : return getRdfValueLabel(node); case 2 : return
	 * getRdfTypeLabel(node); default : return super.getValueAt(node, column); }
	 * }
	 */

	/* (non-Javadoc)
	 * @see javax.swing.tree.DefaultTreeModel#isLeaf(java.lang.Object)
	 */
	@Override
	public boolean isLeaf(Object node) {
		// System.out.println( ((RdfTreeNode) node).isLeaf());
		return ((RdfTreeNode) node).isLeaf();
		// return false;
	}

	/**
	 * Load model.
	 * 
	 * @param model
	 *            the model
	 */
	public void loadModel(Model model) {
		nodeMap.loadModel(model);
		// super.reload();
	}

	/**
	 * Sets the value at.
	 * 
	 * @param value
	 *            the value
	 * @param node
	 *            the node
	 * @param column
	 *            the column
	 */
	public void setValueAt(Object value, Object node, int column) {
		if (column == 0)
			return;
		((RdfTreeNode) node).setRdfValue(value.toString());
	}

	/*
	 * public void setRdfTreeNode(RdfTreeNode rdfTreeNode) { this.rdfTreeNode =
	 * rdfTreeNode; nodeTypeLabel.setText(rdfTreeNode.getNodeTypeName());
	 * nodeValueField.setText(rdfTreeNode.getRdfValue());
	 * 
	 * nodeValueField.setEditable(rdfTreeNode.isTextEditable());
	 * if(rdfTreeNode.isTextEditable()){
	 * nodeValueField.setBackground(Scute.READ_WRITE_COLOR); }else{
	 * nodeValueField.setBackground(Scute.READ_ONLY_COLOR); } }
	 */

	// public boolean isCellEditable(Object node, int column) {
	// return false;
	// }

	// public void setValueAt(Object aValue, Object node, int column) {
	// }

	/*
	 * public Object getChild(Object parent, int index) { // added @@ return
	 * defaultTreeModel.getChild(parent, index); }
	 * 
	 * public int getChildCount(Object parent) { return
	 * defaultTreeModel.getChildCount(parent); }
	 */
	// -------------------
}
