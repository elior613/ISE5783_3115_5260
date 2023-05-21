package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight{

private Vector direction;
    protected SpotLight(Color intensity, Point position) {
        super(intensity, position);
    }
}
