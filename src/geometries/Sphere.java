
/**
 * The Sphere class represents a 3D sphere in Cartesian coordinates.
 * A sphere is defined by its center point and its radius.
 * The sphere can return a normalized vector normal to a given point on its surface.
 */
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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

    /**

     Finds the intersection points of a given ray with a sphere.

     @param ray - the given ray to intersect with the sphere
     @param maxDistance the maximum distance between the ray and the point
     @return List of intersection points if the ray intersects the sphere, null otherwise.
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        Vector v = ray.getDirection();
        Point p0 = ray.getP0();
        if (center.equals(p0)) { // p0 is the center
            //if the radius is bigger than maxDistance, there are no relevant points
            if(alignZero(radius - maxDistance) > 0){
                return null;
            }
            return List.of(new GeoPoint(this, ray.getPoint(radius)));
        }
        Vector u = center.subtract(p0);
        double tm = alignZero(v.dotProduct(u));
        double dSquared = isZero(tm) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(radius * radius - dSquared);
        // if (thSquared <= 0)
        //    return null;
        double th = alignZero(Math.sqrt(thSquared));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        //there is 2 intersection points, and they are closer than the maxDistance
        if (t1 > 0 && t2 > 0 && alignZero(t1 - maxDistance) <= 0 && alignZero(t2 - maxDistance) <= 0) {
            GeoPoint p1 = new GeoPoint(this, ray.getPoint(t1));
            GeoPoint p2 = new GeoPoint(this, ray.getPoint(t2));
            return List.of(p1, p2);
        }
        //only t1 intersects the sphere, and it is closer than the maxDistance
        if (t1 > 0 && alignZero(t1 - maxDistance) <= 0) {
            GeoPoint p1 = new GeoPoint(this, ray.getPoint(t1));
            return List.of(p1);
        }
        //only t2 intersects the sphere, and it is closer than the maxDistance
        if (t2 > 0 && alignZero(t2 - maxDistance) <= 0) {
            GeoPoint p2 = new GeoPoint(this, ray.getPoint(t2));
            return List.of(p2);
        }
        return null;

    }

}
