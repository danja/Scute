
package org.hyperdata.scute.system.panels;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;

import org.hyperdata.scute.cards.Card;

/**
 * @author danny
 * 
 * TODO Backup - to remote store, to file
 */
public class SystemPanel extends Card {

	public SystemPanel() {
		super();
		
		// setLayout(new GridLayout(0,1));
		 setLayout(new BoxLayout(this, BoxLayout.Y_AXIS) );
		
		LookFeelPanel lfPanel = new LookFeelPanel();
		TitledBorder lfBorder = BorderFactory.createTitledBorder("Look & Feel");
		lfPanel.setBorder(lfBorder);
		add(lfPanel);
		
		SystemDataPanel sysPanel = new SystemDataPanel();
		TitledBorder sysBorder = BorderFactory.createTitledBorder("System Data");
		sysPanel.setBorder(sysBorder);
		add(sysPanel);
	}
	
	
}
