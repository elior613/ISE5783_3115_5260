
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Represents a tube in 3D space, which is defined by a radius and an axis ray.
 */
public class Tube extends RadialGeometry {

    /**
     * The axis ray of the tube.
     */
    protected Ray axisRay;

    /**
     * Constructs a new Tube object with the specified radius and axis ray.
     * @param radius The radius of the tube.
     * @param axis The axis ray of the tube.
     */
    public Tube(double radius, Ray axis) {
        super(radius);
        axisRay = axis;
    }

    /**
     * Returns the axis ray of the tube.
     * @return The axis ray of the tube.
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * return a normal vector to the tube in the point p, if there
     * @param p
     * @return
     */
    @Override
    public Vector getNormal(Point p) {
        Vector v = axisRay.getDir();
        Point p0 = axisRay.getP0();
        double t = v.dotProduct(p.subtract(p0));
        Vector normal;
        if(t != 0)
        {
            Point o = p0.add(v.scale(t));
            normal = (Vector) p.subtract(o).normalize();
        }
        else
            normal = (Vector) p.subtract(p0).normalize();
        return normal;
    }
}
