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
	
	private BufferedReader shellReader = null;
	private PrintWriter shellWriter = null;
	private JConsole console;
	
	public ShellWrapper(JConsole console){
		this.console = console;
		init();
	}
	
	public void init(){
		ProcessBuilder processBuilder = new ProcessBuilder(shellCommand);
		processBuilder.directory(new File(shellDir));
		Process shell = null;
		try {
			shell = processBuilder.start();
		} catch (IOException exception1) {
			exception1.printStackTrace();
		}

//		BufferedReader shellIn = new BufferedReader(new InputStreamReader(
//				shell.getInputStream()));
		
		try {
			shellReader = new BufferedReader(new InputStreamReader(
					shell.getInputStream(),"UTF-8"));
		} catch (UnsupportedEncodingException exception) {
			exception.printStackTrace();
		}
		
		shellWriter = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(shell.getOutputStream())), true);
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Reader input = console.getIn();
		BufferedReader consoleSource = new BufferedReader(input);
		
		String line = "";
		System.out.println("running");
		while(true){
			try {
			//	System.out.println("before");
				line = consoleSource.readLine();
				shellWriter.write(line+"\n");
				shellWriter.flush();
			//	System.out.println("after");
				
			//	System.out.println(prompt + line);
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		//	console.print(prompt + line, Color.ORANGE);
		//	System.out.println(prompt + line);
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
	public BufferedReader getShellReader() {
		return this.shellReader;
	}

}
