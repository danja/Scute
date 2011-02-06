/**
 * 
 */
package org.hyperdata.scute.swing.status;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.hyperdata.resources.indicators.IndicatorIcons;
import org.hyperdata.scute.swing.RoundButton;

/**
 * @author danny
 * 
 */
public class StatusButton extends JButton implements StatusChangeListener {

	private int status;

	private String[] description = StatusMonitor.DEFAULT_DESCRIPTION;
	
	/**
	 * @param turtleAction
	 */
	public StatusButton() {
		super();
init();
	}
	
	public StatusButton(Action action) {
		super();
		setAction(action);
		init();
	}
	
	/**
	 * @param turtleAction
	 * @param redDescription
	 *            description for state RED
	 * @param amberDescription
	 *            description for state AMBER
	 * @param greenDescription
	 *            description for state GREEN
	 */
	public StatusButton(Action action, String redDescription, String amberDescription,
			String greenDescription) {
		this(action);
		setDescriptions(redDescription, amberDescription, greenDescription);
		
		if(action != null && action instanceof MouseListener){
			addMouseListener((MouseListener) action);
		}

		Object statusTask = action.getValue("StatusTask");
		if(statusTask != null){
		// 	System.out.println("got StatusTask");
			((StatusMonitor)statusTask).addStatusListener(this);
		}
		setStatus(StatusMonitor.GREEN);
	}
	
	private void init(){
		setContentAreaFilled(false);
		setStatus(StatusMonitor.GREEN);
		setPressedIcon(IndicatorIcons.blueIcon);
	}
	
	@Override
	protected void paintBorder(Graphics g) {
		// do nothing, only want icon
	}


	
	/**
	 * @param redDescription
	 *            description for state RED
	 * @param amberDescription
	 *            description for state AMBER
	 * @param greenDescription
	 *            description for state GREEN
	 */
	public void setDescriptions(String redDescription, String amberDescription,
			String greenDescription) {
		this.description[0] = redDescription;
		this.description[1] = amberDescription;
		this.description[2] = greenDescription;
	}

	public void setStatus(int status) {
		this.status = status;
		setToolTipText(description[status]);
		setIcon(StatusMonitor.ICON[status]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.hyperdata.scute.validate.StatusChangeListener#statusChanged(org.hyperdata
	 * .scute.validate.StatusEvent)
	 */
	@Override
	public void statusChanged(StatusEvent vEvent) {
		setStatus(vEvent.getStatus());
	}
}
