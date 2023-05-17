package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {

    private List<Intersectable> geometryList;

    public Geometries() {
        geometryList = new LinkedList<Intersectable>();
    }

    public Geometries(Intersectable... geometries) {
        this.geometryList = List.of(geometries);

    }

    public void add(Intersectable... geometries) {
        for (Intersectable element : geometries) {
            this.geometryList.add(element);
        }
    }

    /**
     * Finds the intersections of the given ray with the geometries that we have in the list.
     * Returns a list of points representing the intersections.
     * If there are no intersections, the method returns null.
     *
     * @param ray The ray to intersect with the plane.
     * @return A list of points representing the intersections, or null if there are no intersections.
     */

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        //if the list has no geometries
        if (geometryList == null)
            return null;

        List<GeoPoint> totalIntersections = new LinkedList<>();

        //iterate over all the geometries in the list
        for (Intersectable geometry : geometryList) {
            //find the intersection points
            List<GeoPoint> intersections = geometry.findGeoIntersections(ray);
            //if there are intersections
            if (intersections != null)
                //add the intersection points to the total list
                totalIntersections.addAll(intersections);
        }
        //if there are no intersection points
        if (totalIntersections.size() == 0)
            //return the list of intersection points
            return null;
        return totalIntersections;
    }

}
