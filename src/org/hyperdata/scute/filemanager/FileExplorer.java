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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;


/**
 * The Class FileExplorer.
 */
public class FileExplorer {
    
    /**
     * The main method.
     *
     * @param argv the arguments
     */
    public static void main( String[] argv ) {
        JFrame frame = new JFrame( "File Explorer" );

        frame.addWindowListener( new WindowAdapter() {
                                     @Override
									public void windowClosing( WindowEvent e ) {
                                         System.exit( 0 );
                                     }
                                 });

        FileSystemModel model = new FileSystemModel(".");
        DirectoryModel directoryModel = new DirectoryModel( (File)model.getRoot() );
        JTable table = new JTable( directoryModel );
        table.setShowHorizontalLines( false );
        table.setShowVerticalLines( false );
        table.setIntercellSpacing( new Dimension( 0, 2 ) );
        table.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        table.getColumn( "Type" ).setCellRenderer( new DirectoryRenderer() );
        table.getColumn( "Type" ).setMaxWidth( 32 );
        table.getColumn( "Type" ).setMinWidth( 32 );

        FileSystemTreePanel fileTree = new FileSystemTreePanel( model );
        fileTree.getTree().addTreeSelectionListener( new TreeListener( directoryModel ) );

        JScrollPane treeScroller = new JScrollPane( fileTree );
      //  JScrollPane tableScroller = JTable.createScrollPaneForTable( table );
        JScrollPane tableScroller = new JScrollPane( table );
        treeScroller.setMinimumSize( new Dimension( 0, 0 ) );
        tableScroller.setMinimumSize( new Dimension( 0, 0 ) );
        tableScroller.setBackground( Color.white );
        JSplitPane splitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT,
                                               treeScroller,
                                               tableScroller );
        splitPane.setContinuousLayout( true );

        frame.getContentPane().add( splitPane );

        frame.setSize( 400, 400 );
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * The listener interface for receiving tree events.
     * The class that is interested in processing a tree
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addTreeListener<code> method. When
     * the tree event occurs, that object's appropriate
     * method is invoked.
     *
     * @see TreeEvent
     */
    protected static class TreeListener implements TreeSelectionListener {
        DirectoryModel model;

        /**
         * Instantiates a new tree listener.
         *
         * @param mdl the mdl
         */
        public TreeListener( DirectoryModel mdl ) {
            model = mdl;
        }
        
        /* (non-Javadoc)
         * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
         */
        @Override
		public void valueChanged( TreeSelectionEvent e ) {
            File fileSysEntity = (File)e.getPath().getLastPathComponent();
            if ( fileSysEntity.isDirectory() ) {
                model.setDirectory( fileSysEntity );
            }
            else {
                model.setDirectory( null );
            }
        }
    }
}
