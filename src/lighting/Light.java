package lighting;

import primitives.Color;
/**

 The Light class is an abstract base class for different types of lights in a scene.
 It provides a common structure for lights and holds the intensity of the light.
 */

abstract class Light {

    /**

     The intensity of the light.
     */
   private final Color intensity;

    /**
     * Constructs a Light object with the specified intensity.
     *
     * @param intensity the intensity of the light
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Returns the intensity of the light.
     *
     * @return the intensity of the light
     */
    public Color getIntensity() {
        return intensity;
    }

}
