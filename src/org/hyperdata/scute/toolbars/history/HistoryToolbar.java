package org.hyperdata.scute.toolbars.history;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.hyperdata.resources.general.GeneralIcons;
import org.hyperdata.resources.scute.ScuteIcons;

/**
 * NOT CURRENTLY USED
 * 
 * @author danny
 *
 */
public class HistoryToolbar extends JPanel {


	public HistoryToolbar(){
		Action previousAction = new PreviousAction();
		JButton previousButton = new JButton(previousAction);
		previousButton.setIcon(ScuteIcons.previousIcon);
		previousButton.setHideActionText(true);
		previousButton.setToolTipText("Previous");
		add(previousButton);
		
		Action nextAction = new NextAction();
		JButton nextButton = new JButton(nextAction);
		nextButton.setIcon(ScuteIcons.nextIcon);
		nextButton.setHideActionText(true);
		nextButton.setToolTipText("Next");
		add(nextButton);
	}
}
