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
        Vup=up;
        Vup.normalize();
        Vto=to;
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
    // ***************** Operations ******************** //

    /**
     * this function gets the view plane size and a selected pixel,
     * and return the ray from the camera which intersects this pixel
     *
     * @param nX - amount of rows in view plane (number of pixels)
     * @param nY - amount of columns in view plane (number of pixels)
     * @param j  - X's index
     * @param i  - Y's index
     * @return - the ray which goes through the pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {

        //calculate the view plane center Point
        Point Pcenter = p0.add(Vto.scale(distance));

        //calculate the height and the width of any pixel
        double Ry = height / nY;
        double Rx = width / nX;

        //delta values for moving on the view plane
        double Xj = (j - (nX - 1) / 2d) * Rx;
        double Yi = -(i - (nY - 1) / 2d) * Ry;


        //initialize Pij point[i,j] in view-plane coordinates
        Point Pij = Pcenter;

        if (!isZero(Xj)) {
            Pij = Pij.add(Vright.scale(Xj));
        }
        if (!isZero(Yi)) {
            Pij = Pij.add(Vup.scale(Yi));
        }

        // vector from camera's eye in the direction of point(i,j) in the viewplane
        Vector Vij = Pij.subtract(p0);

        return new Ray(p0, Vij);

    }
}
