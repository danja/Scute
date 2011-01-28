/**
 * 
 */
package org.hyperdata.scute.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.Iterator;

import javax.swing.JPanel;

/**
 * @author danny
 * 
 */
public class GraphLayout implements Runnable {

	private static final double CONSTANT1 = .1;
	private static final double CONSTANT2 = 1000;
	private static final double CONSTANT3 = 10000;
	private static final int CONSTANT4 = 500;
	private static final double CONSTANT5 = 0.5;

	private Node pick;
	private boolean pickfixed;
	private Image offScreen;
	private Dimension offScreenSize;
	private Graphics2D offGraphics;

	final Color edgeColor = Color.black;
	final Color arcColor1 = Color.black;
	final Color arcColor2 = Color.pink;
	final Color arcColor3 = Color.red;

	private Thread springs = null;
	boolean stress;
	boolean random;
	private final GraphSet graphSet;
	private final JPanel panel;

	private final double arrowScale = 10;
	// private final Line2D.Double originLine = new Line2D.Double(0, 0, 0,
	// arrowLength);
	private final Path2D.Double originArrow = getOriginArrow();

	// private static final Path2D.Double;

	public GraphLayout(JPanel panel, GraphSet graphSet) {
		this.graphSet = graphSet;
		this.panel = panel;
	}

