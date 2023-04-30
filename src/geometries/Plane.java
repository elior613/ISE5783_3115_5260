


/**
 This class represents a Plane in 3D space.
 */
package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;
/**
 * Plane is an object witch includes a point on the plane and the vector normal on the plane
 */
public class Plane implements Geometry {
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

    public List<Point> findIntersections(Ray ray){
        return null;
    }
}







