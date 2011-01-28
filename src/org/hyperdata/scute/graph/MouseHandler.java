package org.hyperdata.scute.graph;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

public class MouseHandler implements MouseMotionListener, MouseListener {
	private final GraphDiagramPanel graphPanel;
	private Node pick;

	public MouseHandler(GraphDiagramPanel graphPanel) {
		this.graphPanel = graphPanel;
		pick = graphPanel.getPick();
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		// addMouseMotionListener(this);
		double bestdist = Double.MAX_VALUE;
		int x = e.getX();
		int y = e.getY();
//		for (int i = 0; i < graphPanel.getNnodes(); i++) {
//			Node n = graphPanel.getNode(i); 
Iterator<Node> nIterator = graphPanel.nodeIterator();
		while(nIterator.hasNext()){
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

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		pick.setX(e.getX());
		pick.setY(e.getY());
		graphPanel.repaint();
		e.consume();
	}

	public void mouseMoved(MouseEvent e) {
	}

}
