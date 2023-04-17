
package geometries;

/**
 * The abstract class representing radial geometries in three-dimensional space.
 * A radial geometry is a geometry that has a fixed radius.
 */
public abstract class RadialGeometry implements Geometry {
    /**
     * The radius of the radial geometry.
     */
    protected double radius;
    /**
     * Constructs a new radial geometry with the specified radius.
     * @param radius the radius of the geometry
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }

    /**
     * Gets the radius of the radial geometry.
     * @return the radius of the geometry
     */
    public double getRadius() {
        return radius;
    }
}
