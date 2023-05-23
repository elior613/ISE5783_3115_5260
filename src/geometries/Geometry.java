

/**
 The Geometry interface represents geometric shapes in 3D space.
 Any class that implements this interface must provide an implementation for the getNormal method.
 */
package geometries;
        import primitives.*;

        import java.util.List;

public abstract class Geometry extends Intersectable {
    /**
     * The emission color of the geometry. Default value is Color.BLACK.
     */
    protected Color emission=Color.BLACK;
    private Material material=new Material();

    public Material getMaterial() {
        return material;
    }


    /**
     * Returns the emission color of the geometry.
     *
     * @return the emission color of the geometry
     */
    public Color getEmission() {
        return emission;
    }
    /**
     * Sets the emission color of the geometry.
     *
     * @param emission the emission color to set
     * @return the geometry with the updated emission color
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * Returns a normal vector to the geometry shape at the specified point.
     *
     * @param p the point on the shape to get the normal vector at
     * @return the normal vector to the geometry shape at the specified point
     */
    public abstract Vector getNormal(Point p);




}
