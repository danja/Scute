/**
 * 
 */
package org.hyperdata.scute.syntax;

/**
 * @author danny
 *
 */
import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class ScalableEditorPane extends JEditorPane {

	public String getSyntax() {
		return this.syntax;
	}

	JComboBox zoomCombo = new JComboBox(new String[] { "50%", "75%", "100%",
			"150%", "200%" });

	private String syntax = "Turtle"; // reasonable default

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ScalableEditorPane scaledTextPane = new ScalableEditorPane();
		scaledTextPane.getDocument().putProperty("i18n", Boolean.FALSE);
		scaledTextPane.getDocument()
				.putProperty("ZOOM_FACTOR", new Double(1.5));
		JScrollPane scroll = new JScrollPane(scaledTextPane);
		frame.getContentPane().add(scroll);
		frame.getContentPane()
				.add(scaledTextPane.zoomCombo, BorderLayout.NORTH);

		frame.setSize(600, 200);
		frame.setVisible(true);
	}

	public ScalableEditorPane() {
		super();

		final SimpleAttributeSet attrs = new SimpleAttributeSet();
		StyleConstants.setFontSize(attrs, 16);

		// setEditorKit(new ScuteEditorKit());
		// StyledDocument doc=(StyledDocument)ScaledTextPane.this.getDocument();
		// doc.setCharacterAttributes(0,1,attrs,true);
		// try {
		// StyleConstants.setFontFamily(attrs,"Lucida Sans");
		// doc.insertString(0, "Lusida Sans font test\n", attrs);
		//
		// StyleConstants.setFontFamily(attrs,"Lucida Bright");
		// doc.insertString(0, "Lucida Bright font test\n", attrs);
		//
		// StyleConstants.setFontFamily(attrs,"Lucida Sans Typewriter");
		// doc.insertString(0, "Lucida Sans Typewriter font test\n", attrs);
		// }
		// catch (BadLocationException ex) {
		// }

		// zoomCombo.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// String s = (String) zoomCombo.getSelectedItem();
		// s = s.substring(0, s.length() - 1);
		// double scale = new Double(s).doubleValue() / 100;
		// ScaledTextPane.this.getDocument().putProperty("ZOOM_FACTOR",new
		// Double(scale));
		//
		// try {
		// StyledDocument doc=(StyledDocument)ScaledTextPane.this.getDocument();
		// doc.setCharacterAttributes(0,1,attrs,true);
		// doc.insertString(0, "", null); //refresh
		// }
		// catch (Exception ex) {
		// ex.printStackTrace();
		// }
		// }
		// });
		// zoomCombo.setSelectedItem("150%");
	}

	public void repaint(int x, int y, int width, int height) {
		super.repaint(0, 0, getWidth(), getHeight());
	}

	/**
	 * @param syntax
	 */
	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

}
