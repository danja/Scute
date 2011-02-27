/**
 * 
 */
package org.hyperdata.scute.window;

import java.awt.event.ActionEvent;

import org.jdesktop.swingx.action.AbstractActionExt;

final class ChangeEditorPanelAction extends
		AbstractActionExt {

	/**
	 * 
	 */
	private final TaskPanel taskPanel;

	public ChangeEditorPanelAction(TaskPanel taskPanel, String command, String label) {
		// setName(panelName);
		super(label,command);
	//	this.label = label;
		this.taskPanel = taskPanel;
	}

	public void actionPerformed(ActionEvent actionEvent) {
    	System.out.println("ChangeEditorPanelAction AACCTTIIOONN = "+actionEvent);
    	// CardLayout cLay = cardPanel.getLayout();
    	// cLay.show(cardPanel,"panel1Identifier");
    	System.out.println("SOURCE="+actionEvent.getSource());
    	taskPanel.cardPanel.fireChange(actionEvent);
    	taskPanel.layout.show(taskPanel.cardPanel, actionEvent.getActionCommand());
    }
}