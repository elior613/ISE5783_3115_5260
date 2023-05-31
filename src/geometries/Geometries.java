package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;


/**

 The Geometries class represents a collection of intersectable geometric objects.
 It extends the Intersectable class and provides methods for finding intersections with the objects in the collection.
 */

public class Geometries extends Intersectable {

    /**

     The geometryList represents the list of intersectable objects in the Geometries collection.
     It stores the geometries that can be intersected with rays.
     */
    private List<Intersectable> geometryList;

    /**
     * Constructs an empty Geometries object.
     */
    public Geometries() {
        geometryList = new LinkedList<>();
    }

    /**
     * Constructs a Geometries object with the specified intersectable objects.
     *
     * @param geometries the intersectable objects to add to the collection
     */
    public Geometries(Intersectable... geometries) {
        this.geometryList = List.of(geometries);
    }

    /**
     * Adds the specified intersectable objects to the collection.
     *
     * @param geometries the intersectable objects to add
     */
    public void add(Intersectable... geometries) {
        for (Intersectable element : geometries) {
            this.geometryList.add(element);
        }
    }

    /**
     * Finds the geometric intersections between the given ray and the objects in the collection.
     *
     * @param ray the ray to intersect with the objects
     * @return a list of geometric intersection points, or null if there are no intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        // If the list has no geometries
        if (geometryList == null)
            return null;

        List<GeoPoint> totalIntersections = new LinkedList<>();

        // Iterate over all the geometries in the list
        for (Intersectable geometry : geometryList) {
            // Find the intersection points
            List<GeoPoint> intersections = geometry.findGeoIntersectionsHelper(ray,maxDistance);
            // If there are intersections
            if (intersections != null)
                // Add the intersection points to the total list
                totalIntersections.addAll(intersections);
        }
        // If there are no intersection points
        if (totalIntersections.size() == 0)
            // Return null
            return null;
        return totalIntersections;
    }


}
