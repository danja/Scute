/**
 * 
 */
package org.hyperdata.scute.sparql.popup;

import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JMenu;

/**
 * @author danny
 *
 */
public class Snippets extends JMenu {

	private ActionListener listener;
	private Map<String, String> map = new TreeMap<String, String>();

	public Snippets(ActionListener listener){
		super("Snippets");
		this.listener = listener;
		populate();
	}

	/**
	 * 
	 */
	private void populate() {
		// JMenuItem 
	}
}
