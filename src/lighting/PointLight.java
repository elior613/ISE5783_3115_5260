package lighting;


import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource{
    final Point position;
    private  double kC;
    private  double kA;
    private double kQ;

    protected PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setkA(double kA) {
        this.kA = kA;
        return this;
    }

    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        return null;
    }

    @Override
    public Vector getL(Point p) {
        return null;
    }
}
