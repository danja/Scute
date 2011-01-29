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
package org.hyperdata.scute.swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.theme.DesertBluer;

/**
 * The Class WindowKit.
 */
public class WindowKit {

	/**
	 * Uses Color.white as the background color, and the name of the Container's
	 * class as the JFrame title.
	 * 
	 * @param content
	 *            the content
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @return the j frame
	 */

	public static JFrame openInJFrame(Container content, int width, int height) {
		return (openInJFrame(content, width, height, content.getClass()
				.getName(), Color.white));
	}

	/**
	 * Uses Color.white as the background color.
	 * 
	 * @param content
	 *            the content
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @param title
	 *            the title
	 * @return the j frame
	 */

	public static JFrame openInJFrame(Container content, int width, int height,
			String title) {
		return (openInJFrame(content, width, height, title, Color.white));
	}

	/**
	 * A simplified way to see a JPanel or other Container. Pops up a JFrame
	 * with specified Container as the content pane.
	 * 
	 * @param content
	 *            the content
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @param title
	 *            the title
	 * @param bgColor
	 *            the bg color
	 * @return the j frame
	 */

	public static JFrame openInJFrame(Container content, int width, int height,
			String title, Color bgColor) {
		final JFrame frame = new JFrame(title);
		frame.setBackground(bgColor);
		content.setBackground(bgColor);
		frame.setSize(width, height);
		frame.setContentPane(content);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				// System.exit(0);
				// frame.dispose();
			}
		});
		frame.setVisible(true);
		return (frame);
	}

	/**
	 * Sets the java look and feel.
	 */
	public static void setJavaLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (final Exception e) {
			System.out.println("Error setting Java LAF: " + e);
		}
	}

	/**
	 * Sets the motif look and feel.
	 */
	public static void setMotifLookAndFeel() {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		} catch (final Exception e) {
			System.out.println("Error setting Motif LAF: " + e);
		}
	}

	/**
	 * Tell system to use native look and feel, as in previous releases. Metal
	 * (Java) LAF is the default otherwise.
	 */

	public static void setNativeLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (final Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
	}

	/**
	 * Sets the plastic3 d look and feel.
	 */
	public static void setPlastic3DLookAndFeel() {
		try {
			PlasticLookAndFeel.setCurrentTheme(new DesertBluer());
			// .setMyCurrentTheme(new DesertBluer());

			UIManager
					.setLookAndFeel("com.jgoodies.plaf.plastic.Plastic3DLookAndFeel");
		} catch (final Exception e) {
		}
	}
}