/**

 The Camera class represents a camera in 3D space.
 */
package renderer;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

public class Camera
{
    private Point p0; // The position of the camera's center
    private Vector Vto; // The direction vector towards which the camera is pointing
    private Vector Vup; // The up vector
    private Vector Vright; // The right vector
    private double width; // The width of the view plane
    private double height; // The height of the view plane
    private double distance; // The distance between the camera and the view plane
    private ImageWriter imageWriter;//write the image on the view plane
    private RayTracerBase rayTracerBase;//include the scene

    /**
     * Constructs a new Camera object.
     *
     * @param p  The position of the camera's center
     * @param to The direction vector towards which the camera is pointing
     * @param up The up vector
     */
    public Camera(Point p, Vector to, Vector up){
        //checks if the 2 vectors are orthogonal
        if(!isZero(up.dotProduct(to)))
            throw new IllegalArgumentException("the vectors are not orthogonal");

        Vup = up;
        Vup.normalize();
        Vto = to;
        Vright = Vup.crossProduct(Vto);
        p0 = p;
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

    /**
     * Sets the size of the view plane.
     *
     * @param width  The width of the view plane
     * @param height The height of the view plane
     * @return The Camera object, for chaining method calls
     */
    public Camera setVPSize(double width, double height)
    {
        if (width < 0 || height < 0)
            throw new IllegalArgumentException("ERROR value parameter of view plane");
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Sets the distance between the camera and the view plane.
     *
     * @param distance The distance between the camera and the view plane
     * @return The Camera object, for chaining method calls
     */
    public Camera setVPDistance(double distance)
    {
        if (distance < 0)
            throw new IllegalArgumentException("ERROR value parameter of the distance");
        this.distance = distance;
        return this;
    }

    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    public Camera setRayTracerBase(RayTracerBase rayTracerBase) {
        this.rayTracerBase = rayTracerBase;
        return this;
    }

    // ***************** Operations ******************** //

    /**
     * the function gets the view plane size and a specific pixel,
     * return the ray from the camera which intersects this pixel
     *
     * @param nX - row amount in the view plane
     * @param nY - column amount in the view plane
     * @param j  - The index of X
     * @param i  - The index of Y
     * @return - the ray which goes through the pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {

        //calculate the view plane center Point
        Point Pcenter = p0.add(Vto.scale(distance));

        //calculate the height and the width of any pixel
        double Ry = height / nY;
        double Rx = width / nX;

        //delta values for moving on the view plane
        double Xj = -(j - (nX - 1) / 2d) * Rx;
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

    public void renderImage(){
        if(p0==null)
            throw new MissingResourceException("missing resource", Point.class.getName(), "");
        if(Vto==null)
            throw new MissingResourceException("missing resource", Vector.class.getName(), "");
        if(Vup==null)
            throw new MissingResourceException("missing resource", Vector.class.getName(), "");
        if(Vright==null)
            throw new MissingResourceException("missing resource", Vector.class.getName(), "");
        if(width==0)
            throw new MissingResourceException("missing resource", Double.class.getName(), "");
        if(height==0)
            throw new MissingResourceException("missing resource", Double.class.getName(), "");
        if(distance==0)
            throw new MissingResourceException("missing resource", Double.class.getName(), "");
        if(imageWriter==null)
            throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
        if(rayTracerBase==null)
            throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                imageWriter.writePixel(i,j,castRay(nX, nY, i, j));
            }
        }
    }
    public void printGrid(int interval, Color color){
        double Nx=imageWriter.getNx();
        double Ny=imageWriter.getNy();

        if(imageWriter==null)
            throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
        for (int i=0;i<Nx;i++)//scan the width
        {
            for(int j=0;j<Ny;j++)
            {
                if (j % interval==0 || i % interval==0)
                {
                    imageWriter.writePixel(i,j,color);
                }
            }
        }
        imageWriter.writeToImage();
    }

    public Camera writeToImage(){
        if(imageWriter==null)
            throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
        imageWriter.writeToImage();
        return this;
    }
}
