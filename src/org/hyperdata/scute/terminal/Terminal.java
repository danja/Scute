/**
 * 
 */
package org.hyperdata.scute.terminal;

import java.awt.Color;
import java.awt.Component;
import java.io.*;

import javax.swing.JFrame;

import org.apache.commons.lang3.StringEscapeUtils;

import bsh.util.GUIConsoleInterface;

public class Terminal implements Runnable {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Terminal");

		Terminal terminal = new Terminal();
		frame.getContentPane().add(terminal.getConsole());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);

		frame.setVisible(true);
		terminal.init();
	}

	/**
	 * @return
	 */
	private Component getConsole() {
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
	private void init() {

		shelly = new ShellWrapper(jConsole);
		// PrintWriter shellWriter = shelly.getShellWriter();
		BufferedReader shellInputReader = shelly.getShellInputReader();

		new Thread(shelly).start();
		new Thread(this).start();

		String outLine = "";

		jConsole.print("Hello!\n", Color.GREEN);
		jConsole.print(outLine + "\n> ", Color.BLUE);

		try { // reads from the shell and outputs to console
			// shellWriter.write("echo Hello!\n");
			while ((outLine = shellInputReader.readLine()) != null) {
				if (!outLine.equals("")) {
					jConsole.print(outLine + "\n", Color.BLUE);
				}
				jConsole.print("> ", Color.BLUE);
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Print prompt and echos commands entered via the JConsole
	 * 
	 * @param jConsole
	 *            a GUIConsoleInterface which in addition to basic input and
	 *            output also provides coloured text output and name completion
	 * @param prompt
	 *            text to display before each input line
	 */
	public void run() {
		String errorLine = "";
		BufferedReader shellErrorReader = shelly.getShellErrorReader();
		try {
			while ((errorLine = shellErrorReader.readLine()) != null) {
				jConsole.print(errorLine + "\n", Color.RED);
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}