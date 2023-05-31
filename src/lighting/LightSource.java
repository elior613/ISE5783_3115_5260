/**

 The LightSource interface represents a light source in a scene.
 It defines methods for retrieving the intensity of the light at a given point and the direction of the light from the point.
 */
package lighting;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

public interface LightSource {
    /**
     * Returns the intensity of the light at the specified point.
     *
     * @param p the point to calculate the intensity at
     * @return the intensity of the light at the point
     */
    public Color getIntensity(Point p);
    /**
     * Returns the direction of the light from the specified point.
     *
     * @param p the point to calculate the light direction from
     * @return the direction of the light from the point
     */
    public Vector getL(Point p);

    public double getDistance(Point point);


}
