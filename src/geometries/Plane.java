


/**
 This class represents a Plane in 3D space.
 */
package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;
/**
 * Plane is an object witch includes a point on the plane and the vector normal on the plane
 */
public class Plane extends Geometry {
    /**
     * A point on the plane
     */
    private Point q0;
    /**
     * The normal vector to the plane
     */
    private Vector normal;
    /**
     * Constructor for creating a plane from three points on the plane
     * @param q1 The first point
     * @param q2 The second point
     * @param q3 The third point
     */
    public Plane(Point q1,Point q2,Point q3) {
        q0 = q1; // Set one of the points as a point on the plane
        Vector v1=q2.subtract(q1);
        Vector v2=q3.subtract(q1);
        if (v1.lengthSquared()==0 || v2.lengthSquared()==0)
            throw new IllegalArgumentException("the points are  the same ");
        // check whether the vertices are on the same ray
        if(v1 == v2 || v1 == v2.scale(-1d))
            throw new IllegalArgumentException("the points in the same line");

        this.normal = v1.crossProduct(v2).normalize(); // Initialize the normal vector to null
    }


    /**
     * Constructor for creating a plane from a point on the plane and the normal vector
     * @param q0 A point on the plane
     * @param normal The normal vector to the plane
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize(); // Normalize the normal vector
    }


    /**
     * Getter for the point on the plane
     * @return The point on the plane
     */
    public Point getPoint() {
        return q0;
    }


    /**
     * Getter for the normal vector to the plane
     * @return The normal vector to the plane
     */
    public Vector getNormal() {
        return normal;
    }


    /**
     * Returns the normal vector to the plane at a given point (which is on the plane)
     * @param p The point on the plane
     * @return The normal vector to the plane at the given point
     */
    @Override
    public Vector getNormal(Point p) {
        return normal;
    }


    /**

     Finds the intersections of the given ray with the plane. Returns a list of points representing the intersections.

     If there are no intersections, the method returns null.

     @param ray The ray to intersect with the plane.

     @return A list of points representing the intersections, or null if there are no intersections.
     */

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        //get the direction and starting point of the ray
        Vector v = ray.getDirection();
        Point p0 = ray.getP0();

        //get the normal to the plane
        Vector n=normal;

        //if the ray starts on the q0 point of the plane, there is no intersection
        if(q0.equals(p0))
            return null;

        //calculate the vector between the starting point of the ray and q0
        Vector P0_Q0=q0.subtract(p0);

        //calculate the numerator of the formula for calculating the intersection point
        double nP0Q0=alignZero(n.dotProduct(P0_Q0));

        //if the numerator is zero, the point p0 is on the plane and there is no intersection
        if(isZero(nP0Q0))
            return null;

        //calculate the denominator of the formula for calculating the intersection point
        double nv=alignZero(n.dotProduct(v));

        //if the denominator is zero, the ray is lying in the plane axis,
        // or the ray is in the plane or parallel to the plane - 0 points intersection
        if(isZero(nv))
            return null;

        //find the factor of the vector of the ray
        double t=alignZero(nP0Q0/nv);

        //if the factor is zero or negative, the ray is on the plane (t=0) or the ray is in the opposite side (t<0) - 0 points intersection
        if(t<=0)
            return null;

        //calculate the intersection point using the formula: P = P0 + tV
        Point intersection=ray.getPoint(t);

        return List.of(new GeoPoint(this,intersection));
    }
}







