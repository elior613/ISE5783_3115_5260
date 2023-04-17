


/**
 This class represents a Plane in 3D space.
 */
package geometries;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.isZero;

public class Plane implements Geometry {
    private Point q0; // A point on the plane
    private Vector normal; // The normal vector to the plane
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
        if(!isZero(v1.dotProduct(v2)))
            throw new IllegalArgumentException("the points are in the same line");

        this.normal = (Vector) v1.crossProduct(v2).normalize(); // Initialize the normal vector to null
    }


    /**
     * Constructor for creating a plane from a point on the plane and the normal vector
     * @param q0 A point on the plane
     * @param normal The normal vector to the plane
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = (Vector) normal.normalize(); // Normalize the normal vector
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
        return normal; // Will be implemented later
    }
}







