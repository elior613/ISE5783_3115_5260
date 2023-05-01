
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

    public List<Point> findIntersections(Ray ray) {
        List<Point> intersection = this.plane.findIntersections(ray);

        //no intersections with the plane of the triangle, 0 points intersection.
        if (intersection != null) {

            Vector v = ray.getDirection();
            Point p0 = ray.getP0();

            //vd represents the ray dot product normal of the triangle
            //double vd=v.dotProduct(plane.getNormal());

            Vector v1 = this.vertices.get(0).subtract(ray.getP0());
            Vector v2 = this.vertices.get(1).subtract(ray.getP0());
            Vector v3 = this.vertices.get(2).subtract(ray.getP0());

            Vector v1CrossProdV2 = v1.crossProduct(v2);
            Vector v2CrossProdV3 = v2.crossProduct(v3);
            Vector v3CrossProdV1 = v3.crossProduct(v1);

            Vector n1 = v1CrossProdV2.normalize();
            Vector n2 = v2CrossProdV3.normalize();
            Vector n3 = v3CrossProdV1.normalize();

            double vN1 = v.dotProduct(n1);
            double vN2 = v.dotProduct(n2);
            double vN3 = v.dotProduct(n3);

            if (vN1 > 0 && vN2 > 0 && vN3 > 0 || vN1 < 0 && vN2 < 0 && vN3 < 0) {
                //if we entered to hear, the ray intersect the triangle
                return intersection;

            }
        }
        return null;
    }
}