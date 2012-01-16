/**
 * 
 */
package org.hyperdata.scute.terminal;

import java.awt.Component;
import java.io.*;

import javax.swing.JFrame;

import org.apache.commons.lang3.StringEscapeUtils;

import bsh.util.GUIConsoleInterface;

public class Terminal {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Terminal");

		Terminal terminal = new Terminal();
		frame.getContentPane().add(terminal.getConsole());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);

		frame.setVisible(true);
		terminal.init();
		terminal.start();
	}

	/**
	 * @return
	 */
	public Component getConsole() {
		return jConsole;
	}

	ShellWrapper shelly = null;
	JConsole jConsole = new JConsole();
	String prompt = "\n> ";

	public Terminal() {
	}

	/**
	 * 
	 */
	public void init() {
		shelly = new ShellWrapper(jConsole);
	}
	
	public void start(){
		//new Thread(shelly.).start();
		System.out.println("terminal.start");
		shelly.start();
		new Thread(new ShellToConsole(shelly)).start();
		new Thread(new ShellErr(shelly)).start();
	}
}