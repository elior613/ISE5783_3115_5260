package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {
    protected Ray axisRay;
    public Tube(double radius,Ray axis) {
        super(radius);
        axisRay=axis;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
