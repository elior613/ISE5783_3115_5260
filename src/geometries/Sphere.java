
/**
 The Sphere class represents a 3D sphere in Cartesian coordinates.
 A sphere is defined by its center point and its radius.
 The sphere can return a normalized vector normal to a given point on its surface.
 */
package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Sphere class represents implementation of RadialGeometry and includes the point center and double of the radius
 */
public class Sphere extends RadialGeometry {
    /**
     * the point center is the center of the sphere
     */

    private Point center;

    /**
     * Constructs a sphere with the given radius and center point.
     *
     * @param radius the radius of the sphere
     * @param center the center point of the sphere
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * Returns the center point of the sphere.
     *
     * @return the center point of the sphere
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Computes and returns the normalized vector that is perpendicular to the sphere's surface at the given point.
     *
     * @return a normalized vector that is perpendicular to the sphere's surface at the given point
     */
    @Override
    public Vector getNormal(Point p) {
        // The normal vector to a point on a sphere is the direction vector from the center of the sphere to that point.
        return (Vector) p.subtract(center).normalize();
    }

    public List<Point> findIntersections(Ray ray){
        return null;
    }
}
