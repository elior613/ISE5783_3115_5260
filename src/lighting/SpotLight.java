package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
/**

 The SpotLight class represents a spot light source in a scene.
 It is a type of point light that emits light in a specific direction within a cone-shaped region.
 It extends the PointLight class.
 */

public class SpotLight extends PointLight{

    private Vector direction;

    /**
     * Constructs a SpotLight object with the specified intensity and position.
     *
     * @param intensity the intensity of the spot light
     * @param position  the position of the light source
     */
    public SpotLight(Color intensity, Point position,Vector direction) {
        super(intensity, position);
        this.direction=direction;
    }

    /**

     Calculates the intensity of the spot light at the specified point.
     @param p the point at which to calculate the intensity
     @return the intensity of the spot light at the specified point
     */
    @Override
//    public Color getIntensity(Point p) {
//        // Calculate the spotlight intensity
//        Vector l = getL(p);
//        double dirL = direction.dotProduct(l);
//        // If the direction of the right position is greater than zero
//        if (dirL > 0) {
//            return getIntensity().scale(dirL).reduce(getDenominatorLight(p));
//        } else {
//            return getIntensity().scale(0);
//        }
//    }


    public SpotLight setNarrowBeam(int i) {
        return this;
    }
}
