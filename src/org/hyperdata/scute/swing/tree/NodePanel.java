package org.hyperdata.scute.swing.tree;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hyperdata.scute.run.Scute;
import org.hyperdata.scute.swing.rdftree.RdfTreeNode;

public class NodePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4560694199797851361L;
	private final JLabel nodeTypeLabel;
	private final JTextField nodeValueField;

	public NodePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		nodeTypeLabel = new JLabel("Node Type : ");
		System.out.println("NodePanel");
		add(nodeTypeLabel);

		nodeValueField = new JTextField("None Selected.");
		nodeValueField.setBackground(Scute.READ_ONLY_COLOR);
		add(nodeValueField);
	}

	public void setRdfTreeNode(RdfTreeNode rdfTreeNode) {
		nodeTypeLabel.setText(rdfTreeNode.getNodeTypeName());
		nodeValueField.setText(rdfTreeNode.getRdfValue());
		System.out.println("setRdfTreeNode");
		nodeValueField.setEditable(rdfTreeNode.isValueEditable());
		if (rdfTreeNode.isValueEditable()) {
			nodeValueField.setBackground(Scute.READ_WRITE_COLOR);
		} else {
			nodeValueField.setBackground(Scute.READ_ONLY_COLOR);
		}
	}

}
