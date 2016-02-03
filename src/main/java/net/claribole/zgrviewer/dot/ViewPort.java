/*   FILE: ViewPort.java
 *   DATE OF CREATION:   Apr 4 2005
 *   AUTHOR :            Eric Mounhem (skbo@lri.fr)
 *   Copyright (c) INRIA, 2004-2005. All Rights Reserved
 *   Licensed under the GNU LGPL. For full terms see the file COPYING.
 * 
 * $Id: ViewPort.java 576 2007-03-29 18:32:53Z epietrig $
 */

package net.claribole.zgrviewer.dot;

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * ViewPort used to render only some parts of a graph
 * @author Eric Mounhem
 */
class ViewPort {
    private Point          dimensions;
    private Point2D.Double position;
    private double         Z;

    /**
     * Create a new viewPort
     * 
     * @param W
     *            width of the final image
     * @param H
     *            height of the final image
     * @param Z
     *            zoom factor
     * @param x
     *            x position in the graph
     * @param y
     *            y position in the graph
     */
    public ViewPort(int W, int H, double Z, double x, double y) {
        this.setDimensions(new Point(W, H));
        this.setPosition(new Point2D.Double(x, y));
        this.setZ(Z);
    }

    /**
     * Dimensions of the viewPort image (in pixels)
     */
    public Point getDimensions() {
        return dimensions;
    }

    public void setDimensions(Point dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * Position in the graph (in points)
     */
    public Point2D.Double getPosition() {
        return position;
    }

    public void setPosition(Point2D.Double position) {
        this.position = position;
    }

    /**
     * Zoom factor: 1 point correspond to Z pixels
     */
    public double getZ() {
        return Z;
    }

    public void setZ(double z) {
        Z = z;
    }
}