/**
 * 
 */
package org.hyperdata.scute.toolbars.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JEditorPane;

/**
 * @author danny
 *
 */
public class ZoomAction extends AbstractAction {
	
	private JEditorPane zoomPane;
	private double zoomFactor;

	/**
	 * @param zoomPane
	 */
	public ZoomAction(JEditorPane zoomPane, String label, double zoomFactor) {
		super(label);
		this.zoomPane = zoomPane;
		this.zoomFactor = zoomFactor;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		double zoom = getZoom() * zoomFactor;
		System.out.println("zoom="+zoom);
		zoomPane.getDocument().putProperty("zoom", new Double(zoom));
		zoomPane.grabFocus();
	}
	
	private double getZoom() {
		Object zf = zoomPane.getDocument().getProperty("zoom");
		if (zf == null) {
			return 1.0;
		}
		return ((Double) zf).doubleValue();
	}

}
