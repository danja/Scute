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
package org.hyperdata.scute.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;

/**
 * The Class RoundButton.
 */
public class RoundButton extends JButton {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5609834220696804796L;

	// private boolean circle = false;

	// public RoundButton(String label) {
	// super(label);
	//
	// // These statements enlarge the button so that it
	// // becomes a circle rather than an oval.
	//
	// // This call causes the JButton not to paint
	// // the background.
	// // This allows us to paint a round background.
	// setContentAreaFilled(false);
	// }

	/**
	 * Instantiates a new round button.
	 */
	public RoundButton() {
		setContentAreaFilled(false);
	}

	/**
	 * Sets the circular.
	 */
	public void setCircular() {
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
	}

	// Paint the round background and label.
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
			// You might want to make the highlight color
			// a property of the RoundButton class.
			g.setColor(Color.red);
		} else {
			g.setColor(getBackground());
		}
		g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

		// This call will paint the label and the
		// focus rectangle.
		super.paintComponent(g);
	}

	// Paint the border of the button using a simple stroke.
	/* (non-Javadoc)
	 * @see javax.swing.AbstractButton#paintBorder(java.awt.Graphics)
	 */
	@Override
	protected void paintBorder(Graphics g) {
		// g.setColor(getForeground());
		// g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
	}

	// Hit detection.
	/** The shape. */
	Shape shape;

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#contains(int, int)
	 */
	@Override
	public boolean contains(int x, int y) {
		// If the button has changed size,
		// make a new shape object.
		if ((shape == null) || !shape.getBounds().equals(getBounds())) {
			shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
		}
		return shape.contains(x, y);
	}
}
