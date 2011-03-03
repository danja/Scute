/**
 * 
 */
package org.hyperdata.scute.syntax;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import javax.swing.text.BadLocationException;
import javax.swing.text.BoxView;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.View;
import javax.swing.text.WrappedPlainView;

/**
 * @author danny
 *
 */
class ScalableView extends WrappedPlainView { // BoxView
	
	
    public ScalableView(Element elem) {
        super(elem);
        setAxis(View.Y_AXIS);
    }

    public double getZoomFactor() {
        Double scale = (Double) getDocument().getProperty("ZOOM_FACTOR");
        if (scale != null) {
            return scale.doubleValue();
        }

        return 1;
    }

    public void paint(Graphics g, Shape allocation) {
        Graphics2D g2d = (Graphics2D) g;
        double zoomFactor = getZoomFactor();
        AffineTransform old = g2d.getTransform();
        g2d.scale(zoomFactor, zoomFactor);
        super.paint(g2d, allocation);
        g2d.setTransform(old);
    }

    public float getMinimumSpan(int axis) {
        float f = super.getMinimumSpan(axis);
        f *= getZoomFactor();
        return f;
    }

    public float getMaximumSpan(int axis) {
        float f = super.getMaximumSpan(axis);
        f *= getZoomFactor();
        return f;
    }

    public float getPreferredSpan(int axis) {
        float f = super.getPreferredSpan(axis);
        f *= getZoomFactor();
        return f;
    }

    protected void layout(int width, int height) {
        super.layout(new Double(width / getZoomFactor()).intValue(),
                     new Double(height *
                                getZoomFactor()).intValue());
    }

    public Shape modelToView(int pos, Shape a, Position.Bias b) throws BadLocationException {
        double zoomFactor = getZoomFactor();
        Rectangle alloc;
        alloc = a.getBounds();
        Shape s = super.modelToView(pos, alloc, b);
        alloc = s.getBounds();
        alloc.x *= zoomFactor;
        alloc.y *= zoomFactor;
        alloc.width *= zoomFactor;
        alloc.height *= zoomFactor;

        return alloc;
    }

    public int viewToModel(float x, float y, Shape a,
                           Position.Bias[] bias) {
        double zoomFactor = getZoomFactor();
        Rectangle alloc = a.getBounds();
        x /= zoomFactor;
        y /= zoomFactor;
        alloc.x /= zoomFactor;
        alloc.y /= zoomFactor;
        alloc.width /= zoomFactor;
        alloc.height /= zoomFactor;

        return super.viewToModel(x, y, alloc, bias);
    }

}

