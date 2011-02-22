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
package org.hyperdata.scute.filemanager.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.hyperdata.scute.filemanager.FileExplorerPanel;
import org.hyperdata.scute.filemanager.FileReference;
import org.hyperdata.scute.tree.RdfTreePanel;

/**
 * The Class CopyAction.
 */
public class SendToSparqlAction extends AbstractAction {

	private FileReference fileReference;

	/**
	 * Instantiates a new copy action.
	 * @param fileExplorerPanel 
	 * 
	 * @param treePanel
	 *            the tree panel
	 */
	public SendToSparqlAction(FileReference fileReference) {
		super("Send To SPARQL");
		this.fileReference = fileReference;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		JOptionPane.showMessageDialog(null, "File is "+fileReference.getCurrentFile().getName());
	}

}
