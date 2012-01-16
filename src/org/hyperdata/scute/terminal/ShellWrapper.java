/**
 * 
 */
package org.hyperdata.scute.terminal;

import java.awt.Color;
import java.io.*;

import org.hyperdata.scute.terminal.ShellWrapper.ShellIn;

import bsh.ConsoleInterface;

/**
 * @author danny
 * 
 */
public class ShellWrapper {

	String shellDir = "/bin";
	String shellCommand = "bash";
	String prompt = "> ";

	private BufferedReader shellInputReader = null;
	private PrintWriter shellWriter = null;
	private JConsole console;
	private BufferedReader shellErrorReader;
	private ShellIn shellIn;

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

public void start(){
	System.out.println("shelly.start");
	shellIn = new ShellIn();
	new Thread(shellIn).start();
}
	
	class ShellIn implements Runnable {
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() { // pushes input from the console to the shell
			// System.out.println("starting shellin");
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

	public JConsole getConsole(){
		return console;
	}
}

class ShellErr implements Runnable {

	private ShellWrapper shelly;

	public ShellErr(ShellWrapper shelly){
		this.shelly = shelly;
	}
	
	public void run() {
		String errorLine = "";
		BufferedReader shellErrorReader = shelly.getShellErrorReader();
		try {
			while ((errorLine = shellErrorReader.readLine()) != null) {
				shelly.getConsole().print(errorLine + "\n", Color.RED);
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}

class ShellToConsole implements Runnable {

	private ShellWrapper shelly;

	public ShellToConsole(ShellWrapper shelly){
		this.shelly = shelly;
	}
	
	public void run() {
		BufferedReader shellInputReader = shelly.getShellInputReader();

		String outLine = "";

		shelly.getConsole().print("Hello!\n", Color.GREEN);
		shelly.getConsole().print(outLine + "\n> ", Color.BLUE);
		try { // reads from the shell and outputs to console
			// shellWriter.write("echo Hello!\n");
			while ((outLine = shellInputReader.readLine()) != null) {
				if (!outLine.equals("")) {
					shelly.getConsole().print(outLine + "\n", Color.BLUE);
				}
				shelly.getConsole().print("> ", Color.BLUE);
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		
	}
	
}
