/**
 * 
 */
package org.hyperdata.scute.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import org.hyperdata.resources.scute.ScuteIcons;
import org.hyperdata.scute.cards.Card;

/**
 * @author danny
 * 
 */
public class ImageCard extends Card implements ActionListener, KeyListener {

	// private BufferedImage image = ScuteIcons.bigImage;
	private JButton button;
	private Scute scute;

	public ImageCard(Scute scute) {
		this.scute = scute;
		button = new JButton(ScuteIcons.bigImageIcon);
		button.setText("");
		button.setBorderPainted(false);
		// button.setContentAreaFilled(false);
		button.setBackground(Color.white);
		button.addActionListener(this);
		
		button.addKeyListener(this); // bleah, doesn't work

		
		add(button);
	}

	// @Override
	// public void paintComponent(Graphics g) {
	// int height = image.getHeight();
	// int width = image.getWidth();
	// int panelWidth = getWidth();
	// int panelHeight = getHeight();
	// int x = (panelWidth-width)/2;
	// int y = (panelHeight-height)/2;
	// g.setColor(Color.white);
	// g.fillRect(0, 0, panelWidth, panelHeight);
	// g.drawImage(image, x, y, width, height, Color.white, null);
	// }

	/**
	 * @param splitButtons
	 */
	public void addActionListener(ActionListener listener) {
		button.addActionListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		initScreen();
	}

	/**
	 * 
	 */
	private void initScreen() {
		scute.setDefaultSplit();
		scute.showTools(true);
		scute.showStatusBar(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		initScreen();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		// ignore
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
// ignore
	}

}
