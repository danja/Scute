
package org.hyperdata.scute.syspane;

import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**
 * @author danny
 * 
 * TODO Backup - to remote store, to file
 */
public class SystemPanel extends JPanel {

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
