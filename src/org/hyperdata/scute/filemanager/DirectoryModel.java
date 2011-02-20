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

import java.io.File;

import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;

/**
 * The Class DirectoryModel.
 */
public class DirectoryModel extends AbstractTableModel {
    protected File directory;
    protected String[] children;
    protected int rowCount;
    protected Object dirIcon;
    protected Object fileIcon;

    /**
     * Instantiates a new directory model.
     */
    public DirectoryModel() {
        init();
    }

    /**
     * Instantiates a new directory model.
     *
     * @param dir the dir
     */
    public DirectoryModel( File dir ) {
        init();
        directory = dir;
        children = dir.list();
        rowCount = children.length;
    }

    /**
     * Inits the.
     */
    protected void init() {
        dirIcon = UIManager.get( "DirectoryPane.directoryIcon" );
        fileIcon = UIManager.get( "DirectoryPane.fileIcon" );
    }

    /**
     * Sets the directory.
     *
     * @param dir the new directory
     */
    public void setDirectory( File dir ) {
        if ( dir != null ) {
            directory = dir;
            children = dir.list();
            rowCount = children.length;
        }
        else {
            directory = null;
            children = null;
            rowCount = 0;
        }
        fireTableDataChanged();
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getRowCount()
     */
    @Override
	public int getRowCount() {
        return children != null ? rowCount : 0;
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    @Override
	public int getColumnCount() {
        return children != null ? 3 :0;
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    @Override
	public Object getValueAt(int row, int column){
        if ( directory == null || children == null ) {
            return null;
        }

        File fileSysEntity = new File( directory, children[row] );

        switch ( column ) {
        case 0:
            return fileSysEntity.isDirectory() ? dirIcon : fileIcon;
        case 1:
            return fileSysEntity.getName();
        case 2:
            if ( fileSysEntity.isDirectory() ) {
                return "--";
            }
            else {
                return new Long( fileSysEntity.length() );
            }
        default:
            return "";
        }
    }

    /* (non-Javadoc)
     * @see javax.swing.table.AbstractTableModel#getColumnName(int)
     */
    @Override
	public String getColumnName( int column ) {
        switch ( column ) {
        case 0:
            return "Type";
        case 1:
            return "Name";
        case 2:
            return "Bytes";
        default:
            return "unknown";
        }
    }

    /* (non-Javadoc)
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    @Override
	public Class getColumnClass( int column ) {
        if ( column == 0 ) {
            return getValueAt( 0, column).getClass();
        }
        else {
            return super.getColumnClass( column );
        }
    }
}                   

