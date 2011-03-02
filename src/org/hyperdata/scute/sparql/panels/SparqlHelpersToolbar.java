/**
 * 
 */
package org.hyperdata.scute.sparql.panels;

import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * @author danny
 *
 */
public class SparqlHelpersToolbar extends JPanel {

	private JComboBox prefixesBox;

	public SparqlHelpersToolbar(){
		prefixesBox = new JComboBox(new Prefixes());
	}
}
