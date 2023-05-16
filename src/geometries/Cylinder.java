
package geometries;

        import primitives.Point;
        import primitives.Ray;
        import primitives.Vector;

        import java.util.List;

        import static primitives.Util.alignZero;
        import static primitives.Util.isZero;

/**
 * Cylinder is an object with the specified radius, axis and height
 */
public class Cylinder extends Tube{
    /**
     * h The height of the cylinder.
     */
    private double height;

    /**
     * Constructs a new Cylinder object with the specified radius, axis and height.
     * @param radius The radius of the cylinder.
     * @param axis The axis ray of the cylinder.
     * @param h The height of the cylinder.
     */
    public Cylinder(double radius, Ray axis,double h) {
        super(radius, axis);
        height=h;
    }

    /**
     * @param Point.
     * return the normalize of the vector
     */

    @Override
    public Vector getNormal(Point point) {
        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDirection();

        //if the point and p0 are the same
        if (point.equals(p0))
            return v;

        // projection of P-p0 on the ray:
        Vector u = point.subtract(p0);

        // distance from p0 to the o who is in from of point
        double t = alignZero(u.dotProduct(v));

        // if the point is at a base
        if (t == 0 || isZero(height - t))
            return v;

        //the other point on the axis facing the given point
        Point o = p0.add(v.scale(t));

        //create the normal vector
        return point.subtract(o).normalize();
    }

}


