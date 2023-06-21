/**
 * The Camera class represents a camera in 3D space.
 */
package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.*;

import static primitives.Color.almostSameColor;
import static primitives.Util.isZero;

public class Camera {
    // The position of the camera's center
    private Point p0;

    // The direction vector towards which the camera is pointing
    private Vector Vto;

    // The up vector
    private Vector Vup;

    // The right vector
    private Vector Vright;

    // The width of the view plane
    private double width;
    // The height of the view plane
    private double height;
    // The distance between the camera and the view plane
    private double distance;

    //write the image on the view plane
    private ImageWriter imageWriter;

    //include the scene
    private RayTracerBase rayTracer;

    //amount of Rays for anti aliasing. Default is 1
    private int amountRays = 1;

    private boolean isAdaptiveSuperSampling= false;

    private int maximumAdaptiveDepth =4;



    /**
     * Constructs a new Camera object.
     *
     * @param p  The position of the camera's center
     * @param to The direction vector towards which the camera is pointing
     * @param up The up vector
     */
    public Camera(Point p, Vector to, Vector up) {
        //checks if the 2 vectors are orthogonal
        if (!isZero(up.dotProduct(to)))
            throw new IllegalArgumentException("the vectors are not orthogonal");

        Vup = up;
        Vup.normalize();
        Vto = to;
        Vright = Vup.crossProduct(Vto);
        p0 = p;
    }

    /**
     * Returns the origin point of the camera.
     *
     * @return the origin point of the camera
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Returns the view vector of the camera.
     *
     * @return the view vector of the camera
     */
    public Vector getVto() {
        return Vto;
    }

    /**
     * Returns the up vector of the camera.
     *
     * @return the up vector of the camera
     */
    public Vector getVup() {
        return Vup;
    }

    /**
     * Returns the right vector of the camera.
     *
     * @return the right vector of the camera
     */
    public Vector getVright() {
        return Vright;
    }

    /**
     * Returns the width of the view plane of the camera.
     *
     * @return the width of the view plane
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the view plane of the camera.
     *
     * @return the height of the view plane
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the distance between the camera and the view plane.
     *
     * @return the distance between the camera and the view plane
     */
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
    public Camera setVPSize(double width, double height) {
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
    public Camera setVPDistance(double distance) {
        if (distance < 0)
            throw new IllegalArgumentException("ERROR value parameter of the distance");

        this.distance = distance;

        return this;
    }

    /**
     * chaining set method for the imageWriter
     * Sets the image writer for the camera.
     *
     * @param imageWriter the image writer to be set
     * @return the camera instance with the updated image writer
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * Sets the ray tracer for the camera.
     *
     * @param rayTracerBase the ray tracer to be set
     * @return the camera instance with the updated ray tracer
     */
    public Camera setRayTracer(RayTracerBase rayTracerBase) {
        this.rayTracer = rayTracerBase;
        return this;
    }

    /**
     * Sets the amount of rays of the camera for anti- aliasing .
     *
     * @param  amountRays amont of race
     * @return the camera instance with the updated amount of rays t
     */

    public Camera setAmountRays(int amountRays) {
        this.amountRays = amountRays;
        return this;
    }

    /**
     * Sets the boolean of the adaptive super sampaling  .
     *
     * @param  adaptiveSuperSampling amont of race
     * @return the camera instance with the updated boolean of adaptiveSuperSampeling
     */
    public Camera setAdaptiveSuperSampling(boolean adaptiveSuperSampling) {
        isAdaptiveSuperSampling = adaptiveSuperSampling;

        if (adaptiveSuperSampling==true) {
            //initialize the amoutRays to 1 that will help to use just one feature
            amountRays = 1;
        }

        return this;
    }

    /**
     * Sets the value of the maximumAdaptive.
     *
     * @param  maximumAdaptiveDepth amonut of the maximum adaptive super sampling
     * @return the camera instance with the updated boolean of adaptiveSuperSampeling
     */
    public Camera setMaximumAdaptiveDepth(int maximumAdaptiveDepth) {
        this.maximumAdaptiveDepth = maximumAdaptiveDepth;
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

    /**
     * Renders the image using the specified camera settings.
     *
     * @throws MissingResourceException if any required resource is missing
     */
    public Camera renderImage() {
        // Check if all required resources are available
        if (p0 == null)
            throw new MissingResourceException("missing resource", Point.class.getName(), "");
        if (Vto == null)
            throw new MissingResourceException("missing resource", Vector.class.getName(), "");
        if (Vup == null)
            throw new MissingResourceException("missing resource", Vector.class.getName(), "");
        if (Vright == null)
            throw new MissingResourceException("missing resource", Vector.class.getName(), "");
        if (width == 0)
            throw new MissingResourceException("missing resource", Double.class.getName(), "");
        if (height == 0)
            throw new MissingResourceException("missing resource", Double.class.getName(), "");
        if (distance == 0)
            throw new MissingResourceException("missing resource", Double.class.getName(), "");
        if (imageWriter == null)
            throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
        if (rayTracer == null)
            throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");

        // Render each pixel in the image
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                imageWriter.writePixel(j, i, this.castRaySelector(nX, nY, i, j));
            }
        }
        return this;
    }


