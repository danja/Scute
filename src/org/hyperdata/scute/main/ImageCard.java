/**
 * 
 */
package org.hyperdata.scute.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import org.hyperdata.resources.scute.ScuteIcons;
import org.hyperdata.scute.cards.Card;

/**
 * @author danny
 * 
 */
public class ImageCard extends Card {

	// private BufferedImage image = ScuteIcons.bigImage;
	private JButton button;

	public ImageCard() {
		button = new JButton(ScuteIcons.bigImageIcon);
		button.setText("");
		button.setBorderPainted(false);
	//	button.setContentAreaFilled(false);
		button.setBackground(Color.white);
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

}
