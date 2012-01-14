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

		// define a frame and add a console to it
		JFrame frame = new JFrame("JConsole example");

		Terminal ce = new Terminal();

		frame.getContentPane().add(ce.getjConsole());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);

		frame.setVisible(true);
		ce.init();
		// inputLoop(console, "JCE (type 'quit' to exit): ");

		// System.exit(0);
	}

	/**
	 * @return
	 */
	private Component getjConsole() {
		return jConsole;
	}

	JConsole jConsole = new JConsole();
	String prompt = "\n> ";

	public Terminal() {
	}

	/**
	 * 
	 */
	private void init() {

		String line = "";
		ShellWrapper shelly = new ShellWrapper(jConsole);
		PrintWriter shellWriter = shelly.getShellWriter();
		BufferedReader shellReader = shelly.getShellReader();
		 new Thread(shelly).start();
		 
		Reader input = jConsole.getIn();
	
//		try {
//			input = new InputStreamReader(jConsole.getInputStream(), "UTF8");
//		} catch (UnsupportedEncodingException exception1) {
//			// TODO Auto-generated catch block
//			exception1.printStackTrace();
//		}
		
		
		 BufferedReader consoleSource = new BufferedReader(input);
while(true){
		try {
			System.out.println("A");
		//	shellWriter.write("\n" + "pwd" + "\n");
//			 if ((line = consoleSource.readLine()) != null) { // from KB
		//	while (true) {
			
//				shellWriter.write("\n" + line + "\n");
				shellWriter.flush();
//				System.out.println("B");
//
//				System.out.println(prompt + line);
//
				String outLine = "";
//				System.out.println("C");
				if ((outLine = shellReader.readLine()) != null) {
					System.out.println(line);
					jConsole.print(outLine + "\n", Color.BLUE);
					System.out.println("*");
				}
				System.out.println("D");

		} catch (IOException exception) {
			exception.printStackTrace();
		}
}

		// //////////////////////////////////////////////////////////////////////////
		// THIS GETS DATA
		// try {
		// int c = 0;
		// char[] chars = new char[10];
		// while ((x = input.read()) != -1) {
		// chars[c++] = (char)x;
		// // if(c == Character.getNumericValue("\\")) {
		// // c = 0;
		// // System.out.print(chars);
		// // }
		// System.out.print(new Character((char)x));
		// // System.out.print(Character.toChars(x).length);
		// // System.out.print((char)x);
		//
		// // System.out.print(new String(x, "UTF8"));
		// // out.print(Character.toChars(x));
		// }
		// } catch (IOException exception) {
		// exception.printStackTrace();
		// }
		// //////////////////////////////////////////////////////////////////////////////

		// try {
		// line = bufInput.readLine();
		// InputStream is = jConsole.getInputStream();
		// // BufferedInputStream b = new BufferedInputStream(is);
		//
		// Reader ir = new InputStreamReader(is);
		// BufferedReader bufInput = new BufferedReader(ir);
		// //
		// String newline = System.getProperty("line.separator");
		// System.out.println("herew");
		// // Console console = System.console();
		//
		// // System.out.println(console);
		//
		// jConsole.print(prompt, Color.BLUE);
		// System.out.flush();
		// System.err.flush();
		// String line = "";
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
		//
		// while(true){
		// // try {
		// // while ((line = bufInput.readLine()) != null) { //
		// bufInput.readLine()
		// try {
		// line = bufInput.readLine();
		// } catch (IOException exception1) {
		// // TODO Auto-generated catch block
		// exception1.printStackTrace();
		// }
		// System.out.println("LINE" + line);
		// jConsole.print("You typed: " + line + newline, Color.ORANGE);
		//
		// // try to sync up the console
		// // System.out.flush();
		// // System.err.flush();
		// // Thread.yield(); // this helps a little
		// try {
		// Thread.sleep(100);
		// } catch (InterruptedException exception) {
		// // TODO Auto-generated catch block
		// exception.printStackTrace();
		// }
		// if (line.equals("quit"))
		// break;
		// jConsole.print(prompt, Color.BLUE);
		//
		// // bufInput.close();
		//
		// // catch (IOException e) {
		// // e.printStackTrace();
		// // }
		//
		// }
	}
}