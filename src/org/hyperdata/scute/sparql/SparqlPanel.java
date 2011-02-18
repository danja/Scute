/**
 * 
 */
package org.hyperdata.scute.sparql;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author danny
 *
 */
public class SparqlPanel extends JPanel {

	public SparqlPanel(){
		super(new BorderLayout());
		setSize(800,600);
		SparqlToolbar toolbar = new SparqlToolbar();
		add(toolbar, BorderLayout.NORTH);
		SparqlSourcePanel sourcePanel = new SparqlSourcePanel();
		add(sourcePanel, BorderLayout.CENTER); 
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SparqlPanel sparqlPanel = new SparqlPanel();
		final JFrame frame = new JFrame();
		frame.getContentPane().add(sparqlPanel);
		frame.pack();
		frame.setVisible(true);
	}

}
