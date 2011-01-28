// originally derived from
// http://java.sun.com/applets/jdk/1.4/demo/applets/GraphLayout/Graph.java

package org.hyperdata.scute.main;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JFrame;

import org.hyperdata.scute.graph.GraphPanel;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class GraphDemo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 117728935219563549L;
	private GraphPanel panel;
	static Model model;

	public static void main(String[] args) {
		String filename = "./data/default.ttl";

		model = ModelFactory.createDefaultModel();

		try {
			final InputStream stream = new FileInputStream(filename);
			model.read(new FileInputStream(filename), "", "N3");
			stream.close();
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		GraphDemo demo = new GraphDemo();
		demo.initialize();
		demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		demo.setSize(800, 800);
		demo.setVisible(true);

	}

	public void initialize() {
		setLayout(new BorderLayout());

		panel = new GraphPanel(model);
		add("Center", panel);
		// panel.initialize();

	}

	public Model getModel() {

		return model;
	}
}
