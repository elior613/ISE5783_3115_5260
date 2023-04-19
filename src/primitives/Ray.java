package primitives;

import java.util.Objects;
/**
 The Ray class represents a ray in three-dimensional space, defined by a starting point and a direction vector.
 */

public class Ray {
    /**
     * po the starting point of the ray
     */
    Point p0;
   /**
    * dir the direction vector of the ray
    */
   Vector dir;


    /**
     * Constructs a new Ray object with the specified starting point and direction vector.
     */
    public Ray(Point po, Vector dir) {
        this.p0 = po;
        this.dir = dir.normalize();
    }

    /**
     * Determines whether the specified object is equal to this Ray object.
     * @param o the object to compare with this Ray object
     * @return true if the specified object is equal to this Ray object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }


    /**
     * Returns a hash code value for this Ray object.
     * @return a hash code value for this Ray object
     */
    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    /**
     * Returns a point of p0.
     * @return Point
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Returns a vector of the ray.
     * @return Vector
     */
    public Vector getDirection() {
        return dir;
    }

    /**
     * Returns a string representation of this Ray object.
     * @return a string representation of this Ray object
     */
    @Override
    public String toString() {
        return "Ray{" +
                "po=" + p0 +
                ", dir=" + dir +
                '}';
    }
}




