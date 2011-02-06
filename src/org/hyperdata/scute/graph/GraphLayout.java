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
import javax.swing.SwingUtilities;

/**
 * The Class GraphLayout.
 * 
 * @author danja
 * 
 * FIXME threading isn't quite right, buttons in a row flash between start/stop
 * 
 * TODO add edge uncrossing
 * 
 * TODO check other algorithms
 */
public class GraphLayout implements Runnable {

	/** The Constant EDGE_MIN_LENGTH. */
	private static final double EDGE_MIN_LENGTH = .1;

	/** The Constant CONSTANT2. */
	private static final double CONSTANT2 = 1000;

	/** The Constant CONSTANT3. */
	private static final double CONSTANT3 = 10000;

	/** The Constant CONSTANT4. */
	private static final int CONSTANT4 = 500;

	/** The Constant RANDOMIZE_NODE_PROBABILITY. */
	private static final double RANDOMIZE_NODE_PROBABILITY = 0.1;

	private boolean running = false;

	/** The pick. */
	private Node pick;

	/** The pickfixed. */
	private boolean pickfixed;

	/** The off screen. */
	private Image offScreen;

	/** The off screen size. */
	private Dimension offScreenSize;

	/** The off graphics. */
	private Graphics2D offGraphics;

	/** The edge color. */
	final Color edgeColor = Color.black;

	/** The arc color1. */
	final Color arcColor1 = Color.black;

	/** The arc color2. */
	final Color arcColor2 = Color.pink;

	/** The arc color3. */
	final Color arcColor3 = Color.red;

	/** The springs. */
	private Thread springs = null;

	/** The stress. */
	boolean stress;

	/** The random. */
	boolean random = true;

	/** The graph set. */
	private final GraphSet graphSet;

	/** The panel. */
	private final JPanel panel;

	/** The arrow scale. */
	private final double arrowScale = 10;

	/** The origin arrow. */
	private final Path2D.Double originArrow = getOriginArrow();

	/**
	 * Instantiates a new graph layout.
	 * 
	 * @param panel
	 *            the panel
	 * @param graphSet
	 *            the graph set
	 */
	public GraphLayout(JPanel panel, GraphSet graphSet) {
		this.graphSet = graphSet;
		this.panel = panel;
	}
	
	/**
	 * Inits the.
	 */
	public void init() {
		scramble();	
	}

	/**
	 * Checks if is running.
	 *
	 * @return true, if is running
	 */
	public boolean isRunning() {
		return this.running;
	}

	/**
	 * Sets the running.
	 *
	 * @param run the new running
	 */
	public void setRunning(boolean run) {
		if (run) {
			scramble();
			start();
		} else {
			stop();
		}
		this.running = run;
	}

	/**
	 * Scramble.
	 */
	public void scramble() {
		Dimension d = panel.getSize();
		Iterator<Node> nIterator = graphSet.nodeIterator();
		while (nIterator.hasNext()) {
			Node n = nIterator.next();
			if (!n.isFixed()) {
				n.setX(10 + (d.width - 20) * Math.random());
				n.setY(10 + (d.height - 20) * Math.random());
			}
		}
	}

	private Runnable cleanup = new Runnable() {
		@Override
		public void run() {
			relax();
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (this.running) {
			relax();
			// if (random && (Math.random() < RANDOMIZE_NODE_PROBABILITY)) {
			// Node n = graphSet.getRandomNode();
			// randomizeNode(n);
			// }
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// SwingUtilities.invokeLater(cleanupRunnable);
				this.running = false;
				break;
			} finally {
				// relax(); // meh
			}
		}
		SwingUtilities.invokeLater(cleanup); // one last relax

	}

	/**
	 * Randomize node.
	 * 
	 * @param node
	 *            the node
	 */
	private void randomizeNode(Node node) {
		if (!node.isFixed()) {
			node.setX(node.getX()
					+ (panel.getWidth() * Math.random() - panel.getWidth() / 2));
			node.setY(node.getY()
					+ (panel.getHeight() * Math.random() - panel.getHeight() / 2));
		}
	}

	/**
	 * Relax.
	 */
	private void relax() {
		doEdges(); // TODO rename these
		doNodes();
		doNodes2();
	}

	/**
	 * Do edges.
	 */
	private void doEdges() { // TODO rename
		Iterator<Edge> eIterator = graphSet.edgeIterator();

		while (eIterator.hasNext()) {
			Edge e = eIterator.next();

			double vx = e.to.getX() - e.from.getX();
			double vy = e.to.getY() - e.from.getY();

			double len = Math.sqrt(vx * vx + vy * vy);

			len = (len == 0) ? EDGE_MIN_LENGTH : len;

			double f = (e.len - len) / (len * CONSTANT2);

			double dx = f * vx;
			double dy = f * vy;

			e.to.setDx(e.to.getDx() + dx);
			e.to.setDy(e.to.getDy() + dy);
			e.from.setDx(e.from.getDx() + (-dx));
			e.from.setDy(e.from.getDy() + (-dy));
		}
	}

	/**
	 * Do nodes.
	 */
	private void doNodes() { // TODO rename
		Iterator<Node> nIterator1 = graphSet.nodeIterator();
		while (nIterator1.hasNext()) {
			Node n1 = nIterator1.next();

			double dx = 0;
			double dy = 0;

			Iterator<Node> nIterator2 = graphSet.nodeIterator();
			while (nIterator2.hasNext()) {
				Node n2 = nIterator2.next();
				if (n1.equals(n2)) {
					continue;
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
	}

	/**
	 * Do nodes2.
	 */
	private void doNodes2() { // TODO rename
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
	}

	/**
	 * Gets the image.
	 * 
	 * @return the image
	 */
	public Image getImage() {

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

		Iterator<Edge> eIterator = graphSet.edgeIterator();
		while (eIterator.hasNext()) {
			Edge e = eIterator.next();

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

	/**
	 * Gets the origin arrow.
	 * 
	 * @return the origin arrow
	 */
	private Path2D.Double getOriginArrow() { // arrow shape, point at origin
		Path2D.Double arrow = new Path2D.Double();
		arrow.moveTo(-1 * arrowScale, 0.5 * arrowScale);
		arrow.lineTo(0, 0);
		arrow.lineTo(-1 * arrowScale, -0.5 * arrowScale);
		return arrow;
	}

	/**
	 * Draw arrows.
	 * 
	 * @param xx1
	 *            the xx1
	 * @param yy1
	 *            the yy1
	 * @param xx2
	 *            the xx2
	 * @param yy2
	 *            the yy2
	 */
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
	/**
	 * Draw arrows old.
	 * 
	 * @param xx1
	 *            the xx1
	 * @param yy1
	 *            the yy1
	 * @param xx2
	 *            the xx2
	 * @param yy2
	 *            the yy2
	 */
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

	/**
	 * Start.
	 */
	public void start() {
		// if(springs == null){
		springs = new Thread(this);
		// }
		springs.start();
	}

	/**
	 * Stop.
	 */
	public void stop() {
		springs.interrupt();
		springs = null;
	}

	/**
	 * Gets the pick.
	 * 
	 * @return the pick
	 */
	public Node getPick() {
		return pick;
	}

	/**
	 * Sets the pick fixed.
	 * 
	 * @param fixed
	 *            the new pick fixed
	 */
	public void setPickFixed(boolean fixed) {
		pickfixed = fixed;
	}

	/**
	 * Checks if is pick fixed.
	 * 
	 * @return true, if is pick fixed
	 */
	public boolean isPickFixed() {
		return pickfixed;
	}
}
