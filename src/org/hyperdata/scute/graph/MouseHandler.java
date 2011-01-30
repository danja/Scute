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
package org.hyperdata.scute.graph;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

/**
 * The Class MouseHandler.
 */
public class MouseHandler implements MouseMotionListener, MouseListener {
	
	/** The graph panel. */
	private final GraphDiagramPanel graphPanel;
	
	/** The pick. */
	private Node pick;

	/**
	 * Instantiates a new mouse handler.
	 * 
	 * @param graphPanel
	 *            the graph panel
	 */
	public MouseHandler(GraphDiagramPanel graphPanel) {
		this.graphPanel = graphPanel;
		pick = graphPanel.getPick();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// addMouseMotionListener(this);
		double bestdist = Double.MAX_VALUE;
		int x = e.getX();
		int y = e.getY();
		// for (int i = 0; i < graphPanel.getNnodes(); i++) {
		// Node n = graphPanel.getNode(i);
		Iterator<Node> nIterator = graphPanel.nodeIterator();
		while (nIterator.hasNext()) {
			Node n = nIterator.next();
			double dist = (n.getX() - x) * (n.getX() - x) + (n.getY() - y)
					* (n.getY() - y);
			if (dist < bestdist) {
				pick = n;
				bestdist = dist;
			}
		}
		graphPanel.setPickFixed(pick.isFixed());
		pick.setFixed(true);
		pick.setX(x);
		pick.setY(y);
		graphPanel.repaint();
		e.consume();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		graphPanel.removeMouseMotionListener(this);
		if (pick != null) {
			pick.setX(e.getX());
			pick.setY(e.getY());
			pick.setFixed(graphPanel.isPickFixed());
			pick = null;
		}
		graphPanel.repaint();
		e.consume();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		pick.setX(e.getX());
		pick.setY(e.getY());
		graphPanel.repaint();
		e.consume();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