    /**
     * the function gets the view plane size and a specific pixel,
     * decide in which way to return the color of the pixel.
     *
     * @param nX - row amount in the view plane
     * @param nY - column amount in the view plane
     * @param j  - The index of X
     * @param i  - The index of Y
     * @return - the color of the pixel
     */

    private Color castRaySelector(int nX, int nY, int i, int j) {
        if (isAdaptiveSuperSampling)
            return adaptiveManager(nX, nY, j, i);
        if (amountRays == 1)
            return castRay(nX, nY, i, j);
        //amountRays is bigger than 1
        return castRays(nX, nY, i, j);
    }

    /**
     * Casts a ray for the specified pixel and traces it to determine the pixel color.
     *
     * @param nX the number of pixels along the x-axis
     * @param nY the number of pixels along the y-axis
     * @param i  the y-coordinate of the pixel
     * @param j  the x-coordinate of the pixel
     */
    private Color castRay(int nX, int nY, int i, int j) {
        Ray ray = constructRay(nX, nY, j, i);
        return rayTracer.traceRay(ray);
    }

    /**
     * Casts rays for the specified pixel and traces it to determine the pixel color by amountRays*amountRays.
     *
     * @param nX the number of pixels along the x-axis
     * @param nY the number of pixels along the y-axis
     * @param i  the y-coordinate of the pixel
     * @param j  the x-coordinate of the pixel
     */
    private Color castRays(int nX, int nY, int i, int j) {
        List<Ray> rays = constructRays(nX, nY, j, i);
        return rayTracer.traceRays(rays);
    }

    /**
     * Prints a grid on the image with the specified interval and color.
     *
     * @param interval the interval at which the grid lines should be drawn
     * @param color    the color of the grid lines
     * @throws MissingResourceException if the image writer is missing
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
        double nX = imageWriter.getNx();
        double nY = imageWriter.getNy();
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                if (j % interval == 0 || i % interval == 0) {
                    imageWriter.writePixel(i, j, color);
                }
            }
        }
        imageWriter.writeToImage();
    }

    /**
     * Writes the rendered image to the image writer.
     *
     * @return the camera instance with the rendered image written to the image writer
     * @throws MissingResourceException if the image writer is missing
     */
    public Camera writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
        imageWriter.writeToImage();
        return this;
    }



    /**
     * the function gets the view plane size and a specific pixel,
     * return the point of the center of the pixel
     *
     * @param nX - row amount in the view plane
     * @param nY - column amount in the view plane
     * @param j  - The index of X
     * @param i  - The index of Y
     * @return - the ray which goes through the pixel
     */
    private Point getCenterPixel(int nX, int nY, int j, int i) {
        Point pIJCenter = p0.add(Vto.scale(distance)); // = initialize the center point of the pixel
        double rY = this.height / nY; //rY is the size of the vertical
        double rX = this.width / nX; //rX is the size of the horizontal
        double xJ = -(j - (double) (nX - 1) / 2) * rX; //xJ is the horizontal distance of the central pixel from our pixel
        double yI = -(i - (double) (nY - 1) / 2) * rY; //yI is the vertical distance of the central pixel from our pixel
        //we check if it needed to move the  point from the first position
        if (xJ != 0) pIJCenter = pIJCenter.add(Vright.scale(xJ));//add to the horizontal
        if (yI != 0) pIJCenter = pIJCenter.add(Vup.scale(yI));//add to the vertical
        return pIJCenter;//return the center of the pixel
    }

