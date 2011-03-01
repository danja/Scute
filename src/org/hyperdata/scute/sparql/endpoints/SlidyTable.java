/**
 * 
 */
package org.hyperdata.scute.sparql.endpoints;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


/**
 * @author danny
 *
 */
public class SlidyTable extends JTable {

    private static final Color evenColor = new Color(250, 250, 250);
    
	 /**
	 * @param endpointTableModel
	 */
	public SlidyTable(EndpointTableModel endpointTableModel) {
		super(endpointTableModel);
	}

	@Override public Component prepareRenderer(TableCellRenderer tcr, int row, int column) {
         Component c = super.prepareRenderer(tcr, row, column);
         if(isRowSelected(row)) {
             c.setForeground(getSelectionForeground());
             c.setBackground(getSelectionBackground());
         }else{
             c.setForeground(getForeground());
             c.setBackground((row % 2 == 0) ? evenColor : getBackground());
         }
         return c;
     }
}
