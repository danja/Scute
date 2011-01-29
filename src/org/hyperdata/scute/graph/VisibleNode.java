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
package org.hyperdata.scute.graph;

import javax.swing.JButton;
import javax.swing.JComponent;

/**
 * The Class VisibleNode.
 * 
 * @TODO add popup menu
 * 
 */
public class VisibleNode {

	// @TODO change to Point2D.Double() ??
	/** The x. */
	private double x = 0;
	
	/** The y. */
	private double y = 0;

	/** The dx. */
	private double dx = 0;
	
	/** The dy. */
	private double dy = 0;

	/** The fixed. */
	private boolean fixed = false;

	/** The label. */
	private String label = "";

	/** The component. */
	protected JButton component = null;

	/**
	 * Instantiates a new visible node.
	 */
	public VisibleNode() {}

	/**
	 * Instantiates a new visible node.
	 * 
	 * @param component
	 *            the component
	 */
	public VisibleNode(JButton component) {
		this.component = component;
	}

	/**
	 * Gets the component.
	 * 
	 * @return the component
	 */
	public JComponent getComponent() {
		return component;
	}

	/**
	 * Sets the x.
	 * 
	 * @param x
	 *            the new x
	 */
	public void setX(double x) {
		this.x = x;
		component.setLocation((int) this.x - component.getWidth() / 2, (int) y
				- component.getHeight() / 2);
	}

	/**
	 * Gets the x.
	 * 
	 * @return the x
	 */
	double getX() {
		return x;
	}

	/**
	 * Sets the y.
	 * 
	 * @param y
	 *            the new y
	 */
	public void setY(double y) {
		this.y = y;
		component.setLocation((int) x - component.getWidth() / 2, (int) this.y
				- component.getHeight() / 2);
	}

	/**
	 * Gets the y.
	 * 
	 * @return the y
	 */
	double getY() {
		return y;
	}

	/**
	 * Sets the dx.
	 * 
	 * @param dx
	 *            the dx to set
	 */
	void setDx(double dx) {
		this.dx = dx;
	}

	/**
	 * Gets the dx.
	 * 
	 * @return the dx
	 */
	double getDx() {
		return dx;
	}

	/**
	 * Sets the dy.
	 * 
	 * @param dy
	 *            the dy to set
	 */
	void setDy(double dy) {
		this.dy = dy;
	}

	/**
	 * Gets the dy.
	 * 
	 * @return the dy
	 */
	double getDy() {
		return dy;
	}

	/**
	 * Sets the fixed.
	 * 
	 * @param fixed
	 *            the fixed to set
	 */
	void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	/**
	 * Checks if is fixed.
	 * 
	 * @return the fixed
	 */
	public boolean isFixed() {
		return fixed;
	}

	/**
	 * Sets the label.
	 * 
	 * @param label
	 *            the label to set
	 */
	void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Gets the label.
	 * 
	 * @return the label
	 */
	String getLabel() {
		return label;
	}
}
