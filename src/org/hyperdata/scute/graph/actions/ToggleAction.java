package org.hyperdata.scute.graph.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import org.hyperdata.scute.graph.GraphDiagramPanel;
import org.hyperdata.scute.tree.RdfTreePanel;

public class ToggleAction extends AbstractAction {

	private GraphDiagramPanel diagramPanel;

	public ToggleAction(GraphDiagramPanel panel) {
		super("Scramble");
		this.diagramPanel = panel;
	}

	public void actionPerformed(ActionEvent event) {
		if(event.getSource() instanceof JButton){ // just to make sure
		JButton button = ((JButton)event.getSource());
		if(button.getText() == "Scramble"){
			button.setText("Freeze");
		} else {
			button.setText("Scramble");
		}
		diagramPanel.setRunning(!diagramPanel.isRunning()); // isSelected available
		}
	
			
		
	}

}
