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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;



/**
 * The Class FileExplorer.
 */
public class FileExplorerPanel extends JPanel {
    

	public FileExplorerPanel(){
		super(new BorderLayout());
		 FileSystemModel model = new FileSystemModel(".");
		 DirectoryListModel directoryModel = new DirectoryListModel((File)model.getRoot());
		 JList dirList = new JList(directoryModel);
		 
		 ListItemRenderer renderer = new ListItemRenderer();
		 dirList.setCellRenderer(renderer);
		 ListMouseListener  listMouseListener = new ListMouseListener();
		 dirList.addMouseListener(listMouseListener);
		 
		 
	        FileSystemTreePanel fileTree = new FileSystemTreePanel(model);
	        fileTree.getTree().addTreeSelectionListener(new TreeListener(directoryModel));

	        JScrollPane treeScroller = new JScrollPane(fileTree);
	   //     JScrollPane tableScroller = new JScrollPane(table);
	        JScrollPane listScroller = new JScrollPane(dirList);
	        treeScroller.setMinimumSize(new Dimension(5, 0));
	      //  tableScroller.setMinimumSize(new Dimension(5, 0));
	     //   tableScroller.setBackground(Color.white);
	        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
	                                               treeScroller,
	                                               listScroller);
	        splitPane.setContinuousLayout(true);
	        add(splitPane, BorderLayout.CENTER);
	}
	
    /**
     * The main method.
     *
     * @param argv the arguments
     */
 

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
        DirectoryListModel model;

        /**
         * Instantiates a new tree listener.
         *
         */
        public TreeListener(DirectoryListModel model) {
            this.model = model;
        }
        
        /* (non-Javadoc)
         * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
         */
        @Override
		public void valueChanged(TreeSelectionEvent e) {
            File fileSysEntity = (File)e.getPath().getLastPathComponent();
            if (fileSysEntity.isDirectory()) {
                model.setDirectory(fileSysEntity);
            }
            else {
                model.setDirectory(null);
            }
        }
    }
}
