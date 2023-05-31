package lighting;


import primitives.Color;
import primitives.Point;
import primitives.Vector;
/**

 The PointLight class represents a point light source in a scene.
 It emits light from a specific point in all directions, providing illumination to surrounding objects.
 It extends the Light class and implements the LightSource interface.
 */
public class PointLight extends Light implements LightSource{
    /**

     The position of the point light source.
     */
    final Point position;
    /**

     The constant attenuation factor of the point light.
     */
    private double kC = 1;
    /**

     The linear attenuation factor of the point light.
     */
    private double kL = 0;
    /**

     The quadratic attenuation factor of the point light.
     */
    private double kQ = 0;

    /**
     * Constructs a PointLight object with the specified intensity and position.
     *
     * @param intensity the intensity of the point light
     * @param position  the position of the light source
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * Sets the constant attenuation factor of the point light.
     *
     * @param kC the constant attenuation factor
     * @return the updated PointLight object
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Sets the linear attenuation factor of the point light.
     *
     * @param kL the linear attenuation factor
     * @return the updated PointLight object
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Sets the quadratic attenuation factor of the point light.
     *
     * @param kQ the quadratic attenuation factor
     * @return the updated PointLight object
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        return getIntensity().reduce(getDenominatorLight(p));
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    /**
     * Calculates the denominator of the light intensity equation at the specified point.
     *
     * @param p the point to calculate the denominator for
     * @return the denominator of the light intensity equation
     */
    protected double getDenominatorLight(Point p) {
        double distance = p.distance(position);
        return kC +
                kL * distance +
                kQ * distance * distance;
    }
    @Override
    public double getDistance(Point point) {
        return point.distance(this.position);
    }

}
