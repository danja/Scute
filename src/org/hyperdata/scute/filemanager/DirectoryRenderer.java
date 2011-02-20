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
package org.hyperdata.scute.filemanager;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


/**
 * The Class DirectoryRenderer.
 */
public class DirectoryRenderer extends DefaultTableCellRenderer {
    
    /* (non-Javadoc)
     * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    @Override
	public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {

        if ( value != null && value instanceof Icon ) {
           super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );
           setIcon( (Icon)value );
           setText( "" );
           return this;
        }
        else {
           setIcon( null );
        }

        return super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );
    }
}

