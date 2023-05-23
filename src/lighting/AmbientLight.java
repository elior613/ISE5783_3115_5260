package lighting;

import primitives.Color;
import primitives.Double3;
/**

 The AmbientLight class represents ambient light in a scene.
 It is a type of light that provides a uniform illumination across all objects in the scene.
 */
public class AmbientLight extends Light {

    /**
     * A constant representing no ambient light (fully dark).
     */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * Constructs an AmbientLight object with the specified intensity and ambient coefficient.
     *
     * @param Ia the intensity of the ambient light
     * @param Ka the ambient coefficient
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        super(Ia.scale(Ka));
    }

    /**
     * Constructs an AmbientLight object with the specified intensity and ambient coefficient.
     * This constructor is used when the ambient coefficient is a scalar value.
     *
     * @param Ia the intensity of the ambient light
     * @param Ka the ambient coefficient
     */
    public AmbientLight(Color Ia, double Ka) {
        super(Ia.scale(Ka));
    }


}