//    private Color antiAliasing(int nX, int nY, int j, int i) {
//        Color sumColors = new Color(0, 0, 0);
//        Vector vectorToThePixel;
//        Ray rayThroughPixel;
//        //get the center of the pixel
//        Point pIJCenter = getCenterPixel(nX, nY, j, i);
//
//        // make the top-left corner of the pixel
//        Point pIJ;
//        double rX = this.width / nX; //rX is the size of the horizontal rib of the pixel (without teh vertical rib)
//        double interval = rX / amountRays;
//
//        for (double z = 0; z < amountRays; z++) {
//            // move pIJ each iteration to the left of the current row
//            // (the pixel is divided to grid of rows and columns
//            // [interval times rows and interval times columns])
//            if (!isZero(rX / 2 - (interval) * z))
//                pIJ = pIJCenter.add(Vup.scale(rX / 2 - (interval) * z));
//            else pIJ = pIJCenter;
//            pIJ = pIJ.add(Vright.scale(-rX / 2));
//            for (int q = 0; q < amountRays; q++) {
//                pIJ = pIJ.add(Vright.scale(interval * rX));
//                vectorToThePixel = pIJ.subtract(this.p0);
//                rayThroughPixel = new Ray(this.p0, vectorToThePixel);
//                sumColors = sumColors.add(rayTracer.traceRay(rayThroughPixel));
//            }
//        }
//        // calculate the average color (there are eyeRaysAmount^2 sample rays);
//        Color avarageColor = sumColors.scale((double) 1 / (amountRays * amountRays));
//        return avarageColor;
//    }


    /**
     * the function gets the view plane size and a specific pixel,
     * return the rays from the camera which intersects this pixel
     *
     * @param nX - row amount in the view plane
     * @param nY - column amount in the view plane
     * @param j  - The index of X
     * @param i  - The index of Y
     * @return - a list of rays which goes through the pixel
     */
    public List<Ray> constructRays(int nX, int nY, int j, int i) {
        List<Ray> rays = new LinkedList<>();
        rays.add(constructRay(nX, nY, j, i));//add the basic ray to the set of samples
        Point centralPixel = getCenterPixel(nX, nY, j, i);
        double rY = height / nY / amountRays;//
        double rX = width / nX / amountRays;
        double x, y;

        for (int rowNumber = 0; rowNumber < amountRays; rowNumber++) {
            for (int colNumber = 0; colNumber < amountRays; colNumber++) {
//                y = -(rowNumber - (amountRays - 1d) / 2) * rY;
//                x = (colNumber - (amountRays - 1d) / 2) * rX;
                Point pIJ = centralPixel;
//                if (y != 0) pIJ = pIJ.add(Vup.scale(y));
//                if (x != 0) pIJ = pIJ.add(Vright.scale(x));
                rays.add(constructRandomRay(nX, nY, pIJ, rowNumber, colNumber, amountRays));
            }
        }

        return rays;
    }


    /**
     * Constructs a random ray based on the given parameters.
     *
     * @param Nx         The number of grid cells along the X-axis.
     * @param Ny         The number of grid cells along the Y-axis.
     * @param Pij        The initial point of the ray.
     * @param gridRow    The row index of the grid cell.
     * @param gridColumn The column index of the grid cell.
     * @param amount     The number of sub-grid cells within a single grid cell.
     * @return A randomly constructed Ray object.
     */
    public Ray constructRandomRay(int Nx, int Ny, Point Pij, int gridRow, int gridColumn, int amount) {
        // Calculate the height and width of each grid cell
        double Ry = (double) height / Ny;
        double Rx = (double) width / Nx;

        // Calculate the height and width of each sub-grid cell
        double gridHeight = (double) Ry / amount;
        double gridWidth = (double) Rx / amount;

        // Generate random values for the sub-grid cell
        Random r = new Random();
        double yI = r.nextDouble(gridHeight) - gridHeight / 2;
        double xJ = r.nextDouble(gridWidth) - gridWidth / 2;

        // Adjust Pij based on the random values and grid indices
        if (!isZero(xJ)) {
            Pij = Pij.add(Vright.scale(gridWidth * gridColumn + xJ));
        }

        if (!isZero(yI)) {
            Pij = Pij.add(Vup.scale(gridHeight * gridRow + yI));
        }

        // Return the constructed Ray object
        return new Ray(p0, Pij.subtract(p0));
    }


    /**
     * get  a point and return the color of the point
     *
     * @param p- point on the view plane
     * @return color of the point
     */
    private Color pointCalcColor(Point p) {
        return rayTracer.traceRay(new Ray(p0, p.subtract(p0)));
    }

    /**
     * get  a list of colors and return if the colors are almost same
     *
     * @param colorList
     * @return boolean
     */

    public Color adaptiveSuperSampaling (int Nx){
        return new Color(0,0,0);
    }


    /**
     * calculate average color of the pixel by using adaptive Super-sampling
     *
     * @param center- the center of the pixel
     * @param nY-     number of width pixel
     * @param nX-     number of length pixel
     * @return- the average color of the pixel
     */
    private Color adaptiveManager(int nX, int nY, int j, int i) {
        Point center= getCenterPixel(nX,nY, j, i);
        Hashtable<Point, Color> pointColorMap= new Hashtable<Point, Color>();
        double rY = height / nY / 2;
        double rX = width / nX / 2;
        Color upRight = pointCalcColor(center.add(Vup.scale(rY)).add(Vright.scale(rX)));
        Color upLeft = pointCalcColor(center.add(Vup.scale(rY)).add(Vright.scale(-rX)));
        Color downRight = pointCalcColor(center.add(Vup.scale(-rY)).add(Vright.scale(rX)));
        Color downLeft = pointCalcColor(center.add(Vup.scale(-rY)).add(Vright.scale(-rX)));

        return adaptive(1, center, rX, rY, pointColorMap, upLeft, upRight, downLeft, downRight);
    }

    /**
     * recursive method that return the average color of the pixel- by checking the color of the four corners
     *
     * @param depth-         the depth of the recursion
     * @param center-      the center of the pixel
     * @param rX-          the width of the pixel
     * @param rY-          the height of the pixel
     * @param upLeftCol-   the color of the vUp left corner
     * @param upRightCol-  the color of the vUp vRight corner
     * @param downLeftCol- the color of the down left corner
     * @param downRightCol - the color of the down vRight corner
     * @return the average color of the pixel
     */
    private Color adaptive(int depth, Point center, double rX, double rY, Hashtable<Point, Color> pointColorMap,
                           Color upLeftCol, Color upRightCol, Color downLeftCol, Color downRightCol) {
        //check if we are in the max level of the recursion
        if (depth == maximumAdaptiveDepth) {
            //returm the sum of the color and divide it by 4
            return downRightCol.add(upLeftCol).add(upRightCol).add(downLeftCol).reduce(4);
        }

        //send the colors of the 4 corners to almostSameColor method and it returns if the colors are almost the same
        if (almostSameColor(upRightCol,upLeftCol,downLeftCol,downRightCol)) {
            //the colors are same we return any one of the colors
            return downRightCol;
        }

        //the colors are different we go to the adaptive sections
        else {
            //for efficiency we calculate the 5 points that we will check in the next level of the recursion
            //we will send the new points to the the 4 new squares in the recursion
            Color rightMiddlePoint = getPointColorFromTable(center.add(Vright.scale(rX)), pointColorMap);
            Color leftMiddlePoint = getPointColorFromTable(center.add(Vright.scale(-rX)), pointColorMap);
            Color upMiddlePoint = getPointColorFromTable(center.add(Vup.scale(rY)), pointColorMap);
            Color downMiddlePoint = getPointColorFromTable(center.add(Vup.scale(-rY)), pointColorMap);
            Color centerPoint = pointCalcColor(center);


            //initialize for the next level find the 4 points that we will use in the next level
            rX = rX / 2;
            rY = rY / 2;
            upLeftCol = adaptive(depth + 1, center.add(Vup.scale(rY / 2)).add(Vright.scale(-rX / 2)), rX, rY, pointColorMap,
                    upLeftCol, upMiddlePoint, leftMiddlePoint, centerPoint);
            upRightCol = adaptive(depth + 1, center.add(Vup.scale(rY / 2)).add(Vright.scale(rX / 2)), rX, rY, pointColorMap,
                    upMiddlePoint, upRightCol, centerPoint, rightMiddlePoint);
            downLeftCol = adaptive(depth + 1, center.add(Vup.scale(-rY / 2)).add(Vright.scale(-rX / 2)), rX, rY, pointColorMap,
                    leftMiddlePoint, centerPoint, downLeftCol, downMiddlePoint);
            downRightCol = adaptive(depth + 1, center.add(Vup.scale(-rY / 2)).add(Vright.scale(rX / 2)), rX, rY, pointColorMap,
                    centerPoint, rightMiddlePoint, downMiddlePoint, downRightCol);

            //sum the Points color and return the average color value
            return downRightCol.add(upLeftCol).add(upRightCol).add(downLeftCol).reduce(4);
        }
    }

    /**
     * check if this point exist in the HashTable return his color otherwise calculate the color and save it
     *
     * @param point-           certain point in the pixel
     * @param pointColorMap- dictionary that save points and their color
     * @return the color of the point
     */
    private Color getPointColorFromTable(Point point, Hashtable<Point, Color> pointColorMap) {
        //for efficiency we search the point first at the HashTable to check if we alreay calcuated this point

        if (!(pointColorMap.containsKey(point))) {
            //the point does not exist at the hashtable we calcuate the color point and add to the hashTable
            Color color = pointCalcColor(point);
            pointColorMap.put(point, color);
            return color;
        }
        //get the point from the hashTable
        return pointColorMap.get(point);
    }
}

