package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{

    private List<Intersectable> geometryList;
    public Geometries(){
        List<Intersectable> list=new LinkedList<Intersectable>();
    }
    public Geometries(Intersectable...geometries){
        this.geometryList = List.of(geometries);

    }
    public void add(Intersectable...geometries) {
        if (geometries==null)
            return;
        for (Intersectable element : geometries) {
            this.geometryList.add(element);
        }
    }
    /**

     Finds the intersections of the given ray with the geometries that we have in the list.
     Returns a list of points representing the intersections.
     If there are no intersections, the method returns null.

     @param ray The ray to intersect with the plane.

     @return A list of points representing the intersections, or null if there are no intersections.
     */
    public List<Point> findIntersections(Ray ray){
        List<Point> totalIntersections=new LinkedList<Point>();
        //if the list has no geometries
        if(geometryList ==null)
            return null;

        //iterate over all the geometries in the list
        for (Intersectable geometry: geometryList) {
            //find the intersection points
            List<Point>intersections=geometry.findIntersections(ray);
            //if there are intersections
            if(intersections!=null)
                //add the intersection points to the total list
                totalIntersections.addAll(intersections);
        }
        //if there are no intersection points
        if(totalIntersections.size()==0)
            //return the list of intersection points
            return null;
        return totalIntersections;
    }

}
