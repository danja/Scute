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
import java.awt.Dimension;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import org.hyperdata.scute.cards.Card;
import org.hyperdata.scute.filemanager.actions.SendToSparqlAction;

/**
 * The Class FileExplorer.
 */
public class FileExplorerPanel extends Card implements FileReference {

	@Override
	public File getCurrentFile() {
		return this.currentFile;
	}

	@Override
	public void setCurrentFile(File currentFile) {
		this.currentFile = currentFile;
	}

	private File currentFile;
	
	public FileExplorerPanel(String startPath) {
		super(new BorderLayout());
		FilesTreeModel treeModel = new FilesTreeModel(startPath);
		DirListModel directoryModel = new DirListModel((File) treeModel.getRoot());
		JList dirList = new JList(directoryModel);

		ListItemRenderer renderer = new ListItemRenderer();
		dirList.setCellRenderer(renderer);

		FilesTreePanel fileTree = new FilesTreePanel(treeModel);
		
		fileTree.getTree().addTreeSelectionListener(new TreeListener(this,directoryModel));
		fileTree.getTree().setSelectionRow(0);
		
		ListMouseListener listMouseListener = new ListMouseListener(this, fileTree.getTree());
		dirList.addMouseListener(listMouseListener);
		
		JScrollPane treeScroller = new JScrollPane(fileTree);
		JScrollPane listScroller = new JScrollPane(dirList);
		treeScroller.setMinimumSize(new Dimension(5, 0));
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScroller, listScroller);
		splitPane.setContinuousLayout(true);
		add(splitPane, BorderLayout.CENTER);
		add(getButtonBar(), BorderLayout.SOUTH);
	}
	
//	public File getSelected(){
//		return null;
//	}

	private JPanel getButtonBar() {
		JPanel panel = new JPanel();
		JButton button = new JButton("Send To SPARQL Editor");
		button.setAction(new SendToSparqlAction(this));
		panel.add(button);
		return panel;
	}
}
