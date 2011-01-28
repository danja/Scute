/**
 * ResourceTreeCellRenderer.java
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

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultTreeCellRenderer;

//import com.idea.graphic.diagram.model.Diagram;

/**
 * @author danny
 * 
 *         created : 04-Dec-2002
 * 
 *         (c) D.Ayers 2002
 */
public class RdfTreeCellRenderer extends DefaultTreeCellRenderer implements
		TableCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1253672782232872047L;

	public RdfTreeCellRenderer() {
		// tutorialIcon = new ImageIcon("images/middle.gif");
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		/*
		 * getTreeCellRendererComponent( null, value, isSelected, false, false,
		 * row, hasFocus);
		 */
		initProperties(value);
		return this;
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);
		initProperties(value);

		// } else {
		// setToolTipText(null); //no tool tip
		// }
		return this;
	}

	public void initProperties(Object value) {

		// setBackground(Diagram.DARK_BLUE);

		// setBackgroundNonSelectionColor(Diagram.DARK_BLUE);
		// setBackgroundSelectionColor(Diagram.DARK_MID_BLUE);

		// setForeground(Color.yellow);

		if (value instanceof RdfTreeNode) {
			setIcon(((RdfTreeNode) value).getIcon());
		}
		if (value instanceof ResourceNode) {
			setToolTipText("Resource");
		}

		if (value instanceof StatementNode) {
			setToolTipText("Property");
		}

		if (value instanceof LiteralNode) {
			setToolTipText("Literal");
		}

	}

}
