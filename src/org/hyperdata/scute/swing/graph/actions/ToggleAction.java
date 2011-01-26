package org.hyperdata.scute.swing.graph.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import org.hyperdata.scute.swing.graph.GraphDiagramPanel;
import org.hyperdata.scute.swing.tree.RdfTreePanel;

public class ToggleAction extends AbstractAction {

	private GraphDiagramPanel panel;

	public ToggleAction(GraphDiagramPanel panel) {
		super("Scramble");
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent event) {
		if(event.getSource() instanceof JButton){ // just to make sure
		JButton button = ((JButton)event.getSource());
		if(button.getText() == "Scramble"){
			button.setText("Freeze");
		} else {
			button.setText("Scramble");
		}
		panel.setRunning(!panel.isRunning()); // isSelected available
		}
	
			
		
	}

}