	public void run() {
		// Thread me = Thread.currentThread();
		// while (springs == me) {
		while (true) {
			relax();
			if (random && (Math.random() < CONSTANT5)) {
				Node n = graphSet.getRandomNode();
				if (!n.isFixed()) {
					n.setX(n.getX()
							+ (panel.getWidth() * Math.random() - panel
									.getWidth() / 2));
					n.setY(n.getY()
							+ (panel.getHeight() * Math.random() - panel
									.getHeight() / 2));
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// panel.repaint();
				break;

			}
		}
	}

	synchronized void relax() {
		for (int i = 0; i < graphSet.getNedges(); i++) {

			Edge e = graphSet.getEdge(i);

			double vx = e.to.getX() - e.from.getX();
			double vy = e.to.getY() - e.from.getY();

			double len = Math.sqrt(vx * vx + vy * vy);
			len = (len == 0) ? CONSTANT1 : len;

			double f = (graphSet.getEdge(i).len - len) / (len * CONSTANT2);

			double dx = f * vx;
			double dy = f * vy;

			e.to.setDx(e.to.getDx() + dx);
			e.to.setDy(e.to.getDy() + dy);
			e.from.setDx(e.from.getDx() + (-dx));
			e.from.setDy(e.from.getDy() + (-dy));
		}

		Iterator<Node> nIterator1 = graphSet.nodeIterator();
		while (nIterator1.hasNext()) {
			Node n1 = nIterator1.next();

			// for (int i = 0; i < graphSet.getNnodes(); i++) {
			// Node n1 = graphSet.getNode(i);
			double dx = 0;
			double dy = 0;

			Iterator<Node> nIterator2 = graphSet.nodeIterator();
			while (nIterator2.hasNext()) {
				Node n2 = nIterator2.next();
				if (n1.equals(n2)) {
					continue;
					// for (int j = 0; j < graphSet.getNnodes(); j++) {
					// if (i == j) {
					// continue;
					// }
					// Node n2 = graphSet.getNode(j);
				}

				double vx = n1.getX() - n2.getX();
				double vy = n1.getY() - n2.getY();
				double len = vx * vx + vy * vy;
				if (len == 0) {
					dx += Math.random();
					dy += Math.random();
				} else if (len < CONSTANT3) {
					dx += vx / len;
					dy += vy / len;
				}
			}
			double dlen = dx * dx + dy * dy;
			if (dlen > 0) {
				dlen = Math.sqrt(dlen) / 2;
				n1.setDx(n1.getDx() + (dx / dlen));
				n1.setDy(n1.getDy() + (dy / dlen));
			}
		}

		Dimension d = panel.getSize();

		Iterator<Node> nIterator = graphSet.nodeIterator();
		while (nIterator.hasNext()) {
			Node n = nIterator.next();

			if (!n.isFixed()) {
				n.setX(n.getX()
						+ Math.max(-CONSTANT4, Math.min(CONSTANT4, n.getDx())));
				n.setY(n.getY()
						+ Math.max(-CONSTANT4, Math.min(CONSTANT4, n.getDy())));
			}
			if (n.getX() < 0) {
				n.setX(0);
			} else if (n.getX() > d.width) {
				n.setX(d.width);
			}
			if (n.getY() < 0) {
				n.setY(0);
			} else if (n.getY() > d.height) {
				n.setY(d.height);
			}
			n.setDx(n.getDx() / 2);
			n.setDy(n.getDy() / 2);
		}
		panel.repaint();
	}

	public synchronized Image getImage() {

		Dimension d = panel.getSize();
		if ((offScreen == null) || (d.width != offScreenSize.width)
				|| (d.height != offScreenSize.height)) {
			offScreen = panel.createImage(d.width, d.height);
			offScreenSize = d;
			if (offGraphics != null) {
				offGraphics.dispose();
			}
			offGraphics = (Graphics2D) offScreen.getGraphics();
			offGraphics.setFont(panel.getFont());
		}

		offGraphics.setColor(panel.getBackground());
		offGraphics.fillRect(0, 0, d.width, d.height);

		// System.out.println("graphics "+d);
		for (int i = 0; i < graphSet.getNedges(); i++) {

			Edge e = graphSet.getEdge(i);

			double xx1 = e.from.getX();
			double yy1 = e.from.getY();
			double xx2 = e.to.getX();
			double yy2 = e.to.getY();

			offGraphics.setColor(Color.blue);
			offGraphics.drawLine((int) xx1, (int) yy1, (int) xx2, (int) yy2);

			drawArrows(xx1, yy1, xx2, yy2);

			double centerX = ((xx1 + xx2) / 2);
			double centerY = ((yy1 + yy2) / 2);
			e.setCenter((int) centerX, (int) centerY);
		}

		// FontMetrics fm = offgraphics.getFontMetrics();

		return offScreen;
	}

	private Path2D.Double getOriginArrow() { // arrow shape, point at origin
		Path2D.Double arrow = new Path2D.Double();
		arrow.moveTo(-1 * arrowScale, 0.5 * arrowScale);
		arrow.lineTo(0, 0);
		arrow.lineTo(-1 * arrowScale, -0.5 * arrowScale);
		return arrow;
	}

	public void drawArrows(double xx1, double yy1, double xx2, double yy2) {

		// Decided against having two arrows on the line, left here in just case
		// double arrowOneX = xx1 + (xx2 - xx1)/4;
		// double arrowOneY = yy1 + (yy2 - yy1)/4;
		//		
		// AffineTransform rotateOne =
		// AffineTransform.getRotateInstance(xx2-xx1, yy2-yy1,
		// arrowOneX,arrowOneY);
		// AffineTransform translateOne =
		// AffineTransform.getTranslateInstance(arrowOneX, arrowOneY);
		// rotateOne.concatenate(translateOne);
		// Shape arrowOne = rotateOne.createTransformedShape(arrow);
		// offGraphics.draw(arrowOne);

		double arrowTwoX = xx1 + 3 * (xx2 - xx1) / 4;
		double arrowTwoY = yy1 + 3 * (yy2 - yy1) / 4;

		AffineTransform rotateTwo = AffineTransform.getRotateInstance(
				xx2 - xx1, yy2 - yy1, arrowTwoX, arrowTwoY);
		AffineTransform translateTwo = AffineTransform.getTranslateInstance(
				arrowTwoX, arrowTwoY);

		rotateTwo.concatenate(translateTwo);

		Shape arrowTwo = rotateTwo.createTransformedShape(originArrow);

		offGraphics.draw(arrowTwo);
	}

	// unused, left here for the moment just in case
	public void drawArrowsOld(double xx1, double yy1, double xx2, double yy2) {
		offGraphics.setColor(Color.GREEN);

		double angle = 30 * Math.PI / 180; // 30'

		double cos = Math.cos(angle);
		double sin = Math.sin(angle);

		double p = xx2 - xx1;
		double q = yy2 - yy1;

		double len = Math.sqrt((xx2 - xx1) * (xx2 - xx1) + (yy2 - yy1)
				* (yy2 - yy1));

		double ax = xx2 - (p * cos - q * sin) * 50 / len;
		double ay = yy2 - (q * cos + p * sin) * 50 / len;

		offGraphics.drawLine((int) xx2, (int) yy2, (int) ax, (int) ay);
	}

	public void start() {
		// if(springs == null){
		springs = new Thread(this);
		// }
		springs.start();
	}

	public void stop() {
		springs.interrupt();
		springs = null;
	}

	public Node getPick() {
		return pick;
	}

	public void setPickFixed(boolean fixed) {
		pickfixed = fixed;
	}

	public boolean isPickFixed() {
		return pickfixed;
	}
}
