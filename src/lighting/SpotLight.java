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
    protected SpotLight(Color intensity, Point position) {
        super(intensity, position);
    }

    @Override
    public Color getIntensity(Point p) {
        // Calculate the spotlight intensity
        Vector l = getL(p);
        double dirL = direction.dotProduct(l);
        // If the direction of the right position is greater than zero
        if (dirL > 0) {
            return getIntensity().scale(dirL).reduce(getDenominatorLight(p));
        } else {
            return getIntensity().scale(0);
        }
    }

}
