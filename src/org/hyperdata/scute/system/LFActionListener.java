/**
 * 
 */
package org.hyperdata.scute.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * @author danny
 * 
 */
public class LFActionListener implements ActionListener {

	private int index;
	private LookAndFeelInfo[] infos;

	/**
	 * @param infos
	 * @param index
	 * @param container
	 */
	public LFActionListener(LookAndFeelInfo[] infos, int index) {
		this.infos = infos;
		this.index = index;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			UIManager.setLookAndFeel(this.infos[this.index].getClassName());
			SwingUtilities
					.updateComponentTreeUI(((JComponent) arg0.getSource())
							.getTopLevelAncestor());
		} catch (Exception exception) {
			Log.exception(exception);
		}

	}
}
