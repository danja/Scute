package org.hyperdata.scute.graph;

import javax.swing.JButton;
import javax.swing.JComponent;

public class VisibleNode {
	
	// @TODO change to Point2D.Double()  ??
	private double x;
	private double y;

	private double dx;
	private double dy;

	private boolean fixed;

	private String label;

//	private int n = -1;

	protected JButton component;

	public VisibleNode() {
		
	}
	public VisibleNode(JButton component) {
		// component = new JLabel("X", TreeIcons.resourceIcon, JLabel.CENTER);
		this.component = component;
	}

	public JComponent getComponent() {
		return component;
	}

	public void setX(double x) {
		this.x = x;
		component.setLocation((int) this.x - component.getWidth() / 2, (int) y
				- component.getHeight() / 2);
	}

	double getX() {
		return x;
	}

	public void setY(double y) {
		this.y = y;
		component.setLocation((int) x - component.getWidth() / 2, (int) this.y
				- component.getHeight() / 2);
	}

	double getY() {
		return y;
	}

	/**
	 * @param dx
	 *            the dx to set
	 */
	void setDx(double dx) {
		this.dx = dx;
	}

	/**
	 * @return the dx
	 */
	double getDx() {
		return dx;
	}

	/**
	 * @param dy
	 *            the dy to set
	 */
	void setDy(double dy) {
		this.dy = dy;
	}

	/**
	 * @return the dy
	 */
	double getDy() {
		return dy;
	}

	/**
	 * @param fixed
	 *            the fixed to set
	 */
	void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	/**
	 * @return the fixed
	 */
	public boolean isFixed() {
		return fixed;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the label
	 */
	String getLabel() {
		return label;
	}
}
