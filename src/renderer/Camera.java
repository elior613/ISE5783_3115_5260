package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera
{
    private Point p0;
    private Vector Vto;
    private Vector Vup;
    private Vector Vright;
    private double  width;
    private double height;
    private double distance;

    public Camera(Point p,Vector up,Vector to){
        if(!isZero(up.dotProduct(to)))
            throw new IllegalArgumentException("the vectors are not orthogonal");
        Vup=up.normalize();
        Vto=to.normalize();
        Vright=Vup.crossProduct(Vto);
        p0=p;
    }

    public Point getP0() {
        return p0;
    }

    public Vector getVto() {
        return Vto;
    }

    public Vector getVup() {
        return Vup;
    }

    public Vector getVright() {
        return Vright;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDistance() {
        return distance;
    }

    public Camera setVPSize (double width, double height)
    {
        if(width<0||height<0)
            throw new IllegalArgumentException("ERROR value parameter of view plane");
        this.width=width;
        this.height=height;
        return this;
    }
    public Camera setVPDistance (double distance)
    {
        if(distance<0)
            throw new IllegalArgumentException("ERROR value parameter of the distance");
        this.distance=distance;
        return this;
    }
    public Ray constructRay(int nX, int nY, int j, int i){
        return null;
    }

}
