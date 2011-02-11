/*
 * Scute
 * 
 * Homepage: http://hyperdata.org/scute
 * 
 * License : http://www.apache.org/licenses/LICENSE-2.0
 * See also license.txt or http://hyperdata.org/wiki/Scute:License
 * 
 * Danny Ayers 2011
 */
package org.hyperdata.scute.syspane;

import javax.swing.JEditorPane;

import org.hyperdata.scute.main.Scute;

/**
 * The Class LogPane.
 */
public class LogPane extends JEditorPane {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6012973480836662133L;

	/** The log. */
	public static LogPane log = null;

	/*
	 * public LogPane() { super(); setContentType("text/html"); }
	 */
	/** The text. */
	private static StringBuffer text;

	// private Updater updater;

	/**
	 * Err.
	 * 
	 * @param string
	 *            the string
	 */
	public static void err(String string) {
		System.err.println(string);
		text.append("<font color='#ff0000'>");
		text.append(string);
		text.append("</font>");
		text.append("<br>");
		log.setText(text.toString());
	}

	/**
	 * Gets the log pane.
	 * 
	 * @return the log pane
	 */
	public static LogPane getLogPane() {
		if (log == null) {
			log = new LogPane();
		}
		return log;
	}

	/**
	 * Println.
	 * 
	 * @param string
	 *            the string
	 */
	public static void println(String string) {
		text.append(string);
		text.append("<br>");
		log.setText(text.toString());
	}

	/**
	 * Instantiates a new log pane.
	 */
	private LogPane() {
		super();
		setContentType("text/html");
		text = new StringBuffer();
		setEditable(false);
		setBackground(Scute.READ_ONLY_COLOR);
	}

	/**
	 * Clear.
	 */
	public void clear() {
		text.setLength(0);
		setText("");
	}

	/* (non-Javadoc)
	 * @see javax.swing.JEditorPane#setText(java.lang.String)
	 */
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
