package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight {

    Color intensity;
    public static final AmbientLight NONE;
 //   static const  AmbientLight NONE;

    public AmbientLight(Color Ia, Double3 Ka) {
        this.intensity = Ia.scale(Ka);
    }
    public AmbientLight(Color Ia, double Ka) {
        this.intensity = Ia.scale(Ka);
    }
    static
    {
        NONE =new AmbientLight(Color.BLACK,Double3.ZERO);
    }

    public Color getIntensity() {
        return intensity;
    }

}
