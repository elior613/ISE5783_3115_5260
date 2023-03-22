package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
    private Point q0;
    private Vector normal;

    public Plane(Point q1,Point q2,Point q3) {
        q0 = q1;
        normal=null;
    }

    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    public Point getPoint() {
        return q0;
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
