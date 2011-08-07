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

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hyperdata.scute.main.Scute;

/**
 * The Class NodePanel.
 */
public class NodePanel extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4560694199797851361L;
	
	/** The node type label. */
	private final JLabel nodeTypeLabel;
	
	/** The node value field. */
	private final JTextField nodeValueField;

	/**
	 * Instantiates a new node panel.
	 */
	public NodePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		nodeTypeLabel = new JLabel("Node Type : ");
		System.out.println("NodePanel");
		add(nodeTypeLabel);

		nodeValueField = new JTextField("None Selected.");
		nodeValueField.setBackground(Scute.READ_ONLY_COLOR);
		add(nodeValueField);
	}

	/**
	 * Sets the rdf tree node.
	 * 
	 * @param rdfTreeNode
	 *            the new rdf tree node
	 */
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
