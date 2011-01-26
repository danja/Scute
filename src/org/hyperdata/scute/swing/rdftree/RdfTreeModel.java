package org.hyperdata.scute.swing.rdftree;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;

// extends  DynamicTreeTableModel implements TreeTableModel
public class RdfTreeModel extends DefaultTreeModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2322361720841873199L;
	private final DefaultTreeModel defaultTreeModel;
	private final Model model;
	private final RdfNodeMap nodeMap;

	public RdfTreeModel(TreeNode root, Model model, RdfNodeMap nodeMap) {
		super(root);
		// super();
		this.model = model;
		this.nodeMap = nodeMap;
		nodeMap.setTreeModel(this);
		defaultTreeModel = new DefaultTreeModel(root); // added@@
	}

	/**
	 * Returns the column name passed into the constructor.
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

	public Model getModel() {
		return model;
	}

	public String getRdfTypeLabel(Object node) {
		return ((RdfTreeNode) node).getRdfValue();
	}

	public String getRdfValueLabel(Object node) {
		return ((RdfTreeNode) node).getNodeTypeName();
	}

	@Override
	public Object getRoot() {
		return nodeMap.getRoot();
	}

	public TreeNode getTreeNode(RDFNode rdfNode) {
		return nodeMap.getTreeNode(rdfNode);
	}

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

	@Override
	public boolean isLeaf(Object node) {
		// System.out.println( ((RdfTreeNode) node).isLeaf());
		return ((RdfTreeNode) node).isLeaf();
		// return false;
	}

	public void loadModel(Model model) {
		nodeMap.loadModel(model);
		// super.reload();
	}

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
	 * nodeValueField.setBackground(RdfEditor.READ_WRITE_COLOR); }else{
	 * nodeValueField.setBackground(RdfEditor.READ_ONLY_COLOR); } }
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
