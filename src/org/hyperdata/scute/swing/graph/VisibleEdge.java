package org.hyperdata.scute.swing.graph;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class VisibleEdge {

	double len;
	private final JButton component;

	private int n = -1;

	public VisibleEdge() {
		component = new JButton();
		component.setBackground(Color.white);
		component.setBorder(new EmptyBorder(5, 5, 5, 5));
		// component.setContentAreaFilled(false);
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public JComponent getComponent() {
		return component;
	}

	public void setCenter(int x, int y) {
		component.setLocation(x-component.getWidth()/2, y-component.getHeight()/2);
	}
}
