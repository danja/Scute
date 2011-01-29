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

import java.awt.Container;

import javax.swing.JFrame;

/**
 * The Class BasicFrameUI.
 */
public class BasicFrameUI {

	/** The frame. */
	private final JFrame frame;

	/**
	 * Instantiates a new basic frame ui.
	 */
	public BasicFrameUI() {
		frame = new JFrame();
	}

	/**
	 * Instantiates a new basic frame ui.
	 * 
	 * @param content
	 *            the content
	 * @param title
	 *            the title
	 */
	public BasicFrameUI(Container content, String title) {
		this();
		setContentPane(content);
		setTitle(title);
		show();
	}

	/**
	 * Instantiates a new basic frame ui.
	 * 
	 * @param title
	 *            the title
	 */
	public BasicFrameUI(String title) {
		this();
		setTitle(title);
	}

	/**
	 * Sets the content pane.
	 * 
	 * @param content
	 *            the new content pane
	 */
	public void setContentPane(Container content) {
		frame.setContentPane(content);
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		frame.setTitle(title);
	}

	/**
	 * Show.
	 */
	public void show() {
		// frame.getContentPane().add(content);
		System.out.println("show");
		frame.pack();
		// frame.setSize(400, 400);
		frame.setVisible(true);
	}
}
