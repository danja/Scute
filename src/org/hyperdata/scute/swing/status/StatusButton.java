/**
 * 
 */
package org.hyperdata.scute.swing.status;

import java.awt.Graphics;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import org.hyperdata.resources.indicators.IndicatorIcons;

/**
 * The Class StatusButton.
 * 
 * @author danny
 */
public class StatusButton extends JButton implements StatusChangeListener {

	private int status;

	private String[] description = StatusMonitor.DEFAULT_DESCRIPTION;

	private ImageIcon[] icons = StatusMonitor.ICON;

	private String[] label = {"","",""};

	/**
	 * Instantiates a new status button.
	 * 
	 */
	public StatusButton() {
		super();
		init();
	}

	public void setIcons(ImageIcon[] icons) {
		this.icons = icons;
	}

	/**
	 * Instantiates a new status button.
	 * 
	 * @param action
	 *            the action
	 */
	public StatusButton(Action action) {
		super();
		setAction(action);
		init();
	}

	/**
	 * Instantiates a new status button.
	 * 
	 * @param action
	 *            the action
	 * @param redDescription
	 *            description for state RED
	 * @param amberDescription
	 *            description for state AMBER
	 * @param greenDescription
	 *            description for state GREEN
	 */
	public StatusButton(Action action, String redDescription,
			String amberDescription, String greenDescription) {
		this(action);
		setDescriptions(redDescription, amberDescription, greenDescription);

		if (action != null && action instanceof MouseListener) {
			addMouseListener((MouseListener) action);
		}

setAction(action);
		setStatus(StatusMonitor.GREEN);
	}

	/**
	 * @param action
	 */
	public void setAction(Action action) {
		Object statusTask = action.getValue("StatusTask");
		if (statusTask != null) {
			// System.out.println("got StatusTask");
			((StatusMonitor) statusTask).addStatusListener(this);
		}
		super.setAction(action);
	}

	/**
	 * @param runQueryAction
	 * @param string
	 * @param string2
	 * @param string3
	 * @param icons2
	 */
	public StatusButton(Action action, String redDescription,
			String amberDescription, String greenDescription, ImageIcon[] icons) {
		this(action, redDescription, amberDescription, greenDescription);
		this.icons = icons;
	}
	
	public StatusButton(Action action, String[] labels, String[] descriptions, ImageIcon[] icons) {
		super();
		setAction(action);
		init();
		this.label = labels;
		this.description = descriptions;
		this.icons = icons;
	}

	/**
	 * @param runQueryAction
	 * @param labels
	 * @param descriptions
	 */
	public StatusButton(Action action, String[] labels,
			String[] descriptions) {
		// this(action);
		this.description = descriptions;
		this.label = labels;
		setAction(action);
		init();
	}

	/**
	 * Inits the.
	 */
	private void init() {
		
		setContentAreaFilled(false);
		setStatus(StatusMonitor.GREEN);
		setPressedIcon(IndicatorIcons.blueIcon);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.AbstractButton#paintBorder(java.awt.Graphics)
	 */
	@Override
	protected void paintBorder(Graphics g) {
		// do nothing, only want icon
	}

	/**
	 * Sets the descriptions.
	 * 
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
	
	public void setLabels(String redLabel, String amberLabel,
			String greenLabel) {
		this.label[0] = redLabel;
		this.label[1] = amberLabel;
		this.label[2] = greenLabel;
	}
	

	/**
	 * Sets the status.
	 * 
	 * @param status
	 *            the new status
	 */
	public void setStatus(int status) {
		this.status = status;
		// System.out.println(status);
		setToolTipText(description[status]);
		setIcon(icons[status]);
		setText(label[status]);
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
