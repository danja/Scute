package org.hyperdata.scute.swing;

import java.awt.Container;

import javax.swing.JFrame;

public class BasicFrameUI {

	private final JFrame frame;

	public BasicFrameUI() {
		frame = new JFrame();
	}

	public BasicFrameUI(Container content, String title) {
		this();
		setContentPane(content);
		setTitle(title);
		show();
	}

	public BasicFrameUI(String title) {
		this();
		setTitle(title);
	}

	public void setContentPane(Container content) {
		frame.setContentPane(content);
	}

	public void setTitle(String title) {
		frame.setTitle(title);
	}

	public void show() {
		// frame.getContentPane().add(content);
		System.out.println("show");
		frame.pack();
		// frame.setSize(400, 400);
		frame.setVisible(true);
	}
}
