package org.hyperdata.scute.main;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.ToolTipManager;

import org.hyperdata.scute.tree.RdfNodeMap;
import org.hyperdata.scute.tree.RdfTreeCellRenderer;
import org.hyperdata.scute.tree.RdfTreeModel;
import org.hyperdata.scute.tree.RootNode;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class TreeDemo {

	public static void main(String[] args) {

		final String filename = "./data/sample2.ttl";

		final Model model = ModelFactory.createDefaultModel();

		try {
			final InputStream stream = new FileInputStream(filename);
			model.read(new FileInputStream(filename), "", "N3");
			stream.close();
		} catch (final Exception exception) {
			exception.printStackTrace();
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
		frame.show();
	}
}
