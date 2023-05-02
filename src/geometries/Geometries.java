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
        for (Intersectable element : geometries) {
            geometryList.add(element);
        }
    }
    public List<Point> findIntersections(Ray ray){
        List<Point> totalIntersections=new LinkedList<Point>();
        if(geometryList ==null)
            return null;
        for (Intersectable geometry: geometryList) {
            List<Point>intersections=geometry.findIntersections(ray);
            if(intersections!=null)
                totalIntersections.addAll(intersections);
        }
        if(totalIntersections.size()==0)
            return null;
        return totalIntersections;
    }

}
