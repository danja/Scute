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
package org.hyperdata.scute.status;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.hyperdata.resources.scute.ScuteIcons;

/**
 * The Class AnimatedCursor.
 * 
 * bit troublesome this - save for later
 */
public class AnimatedCursor implements Runnable, ActionListener, StatusChangeListener{
	private boolean loop;
	// private Cursor[] cursors;
	private JFrame frame;

	/**
	 * Instantiates a new animated cursor.
	 *
	 * @param frame the frame
	 */
	public AnimatedCursor(JFrame frame) {
		loop = false;
		this.frame = frame;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		System.out.println("RUN");
		int count = 0;
		while (loop) {
			// System.out.println("SPIN");
			try {
				Thread.currentThread().sleep(200);
			} catch (Exception ex) {
			}
			frame.setCursor(ScuteIcons.spinCube[count]);
			if (++count == ScuteIcons.spinCube.length) {
				count = 0;
			}
		}
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	} 

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent evt) {
		JButton button = (JButton) evt.getSource();
		if (loop) {
			button.setText("Start");
			loop = false;
		} else {
			loop = true;
			button.setText("Stop");
			new Thread(this).start();
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Animated Cursor");

		JButton button = new JButton("Start Animation");
		button.addActionListener(new AnimatedCursor(frame));

		frame.getContentPane().add(button);
		frame.pack();
		frame.show();
	}

	/**
	 * Loop.
	 *
	 * @param loop the loop
	 */
	public void loop(boolean loop) {
		this.loop = loop;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.status.StatusChangeListener#statusChanged(org.hyperdata.scute.status.StatusEvent)
	 */
	@Override
	public void statusChanged(StatusEvent statusEvent) {
		if(statusEvent.getStatus() == StatusMonitor.GREEN){
			loop = false;
		}
	}
}