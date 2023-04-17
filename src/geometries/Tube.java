
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
     * Calculates and returns the normal vector to the tube at the specified point p.
     * @param p The point on the surface of the tube.
     * @return The normal vector to the tube at the specified point p.
     */
    @Override
    public Vector getNormal(Point p) {
        public Vector getNormal(Point point) {
            Point p0 = axisRay.getPo();
            Vector v = axisRay.getDir();
            Vector p0_p = p.subtract(p0);
            double t = alignZero(v.dotProduct(p0_p));
            double tmp= v.dotProduct(p0_p);
            if (isZero(t)){
                return p0_p.normalize();
            }
            Point o = p0.add(v.scale(t));
            if(point.equals(o)){
                throw new IllegalArgumentException("point cannot be on the tube axis");
            }
            Vector n = point.subtract(o).normalize();
            return n;
        }
    }
}

