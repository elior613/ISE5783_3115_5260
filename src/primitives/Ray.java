package primitives;
import geometries.Intersectable;

import java.util.List;
import java.util.Objects;
import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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
     * DELTA value to change the location of point
     */
    private static final double DELTA = 0.1;



    /**
     * Constructs a new Ray object with the specified starting point and direction vector.
     */
    public Ray(Point po, Vector direction) {
        this.p0 = po;
        this.direction = direction.normalize();
    }
    /**
     * Constructor for ray deflected by DELTA
     *
     * @param p origin
     * @param n   normal vector
     * @param dir direction
     */
    public Ray(Point p, Vector n, Vector dir) {
        this.direction = dir.normalize();
        double nv = n.dotProduct(this.direction);
        Vector delta  =n.scale(DELTA);
        if (nv < 0)
            delta = delta.scale(-1d);
        this.p0 = p.add(delta);
    }
//    public Ray(Point p, Vector n, Vector dir) {
//        this.direction = dir.normalize();
//        double nv = alignZero(n.dotProduct(this.direction));
//        this.p0 =isZero(nv)?p:p.add(n.scale(nv<0?-DELTA:DELTA));
//        this.direction=direction.normalize();
//    }

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

    /**
     * Finds the closest point from a list of points to the reference point p0.
     *
     * @param points the list of points from which to find the closest point
     * @return the closest point to p0, or null if the list is empty
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }


    /**
     * Finds the closest Geopoint from a list of Geopoints to the reference point p0.
     *
     * @param listGeoPoints the list of points from which to find the closest point
     * @return the closest Geopoint to p0, or null if the list is empty
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> listGeoPoints){
        if (listGeoPoints.isEmpty())
            return null;

        // Initialize the closest point to the head point of the list
        // We use distanceSquared to save running time
        double closestDistanceSquared = p0.distanceSquared(listGeoPoints.get(0).point);
        GeoPoint closestGeoPoint = listGeoPoints.get(0);

        for (GeoPoint p : listGeoPoints) {
            if (p0.distanceSquared(p.point) < closestDistanceSquared) {
                // If this Geopoint is closer than our closest point, we update the value to the current point
                closestDistanceSquared = p0.distanceSquared(p.point);
                closestGeoPoint = p;
            }
        }
        return closestGeoPoint;
    }

}




