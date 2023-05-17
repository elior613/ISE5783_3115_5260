package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Intersectable {
    public static class GeoPoint {
        final public Geometry geometry;
        final public Point point;
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint geoPoint)) return false;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        @Override
        public int hashCode() {
            return Objects.hash(geometry, point);
        }
    }
     public final List<Point> findIntersections(Ray ray){
        List<GeoPoint> geopoints = findGeoIntersections(ray);
        if(geopoints == null)
            return null;


        return geopoints.stream()
                .map(elem -> elem.point)
                .collect(Collectors.toList());
    }
     public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

     protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);


}
