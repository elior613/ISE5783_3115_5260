package primitives;

import java.util.Objects;
/**
 The Ray class represents a ray in three-dimensional space, defined by a starting point and a direction vector. */

/**
 * Constructs a new Ray object with the specified starting point and direction vector.
 *  po the starting point of the ray
 *  dir the direction vector of the ray
 */
public class Ray {
    Point po;
    Vector dir;

    public Ray(Point po, Vector dir) {
        this.po = po;
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
        return po.equals(ray.po) && dir.equals(ray.dir);
    }


    /**
     * Returns a hash code value for this Ray object.
     * @return a hash code value for this Ray object
     */
    @Override
    public int hashCode() {
        return Objects.hash(po, dir);
    }


    /**
     * Returns a string representation of this Ray object.
     * @return a string representation of this Ray object
     */
    @Override
    public String toString() {
        return "Ray{" +
                "po=" + po +
                ", dir=" + dir +
                '}';
    }
}





