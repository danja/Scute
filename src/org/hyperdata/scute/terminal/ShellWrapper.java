/**
 * 
 */
package org.hyperdata.scute.terminal;

import java.awt.Color;
import java.io.*;

import org.hyperdata.scute.terminal.ShellWrapper.ShellInput;

import bsh.ConsoleInterface;

/**
 * @author danny
 * 
 */
public class ShellWrapper {

	protected JConsole console = null;

	private Thread inputThread = null;
	private Thread outputThread = null;
	private Thread errorThread = null;

	private Process shellProcess = null;

	private BufferedReader shellInputReader = null;
	private BufferedReader shellErrorReader = null;
	private PrintWriter shellWriter = null;

	private boolean keepAlive = true;
	private boolean stopped = false;
	
	private boolean didOutput = false;

	public ShellWrapper(JConsole console) {
		this.console = console;
		initPipes();
		initThreads();
	}

	public void start() {
		keepAlive = true;
		inputThread.start();
		outputThread.start();
		errorThread.start();
	}

	public void stop() {
		keepAlive = false;
		while (!threadsDone()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}
		shellProcess.destroy();
		stopped = true;
	}

	public boolean isStopped() {
		return stopped;
	}

	public void initPipes() {
		ProcessBuilder processBuilder = new ProcessBuilder(
				Terminal.shellCommand);
		processBuilder.directory(new File(Terminal.shellDir));

		try {
			shellProcess = processBuilder.start();
		} catch (IOException exception1) {
			exception1.printStackTrace();
		}

		try {
			shellInputReader = new BufferedReader(new InputStreamReader(
					shellProcess.getInputStream(), "UTF-8"));
		} catch (UnsupportedEncodingException exception) {
			exception.printStackTrace();
		}
		try {
			shellErrorReader = new BufferedReader(new InputStreamReader(
					shellProcess.getErrorStream(), Terminal.encoding));
		} catch (UnsupportedEncodingException exception) {
			exception.printStackTrace();
		}
		shellWriter = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(shellProcess.getOutputStream())), true);
	}
	
	private void initThreads(){
		inputThread = new Thread(new ShellInput());
		outputThread = new Thread(new ShellOutput());
		errorThread = new Thread(new ShellError());
	}

	class ShellInput implements Runnable {

		public void run() { // pushes input from the console to the shell
			Reader input = console.getIn();
			BufferedReader consoleSource = new BufferedReader(input);

			String line = "";

			while (keepAlive) {
				try {
					while ((line = consoleSource.readLine()) != null
							&& keepAlive) {
						shellWriter.write(line + "\n");
						shellWriter.flush();
				//		if(!didOutput){
						console.print(Terminal.prompt, Color.BLUE);
//						}
//						didOutput = false;
					}
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			//	System.out.println("ShellInput dropped through");
				initPipes();
			}
			shellWriter.close();
		}
	}
	
	class ShellOutput implements Runnable {

		public void run() {

			String outLine = "";

			console.print(Terminal.greeting, Color.GREEN);
			console.print(outLine + Terminal.prompt, Color.BLUE);
			while (keepAlive) {
				try { // reads from the shell and outputs to console
					while ((outLine = shellInputReader.readLine()) != null
							&& keepAlive) {
						//if (!outLine.equals("")) {
							console.print(outLine + "\n", Color.BLUE);
							console.print(Terminal.prompt, Color.BLUE);
						//	didOutput = true;
						//}
						// console.print("> ", Color.BLUE);
					}
				} catch (IOException exception) {
					exception.printStackTrace();
				}
				// System.out.println("ShellOutput dropped through");
				initPipes();
				console.print(Terminal.prompt, Color.BLUE);
			}
			try {
				shellInputReader.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}

		}
	}

	class ShellError implements Runnable {

		public void run() {
			String errorLine = "";
			while (keepAlive) {
				try {
					while ((errorLine = shellErrorReader.readLine()) != null
							&& keepAlive) {
						console.print(errorLine + "\n", Color.RED);
					}
				//	System.out.println("dropped out of err");

				} catch (IOException exception) {
					exception.printStackTrace();
				}
			//	System.out.println("ShellError dropped through");
				initPipes();
			}
			try {
				shellErrorReader.close();
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

	public JConsole getConsole() {
		return console;
	}

	/**
	 * @return
	 */
	public boolean threadsDone() {
		return inputThread.isAlive() || outputThread.isAlive()
				|| errorThread.isAlive();
	}
}