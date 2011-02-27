/**
 * 
 */
package org.hyperdata.scute.sparql;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import org.jdesktop.swingx.JXTitledPanel;

import org.hyperdata.scute.source.HighlighterEditorKit;

/**
 * @author danny
 * 
 * TODO make SPARQL SELECT/CONSTRUCT/DESCRIBE?/ASK? template from current working model
 *
 */
public class SparqlPanel extends JXTitledPanel {

	public SparqlPanel(){
		super();
		super.setLayout(new BorderLayout());
		super.setTitle("SPARQL"); //??/ where is this
		
		SparqlToolbar toolbar = new SparqlToolbar();
		add(toolbar, BorderLayout.NORTH);
		
		SparqlSourcePanel sourcePanel = new SparqlSourcePanel("SPARQL");
		sourcePanel.setEditorKit(new HighlighterEditorKit("SPARQL"));
		
		String text = "SELECT ?s ?p ?o WHERE {\n   ?s ?p ?o \n}";
		sourcePanel.setText(text);
		
		SparqlResultsPanel resultPanel = new SparqlResultsPanel();
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sourcePanel, resultPanel);
		splitPane.setContinuousLayout(true);
		add(splitPane, BorderLayout.CENTER); 
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SparqlPanel sparqlPanel = new SparqlPanel();
		final JFrame frame = new JFrame();
		frame.setSize(800,600);
		frame.add(sparqlPanel);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	//	frame.pack();
		frame.setVisible(true);
	}

}
