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
package org.hyperdata.scute.graph.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.hyperdata.scute.graph.GraphDiagramPanel;

/**
 * The Class ToggleAction.
 */
public class ToggleAction extends AbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3068370948843461801L;
	
	/** The diagram panel. */
	private final GraphDiagramPanel diagramPanel;



	/**
	 * Instantiates a new toggle action.
	 * 
	 * @param panel
	 *            the panel
	 */
	public ToggleAction(GraphDiagramPanel panel) {
		super("Scramble");
		diagramPanel = panel;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() instanceof JButton) { // just to make sure
			JButton button = ((JButton) event.getSource());
			if (button.getText() == "Scramble") {
				button.setText("Freeze");
			} else {
				button.setText("Scramble");
			}
		diagramPanel.setRunning(!diagramPanel.isRunning()); 
		}

	}

}
