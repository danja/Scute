/**
 * 
 */
package org.hyperdata.scute.filemanager;

import java.awt.Color;
import java.awt.Component;
import javax.swing.plaf.metal.MetalIconFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.hyperdata.resources.scute.ScuteIcons;

/**
 * @author danny
 * 
 */
public class ListItemRenderer extends JLabel implements ListCellRenderer {

	private Icon dirIcon;
	private Icon fileIcon;

	public ListItemRenderer() {
		super();
//		dirIcon = ScuteIcons.rdfIcon;
//		fileIcon = ScuteIcons.sparqlIcon;
//		System.out.println(UIManager.get("FileChooser.directoryIcon"));
//		dirIcon = (Icon)UIManager.get("FileChooser.directoryIcon");
//		fileIcon = (Icon)UIManager.get("FileChooser.fileIcon");
		dirIcon = MetalIconFactory.getTreeFolderIcon();
		fileIcon = MetalIconFactory.getTreeLeafIcon();
		setOpaque(true);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing
	 * .JList, java.lang.Object, int, boolean, boolean)
	 */
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		File file = (File) value;
		setText(file.getName());
if(file.isDirectory()){
	setIcon(dirIcon);
}else{
	setIcon(fileIcon);
}
if (isSelected) {
	setBackground(list.getSelectionBackground());
	setForeground(list.getSelectionForeground());
} else {
	setBackground(list.getBackground());
	setForeground(list.getForeground());
}
//		if (isSelected) {
//			setBackground(Color.WHITE);
//			setForeground(Color.DARK_GRAY);
//		} else {
//			setBackground(Color.DARK_GRAY);
//			setForeground(Color.WHITE);
//		}

		return this;
	}
}
