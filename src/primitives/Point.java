/**
 The Point class represents a point in three-dimensional space.
 */

package primitives;
import java.util.Objects;
public class Point {
    /**
     * The coordinates of the point in three-dimensional space.
     */
    final Double3 xyz;

    /**
     * The string representation of the point.
     */
    protected String toString;

    /**
     * Constructs and initializes a Point object with the specified x, y, and z coordinates.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     * @param z the z coordinate of the point
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Constructs and initializes a Point object with the specified Double3 object.
     *
     * @param double3 the Double3 object containing the x, y, and z coordinates of the point
     */
    Point(Double3 double3) {
        this(double3.d1, double3.d2, double3.d3);
    }

    /**
     * Compares this point to the specified object.
     *
     * @param o the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return xyz.equals(point.xyz);
    }

    /**
     * Returns a hash code value for the point.
     *
     * @return a hash code value for the point
     */
    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    /**
     * Returns a string representation of the point.
     *
     * @return a string representation of the point
     */
    @Override
    public String toString() {
        return "Point: " + xyz;
    }

    /**
     * Returns the square of the distance between this point and the specified point.
     *
     * @param other the point to calculate the distance to
     * @return the square of the distance between this point and the specified point
     */
    public double distanceSquared(Point other) {
        double dx = other.xyz.d1 - xyz.d1;
        double dy = other.xyz.d2 - xyz.d2;
        double dz = other.xyz.d3 - xyz.d3;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Returns the distance between this point and the specified point.
     *
     * @param other the point to calculate the distance to
     * @return the distance between this point and the specified point
     */
    public double distance(Point other) {
        return Math.sqrt(distanceSquared(other));
    }

    /**
     * Returns a new point that is the result of adding the specified vector to this point
     **/
    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }

    /**
     * Returns a new point that is the result of substruct the specified vector from this point
     **/
    public Vector subtract(Point point) {
        return new Vector(xyz.subtract(point.xyz));
    }

}



