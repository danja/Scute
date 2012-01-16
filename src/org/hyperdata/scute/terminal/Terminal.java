/**
 * 
 */
package org.hyperdata.scute.terminal;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Terminal {

	static String shellDir = "/bin";
	static String shellCommand = "bash";
	static String prompt = "> ";
	static String greeting = "Hello!\n";
	static String encoding = "UTF-8";
	
	private JConsole jConsole = null;
	private ShellWrapper shelly = null;
	
	public Component getConsole() {
		return jConsole;
	}

	public Terminal() {
		jConsole = new JConsole();
		shelly = new ShellWrapper(jConsole);
	}
	
	public void start(){
		shelly.start();
	}
	
	public void stop(){
		shelly.stop();
	}
	
	public static void main(String[] args) {

		JFrame frame = new JFrame("Terminal");

		final Terminal terminal = new Terminal();
		frame.getContentPane().add(terminal.getConsole());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
		{
		      public void windowClosing(WindowEvent e)
		      {
		          terminal.stop();
		          while(!terminal.isDone()){
		        	  try {
						Thread.sleep(10);
					} catch (InterruptedException exception) {
						exception.printStackTrace();
						System.exit(1);
					}
		          }
		          System.exit(0);
		      }
		});
		
		frame.setSize(600, 400);

		frame.setVisible(true);
		terminal.start();
	}

	/**
	 * @return
	 */
	public boolean isDone() {
		return shelly.isStopped();
	}
}