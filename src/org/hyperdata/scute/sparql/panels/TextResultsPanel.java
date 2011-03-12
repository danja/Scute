/**
 * 
 */
package org.hyperdata.scute.sparql.panels;

import java.awt.Font;

import javax.swing.JEditorPane;
import javax.swing.event.ChangeEvent;

import org.hyperdata.scute.source.EditorPane;

/**
 * @author danny
 *
 */
public class TextResultsPanel extends JEditorPane {
	public TextResultsPanel(){
		super();
		setFont(new Font("Monospaced", Font.PLAIN, 12));
	}
}
