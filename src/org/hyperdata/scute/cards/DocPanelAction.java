/**
 * 
 */
package org.hyperdata.scute.cards;

import java.awt.event.ActionEvent;

import org.jdesktop.swingx.action.AbstractActionExt;

import org.hyperdata.scute.main.Scute;

final class DocPanelAction extends
		AbstractActionExt {
//	private String label;

	/**
	 * 
	 */
	private final TaskPanel taskPanel;

	public DocPanelAction(TaskPanel taskPanel, String command, String label) {
		// setName(panelName);
		super(label,command);
	//	this.label = label;
		this.taskPanel = taskPanel;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
    	System.out.println("DocPanelAction AACCTTIIOONN = "+actionEvent);
    	Scute.scuteHelp.show();
    	// CardLayout cLay = cardPanel.getLayout();
    	// cLay.show(cardPanel,"panel1Identifier");
    	
//    	taskPanel.cardPanel.fireChange(actionEvent);
//    	taskPanel.layout.show(this.taskPanel.cardPanel, actionEvent.getActionCommand());
    }
}