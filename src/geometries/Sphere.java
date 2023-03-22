package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry {

    private Point center;
    public Sphere(double radius,Point c) {
        super(radius);
        center=c;
    }

    public Point getCenter() {
        return center;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
