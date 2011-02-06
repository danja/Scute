/**
 * 
 */
package org.hyperdata.scute.swing.status;

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
public class StatusButton extends RoundButton implements StatusChangeListener, MouseListener {

	private int status;

	private String[] description = StatusMonitor.DEFAULT_DESCRIPTION;

	/**
	 * @param redDescription
	 *            description for state RED
	 * @param amberDescription
	 *            description for state AMBER
	 * @param greenDescription
	 *            description for state GREEN
	 */
	public StatusButton(String redDescription, String amberDescription,
			String greenDescription) {
		super();
		this.description[0] = redDescription;
		this.description[1] = amberDescription;
		this.description[2] = greenDescription;
		setStatus(StatusMonitor.GREEN);
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
		
		this(redDescription, amberDescription, greenDescription);
		setAction(action);
		
		Object statusTask = action.getValue("StatusTask");
		if(statusTask != null){
			System.out.println("got StatusTask");
			((StatusMonitor)statusTask).addStatusListener(this);
		}
		setStatus(StatusMonitor.GREEN);
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
	
	/**
	 * 
	 */
	private void handleSingleClick() {
		if(getAction() != null && getAction().getValue("ClickHandler") != null){
			((ClickHandler)getAction().getValue("ClickHandler")).handleSingleClick();
		}
	}

	/**
	 * 
	 */
	private void handleDoubleClick() {
		if(getAction() != null && getAction().getValue("ClickHandler") != null){
			((ClickHandler)getAction().getValue("ClickHandler")).handleSingleClick();
		} 
	}

	// mouse listener handlers
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		if(mouseEvent.getClickCount() == 2){
			handleDoubleClick();
		}
		else handleSingleClick();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// ignore
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// ignore
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// ignore
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
// ignore
	}
}
