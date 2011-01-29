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
package org.hyperdata.scute.main;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JFrame;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * The Class SourceDemo.
 */
public class SourceDemo {

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
			exception.printStackTrace();
		}

		// @TODO show source window!!

		final JFrame frame = new JFrame();
		// frame.getContentPane().add(tree);
		frame.pack();
		frame.setVisible(true);
	}
}
