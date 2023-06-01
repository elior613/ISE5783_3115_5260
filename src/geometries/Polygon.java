package geometries;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/** Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * @author Dan */
public class Polygon extends Geometry {
   /** List of polygon's vertices */
   protected final List<Point> vertices;
   /** Associated plane in which the polygon lays */
   protected final Plane       plane;
   private final int           size;

   /** Polygon constructor based on vertices list. The list must be ordered by edge
    * path. The polygon must be convex.
    * @param  vertices                 list of vertices according to their order by
    *                                  edge path
    * @throws IllegalArgumentException in any case of illegal combination of
    *                                  vertices:
    *                                  <ul>
    *                                  <li>Less than 3 vertices</li>
    *                                  <li>Consequent vertices are in the same
    *                                  point
    *                                  <li>The vertices are not in the same
    *                                  plane</li>
    *                                  <li>The order of vertices is not according
    *                                  to edge path</li>
    *                                  <li>Three consequent vertices lay in the
    *                                  same line (180&#176; angle between two
    *                                  consequent edges)
    *                                  <li>The polygon is concave (not convex)</li>
    *                                  </ul>
    */
   public Polygon(Point... vertices) {
      if (vertices.length < 3)
         throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
      this.vertices = List.of(vertices);
      size          = vertices.length;

      // Generate the plane according to the first three vertices and associate the
      // polygon with this plane.
      // The plane holds the invariant normal (orthogonal unit) vector to the polygon
      plane         = new Plane(vertices[0], vertices[1], vertices[2]);
      if (size == 3) return; // no need for more tests for a Triangle

      Vector  n= plane.getNormal();
      // Subtracting any subsequent points will throw an IllegalArgumentException
      // because of Zero Vector if they are in the same point
      Vector  edge1    = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
      Vector  edge2    = vertices[0].subtract(vertices[vertices.length - 1]);

      // Cross Product of any subsequent edges will throw an IllegalArgumentException
      // because of Zero Vector if they connect three vertices that lay in the same
      // line.
      // Generate the direction of the polygon according to the angle between last and
      // first edge being less than 180 deg. It is hold by the sign of its dot product
      // with
      // the normal. If all the rest consequent edges will generate the same sign -
      // the
      // polygon is convex ("kamur" in Hebrew).
      boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
      for (var i = 1; i < vertices.length; ++i) {
         // Test that the point is in the same plane as calculated originally
         if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
            throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
         // Test the consequent edges have
         edge1 = edge2;
         edge2 = vertices[i].subtract(vertices[i - 1]);
         if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
            throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
      }
   }

   /**
    * Returns the normal vector to the polygon at a given point (which is on the plane)
    * @param point The point on the polygon
    * @return The normal vector to the polygon at the given point
    */
   @Override
   public Vector getNormal(Point point) { return plane.getNormal(); }


   /**
    * Finds the intersections between the current polygon and a given ray.
    *
    * @param ray The ray to find intersections with.
    * @param maxDistance the maximum distance between the ray and the point
    * @return A list of points representing the intersection points between the polygon and the ray, or null if there are no intersections.
    */
   @Override
   public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
      List<GeoPoint> intersection = this.plane.findGeoIntersections(ray,maxDistance);

      // If there are no intersections with the plane of the triangle, return null (0 points intersection).
      if (intersection == null) {
         return null;
      }

      // Get the direction vector of the given ray and its starting point.
      Vector v = ray.getDirection();
      Point p0 = ray.getP0();

      // Calculate the n-1 edge vectors of the triangle.
      List<Vector> p0Vectors = new LinkedList<Vector>();
      for (Point p : vertices) {
         p0Vectors.add(p.subtract(ray.getP0()));
      }

      // Calculate the normalized cross products of the edge vectors.
      List<Vector> crossVectors = new ArrayList<Vector>();
      int count = 0;
      int max = p0Vectors.size() - 1;
      for (Vector vect : p0Vectors) {
         if (count != max) {
            crossVectors.add(vect.crossProduct(p0Vectors.get(count + 1)).normalize());
         }
         count++;
      }
      //add the  Vn v1 cross product to the crossVectors list and normalized it
      crossVectors.add(p0Vectors.get(max).crossProduct(p0Vectors.get(0)).normalize());


      // sum the number of positive or negative values of the dot product
      count = 0;
      for (Vector cross : crossVectors) {
         double t = alignZero(v.dotProduct(cross));
         if (t > 0)
            count++;//count that the dot is positive
         if (t < 0)
            count--;//count that the dot is negative
      }


      // If the signs of the dot products are all positive or all negative, the ray intersects the triangle.
      if (count == vertices.size() || count == -vertices.size()) {
         // The ray intersects the polygon.
         return  List.of(new GeoPoint(this,intersection.get(0).point));
      }
      return null;
   }
}
