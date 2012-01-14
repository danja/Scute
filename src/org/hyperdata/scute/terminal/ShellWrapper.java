/**
 * 
 */
package org.hyperdata.scute.terminal;

import java.awt.Color;
import java.io.*;

import bsh.ConsoleInterface;

/**
 * @author danny
 * 
 */
public class ShellWrapper implements Runnable {

	String shellDir = "/bin";
	String shellCommand = "bash";
	String prompt = "> ";

	private BufferedReader shellInputReader = null;
	private PrintWriter shellWriter = null;
	private JConsole console;
	private BufferedReader shellErrorReader;

	public ShellWrapper(JConsole console) {
		this.console = console;
		init();
	}

	public void init() {
		ProcessBuilder processBuilder = new ProcessBuilder(shellCommand);
		processBuilder.directory(new File(shellDir));
		Process shell = null;
		try {
			shell = processBuilder.start();
		} catch (IOException exception1) {
			exception1.printStackTrace();
		}

		// BufferedReader shellIn = new BufferedReader(new InputStreamReader(
		// shell.getInputStream()));

		try {
			shellInputReader = new BufferedReader(new InputStreamReader(
					shell.getInputStream(), "UTF-8"));
		} catch (UnsupportedEncodingException exception) {
			exception.printStackTrace();
		}

		try {
			shellErrorReader = new BufferedReader(new InputStreamReader(
					shell.getErrorStream(), "UTF-8"));
		} catch (UnsupportedEncodingException exception) {
			exception.printStackTrace();
		}

		shellWriter = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(shell.getOutputStream())), true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() { // pushes input from the console to the shell
		Reader input = console.getIn();
		BufferedReader consoleSource = new BufferedReader(input);

		String line = "";

		try {
			while ((line = consoleSource.readLine()) != null) {
				shellWriter.write(line + "\n");
				shellWriter.flush();
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * @return the shellOut
	 */
	public PrintWriter getShellWriter() {
		return this.shellWriter;
	}

	/**
	 * @return the shellIn
	 */
	public BufferedReader getShellInputReader() {
		return this.shellInputReader;
	}

	/**
	 * @return the shellErrorReader
	 */
	public BufferedReader getShellErrorReader() {
		return this.shellErrorReader;
	}

}
