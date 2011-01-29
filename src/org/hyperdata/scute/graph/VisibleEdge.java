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

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;

/**
 * The Class VisibleEdge.
 */
public class VisibleEdge {

	/** The len. */
	double len;
	
	/** The component. */
	private final JButton component;

	/**
	 * Instantiates a new visible edge.
	 */
	public VisibleEdge() {
		component = new JButton();
		component.setBackground(Color.white);
		component.setBorder(new EmptyBorder(5, 5, 5, 5));
		// component.setContentAreaFilled(false);
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
	 * Sets the center.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public void setCenter(int x, int y) {
		component.setLocation(x - component.getWidth() / 2, y
				- component.getHeight() / 2);
	}
}
