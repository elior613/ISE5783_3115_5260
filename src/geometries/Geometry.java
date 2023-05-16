

/**
 The Geometry interface represents geometric shapes in 3D space.
 Any class that implements this interface must provide an implementation for the getNormal method.
 */
package geometries;
        import primitives.Point;
        import primitives.Ray;
        import primitives.Vector;

        import java.util.List;

public abstract class Geometry extends Intersectable {
    /**
     * Returns a normal vector to the geometry shape at the specified point.
     *
     * @param p the point on the shape to get the normal vector at
     * @return the normal vector to the geometry shape at the specified point
     */
    public abstract Vector getNormal(Point p);




}
