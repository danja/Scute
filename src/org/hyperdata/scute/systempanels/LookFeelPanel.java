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

package org.hyperdata.scute.systempanels;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

// import com.jgoodies.looks.plastic.theme.DesertBluer; ??

/**
 * The Class LookFeelPanel.
 *
 * @author danny
 */
public class LookFeelPanel extends JPanel {

	public static void main(String[] args){
		UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
		for(int i=0;i<infos.length;i++){
			System.out.println(infos[i]);
		}
		System.out.println("current = "+UIManager.getLookAndFeel().getClass().getName());
	}
	
	/**
	 * Instantiates a new look feel panel.
	 */
	public LookFeelPanel() {
		super();
		
		final UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
		setLayout(new GridLayout(infos.length, 0));
		JRadioButton[] buttons = new JRadioButton[infos.length];
		ButtonGroup group = new ButtonGroup();
		
		String currentLFClassName = UIManager.getLookAndFeel().getClass().getName();
		
		for(int i=0;i<infos.length;i++){
			buttons[i] = new JRadioButton(infos[i].getName());
			if(currentLFClassName.equals(infos[i].getClassName())){
				buttons[i].setSelected(true);
			}
			buttons[i].addActionListener(new LFActionListener(infos, i));			
			group.add(buttons[i]);
			add(buttons[i]);
		}
	}
}
