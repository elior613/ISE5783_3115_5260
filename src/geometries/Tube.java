
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Represents a tube in 3D space, which is defined by a radius and an axis ray.
 */
public class Tube extends RadialGeometry {

    /**
     * The axis ray of the tube.
     */
    protected Ray axisRay;

    /**
     * Constructs a new Tube object with the specified radius and axis ray.
     * @param radius The radius of the tube.
     * @param axis The axis ray of the tube.
     */
    public Tube(double radius, Ray axis) {
        super(radius);
        axisRay = axis;
    }

    /**
     * Returns the axis ray of the tube.
     * @return The axis ray of the tube.
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * return a normal vector to the tube in the point p, if there
     * @param p
     * @return Vector (normal vector)
     */
    @Override
    public Vector getNormal(Point p) {
        Vector v = axisRay.getDirection();
        Point p0 = axisRay.getP0();

        if(p0.equals(p)){
            throw new IllegalArgumentException("Point p cannot equal to tube reference point");
        }

        //calculating the vector between the point of the ray and the point that we get in the function
        Vector P0_P = p.subtract(p0);
        //if P0P parallel to v
        try{
            v.crossProduct(P0_P);
        }
        //the point is on the ray. we can't calculate a normal
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Point p cannot be on the axis ");
        }

        // calculating the value of the scalar of the direction vector
        //v.dotProduct(P0_P) is the projection
        double t = alignZero(v.dotProduct(P0_P));

        //the point not on the base
        if(t != 0){
            // from the point o we calculate the normal
            Point o = p0.add(v.scale(t));
            return p.subtract(o).normalize();
        }
        //the point on the base
           return P0_P.normalize();
    }
   @Override
   public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance){
        return null;
    }
}
