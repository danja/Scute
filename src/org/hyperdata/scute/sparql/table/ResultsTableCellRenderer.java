/**
 * 
 */
package org.hyperdata.scute.sparql.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * @author danny
 * 
 */
public class ResultsTableCellRenderer extends JEditorPane implements TableCellRenderer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax
	 * .swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// 
		String text = String.valueOf(value);
		setText(text);
		// table.getRowMargin()
		
//		int thisHeight = getPreferredSize().height;
//		if (thisHeight > table.getRowHeight(row)) {
//			table.setRowHeight(row, getPreferredSize().height);
//		}
//		this.setWrapStyleWord(true);                    
//        this.setLineWrap(true); 
		
        int fontHeight = getFontMetrics(getFont()).getHeight();
     
//       int textLength = text.length();
       int lines = text.split("<br/>").length +1;
//                             
      int height = fontHeight * lines;    // (table.getRowMargin())+       
        
    //   int height = getPreferredSize().height;
       table.setRowHeight(row, height);
		return this;
	}

	// public int getPreferredRowHeight(JTable table, int rowIndex, int margin)
	// {
	// // Get the current default height for all rows
	// int height = table.getRowHeight();
	//
	// // Determine highest cell in the row
	// for (int c=0; c<table.getColumnCount(); c++) {
	// TableCellRenderer renderer = table.getCellRenderer(rowIndex, c);
	// Component comp = table.prepareRenderer(renderer, rowIndex, c);
	// int h = comp.getPreferredSize().height + 2*margin;
	// height = Math.max(height, h);
	// }
	// return height;
	// }

}
