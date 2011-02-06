/**
 * 
 */
package org.hyperdata.scute.graph;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * The Class ZoomPanel.
 *
 * @author danja
 */
public class ZoomPanel extends JPanel { 

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double zoom = 1.0;

	  private double percentage;

	  private Image image;

	  /**
  	 * Instantiates a new zoom panel.
  	 *
  	 * @param image the image
  	 * @param zoomPercentage the zoom percentage
  	 */
  	public ZoomPanel(Image image, double zoomPercentage) {
	    this.image = image;
	    percentage = zoomPercentage / 100;
	  }

	  /* (non-Javadoc)
  	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
  	 */
  	@Override
	public void paintComponent(Graphics grp) {
	    Graphics2D g2D = (Graphics2D) grp;
	    g2D.scale(zoom, zoom);
	    g2D.drawImage(image, 0, 0, this);
	  }

	  /**
  	 * Sets the zoom percentage.
  	 *
  	 * @param zoomPercentage the new zoom percentage
  	 */
  	public void setZoomPercentage(int zoomPercentage) {
	    percentage = ((double) zoomPercentage) / 100;
	  }

	  /**
  	 * Original size.
  	 */
  	public void originalSize() {
	    zoom = 1;
	  }

	  /**
  	 * Zoom in.
  	 */
  	public void zoomIn() {
	    zoom += percentage;
	  }

	  /**
  	 * Zoom out.
  	 */
  	public void zoomOut() {
	    zoom -= percentage;

	    if (zoom < percentage) {
	      if (percentage > 1.0) {
	        zoom = 1.0;
	      } else {
	        zoomIn();
	      }
	    }
	  }
}
