
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Represents a triangle in 3D Cartesian coordinate system.
 * Inherits from the {@link Polygon} class.
 */
public class Triangle extends Polygon {

    /**
     * Constructs a triangle with three vertices.
     *
     * @param p1 The first vertex.
     * @param p2 The second vertex.
     * @param p3 The third vertex.
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    /**
     * Finds the intersections between the current triangle and a given ray.
     *
     * @param ray The ray to find intersections with.
     * @return A list of points representing the intersection points between the triangle and the ray, or null if there are no intersections.
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> intersection = this.plane.findGeoIntersections(ray, maxDistance);

        // If there are no intersections with the plane of the triangle, return null (0 points intersection).
        if (intersection != null) {

            // Get the direction vector of the given ray and its starting point.
            Vector v = ray.getDirection();
            Point p0 = ray.getP0();

            // Calculate the three edge vectors of the triangle.
            Vector v1 = this.vertices.get(0).subtract(ray.getP0());
            Vector v2 = this.vertices.get(1).subtract(ray.getP0());
            Vector v3 = this.vertices.get(2).subtract(ray.getP0());

            // Calculate the cross products of the edge vectors.
            Vector v1CrossProdV2 = v1.crossProduct(v2);
            Vector v2CrossProdV3 = v2.crossProduct(v3);
            Vector v3CrossProdV1 = v3.crossProduct(v1);

            // Calculate the normal vectors of the triangle.
            Vector n1 = v1CrossProdV2.normalize();
            Vector n2 = v2CrossProdV3.normalize();
            Vector n3 = v3CrossProdV1.normalize();

            // Calculate the dot products between the ray direction vector and the normal vectors of each edge of the triangle.
            double vN1 = v.dotProduct(n1);
            double vN2 = v.dotProduct(n2);
            double vN3 = v.dotProduct(n3);

            // If the signs of the dot products are all positive or all negative, the ray intersects the triangle.
            if (vN1 > 0 && vN2 > 0 && vN3 > 0 || vN1 < 0 && vN2 < 0 && vN3 < 0) {
                return  List.of(new GeoPoint(this,intersection.get(0).point));
            }
        }

        return null; // There are no intersections between the triangle and the ray.
    }

}