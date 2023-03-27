
package geometries;

        import primitives.Point;
        import primitives.Ray;
        import primitives.Vector;

public class Cylinder extends Tube{
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
    public Vector getNormal(Point p) {
        // TODO: Implement this method to return the normal vector to a      //point on the cylinder's surface.
        return null;
    }
}


