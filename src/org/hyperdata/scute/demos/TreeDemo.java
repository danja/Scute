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
package org.hyperdata.scute.demos;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.ToolTipManager;

import org.hyperdata.scute.system.Log;
import org.hyperdata.scute.tree.RdfNodeMap;
import org.hyperdata.scute.tree.RdfTreeCellRenderer;
import org.hyperdata.scute.tree.RdfTreeModel;
import org.hyperdata.scute.tree.RootNode;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * The Class TreeDemo.
 */
public class TreeDemo {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		final String filename = "./data/sample2.ttl";

		final Model model = ModelFactory.createDefaultModel();

		try {
			final InputStream stream = new FileInputStream(filename);
			model.read(new FileInputStream(filename), "", "N3");
			stream.close();
		} catch (final Exception exception) {
			Log.exception(exception);
		}
		final RdfNodeMap nodeMap = new RdfNodeMap(model);
		final RootNode root = new RootNode(nodeMap);
		root.setModel(model);
		nodeMap.interpret();
		/*
		 * tree.getSelectionModel().setSelectionMode
		 * (TreeSelectionModel.SINGLE_TREE_SELECTION);
		 */
		final RdfTreeModel treeModel = new RdfTreeModel(root, model, nodeMap);
		final JTree tree = new JTree(treeModel);
		tree.putClientProperty("JTree.lineStyle", "Angled");
		ToolTipManager.sharedInstance().registerComponent(tree);
		tree.setCellRenderer(new RdfTreeCellRenderer());

		final JFrame frame = new JFrame();
		frame.getContentPane().add(tree);
		frame.pack();
		frame.setVisible(true);
	}
}
