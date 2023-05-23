package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
/**

 The DirectionalLight class represents a directional light source in a scene.
 It emits light in a specific direction, providing illumination to all objects in that direction.
 It implements the LightSource interface.
 */

public class DirectionalLight extends Light implements LightSource{
    /**

     The direction of the light.
     */
    private Vector direction;

    /**
     * Constructs a DirectionalLight object with the specified intensity and direction.
     *
     * @param intensity the intensity of the directional light
     * @param direction the direction of the light
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }

}
