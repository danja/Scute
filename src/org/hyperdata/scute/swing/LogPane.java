package org.hyperdata.scute.swing;

import javax.swing.JEditorPane;

import org.hyperdata.scute.run.RdfEditor;

public class LogPane extends JEditorPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6012973480836662133L;

	public static LogPane log = null;

	/*
	 * public LogPane() { super(); setContentType("text/html"); }
	 */
	private static StringBuffer text;

	// private Updater updater;

	public static void err(String string) {
		System.err.println(string);
		text.append("<font color='#ff0000'>");
		text.append(string);
		text.append("</font>");
		text.append("<br>");
		log.setText(text.toString());
	}

	public static LogPane getLogPane() {
		if (log == null) {
			log = new LogPane();
		}
		return log;
	}

	public static void println(String string) {
		text.append(string);
		text.append("<br>");
		log.setText(text.toString());
	}

	private LogPane() {
		super();
		setContentType("text/html");
		text = new StringBuffer();
		setEditable(false);
		setBackground(RdfEditor.READ_ONLY_COLOR);
	}

	public void clear() {
		text.setLength(0);
		setText("");
	}

	@Override
	public void setText(String string) {
		super.setText("<html>" + string + "</html>");
	}

	/*
	 * class Updater implements Runnable {
	 * 
	 * public void run() { System.out.println("Hello World on " +
	 * Thread.currentThread()); setText(text); } }
	 */
}
