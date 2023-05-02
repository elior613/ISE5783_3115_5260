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
   Vector direction;


    /**
     * Constructs a new Ray object with the specified starting point and direction vector.
     */
    public Ray(Point po, Vector direction) {
        this.p0 = po;
        this.direction = direction.normalize();
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
        return p0.equals(ray.p0) && direction.equals(ray.direction);
    }


    /**
     * Returns a hash code value for this Ray object.
     * @return a hash code value for this Ray object
     */
    @Override
    public int hashCode() {
        return Objects.hash(p0, direction);
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
        return direction;
    }

    /**
     * Returns a string representation of this Ray object.
     * @return a string representation of this Ray object
     */
    @Override
    public String toString() {
        return "Ray{" +
                "po=" + p0 +
                ", dir=" + direction +
                '}';
    }

    /**
     * Returns a point on the ray with the value of vector multiplication by t starting from p0
     * @return Point
     */
    public Point getPoint(double t){
        return p0.add(direction.scale(t));
    }
}




