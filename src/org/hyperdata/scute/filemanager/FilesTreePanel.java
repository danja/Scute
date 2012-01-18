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

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeSelectionModel;

import java.io.File;



/**
 * The Class FileSystemTreePanel.
 */
public class FilesTreePanel extends JPanel {
    private JTree tree;

    /**
     * Instantiates a new file system tree panel.
     *
     * @param startPath the start path
     */
    public FilesTreePanel(String startPath) {
        this( new FilesTreeModel(startPath) );
    }

    public void addTreeSelectionListener(TreeSelectionListener listener){
    	tree.addTreeSelectionListener(listener);
    }
    /**
     * Instantiates a new file system tree panel.
     *
     * @param model the model
     */
    public FilesTreePanel(FilesTreeModel model) {
        tree = new JTree(model) {       
            @Override
			public String convertValueToText(Object value, boolean selected,
                                             boolean expanded, boolean leaf, int row,
                                             boolean hasFocus) {
                return ((File)value).getName();
            }
        };

        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        //tree.setLargeModel( true );        
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
      //  tree.putClientProperty( "JTree.lineStyle", "Angled" );

        setLayout(new BorderLayout());
        add(tree, BorderLayout.CENTER);
    }

    /**
     * Gets the tree.
     *
     * @return the tree
     */
    public JTree getTree() {
       return tree;
    }
}