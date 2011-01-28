package org.hyperdata.scute.graph.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import org.hyperdata.scute.graph.GraphDiagramPanel;

public class ToggleAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3068370948843461801L;
	private final GraphDiagramPanel diagramPanel;

	public ToggleAction(GraphDiagramPanel panel) {
		super("Scramble");
		diagramPanel = panel;
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() instanceof JButton) { // just to make sure
			JButton button = ((JButton) event.getSource());
			if (button.getText() == "Scramble") {
				button.setText("Freeze");
			} else {
				button.setText("Scramble");
			}
			diagramPanel.setRunning(!diagramPanel.isRunning()); // isSelected
																// available
		}

	}

}
