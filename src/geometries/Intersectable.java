package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**

 The Intersectable class represents an abstract base class for intersectable geometric objects.
 It provides methods for finding intersections between objects and rays.
 */
public abstract class Intersectable {
    /**
     * Represents a geometric point of intersection between a ray and a geometry object.
     */
    public static class GeoPoint {
        final public Geometry geometry;
        final public Point point;

        /**
         * Constructs a GeoPoint object with the specified geometry and intersection point.
         *
         * @param geometry the geometry object
         * @param point    the intersection point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        /**
         * Returns a string representation of the GeoPoint object.
         *
         * @return a string representation of the GeoPoint object
         */
        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }

        /**
         * Checks if this GeoPoint object is equal to another object.
         *
         * @param o the object to compare with
         * @return true if the objects are equal, false otherwise
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint geoPoint)) return false;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        /**
         * Returns the hash code value for the GeoPoint object.
         *
         * @return the hash code value for the GeoPoint object
         */
        @Override
        public int hashCode() {
            return Objects.hash(geometry, point);
        }
    }

    /**
     * Finds the intersections between a ray and the geometry object.
     *
     * @param ray the ray to intersect with the object
     * @return a list of intersection points, or null if there are no intersections
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * Finds the geometric intersections between a ray and the geometry object.
     *
     * @param ray the ray to intersect with the object
     * @return a list of geometric intersection points, or null if there are no intersections
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        //initialize the max distance as a positive infinity
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }
    /**
     * Finds the geometric intersections between a ray and the geometry object.
     *
     * @param ray the ray to intersect with the object
     * @param maxDistance the maximum distance between the ray and the point
     * @return a list of geometric intersection points, or null if there are no intersections
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }



    /**
     * Helper method for finding the geometric intersections between a ray and the geometry object.
     * Subclasses must implement this method.
     *
     * @param ray the ray to intersect with the object
     * @param maxDistance the maximum distance between the ray and the point
     * @return a list of geometric intersection points, or null if there are no intersections
     */

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

}
