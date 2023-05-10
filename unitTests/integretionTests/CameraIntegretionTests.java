package integretionTests;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing Camera Class
 *
 * @author Elior & Omer
 *
 */
class CameraIntegretionTests {

    int countIntersections(Camera camera, Intersectable geometry) {
        int sum = 0;
        List<Point> intersectionPoints = new LinkedList<Point>();
        camera.setVPSize(3, 3);
        camera.setVPDistance(1);
        int row = 3;
        int column = 3;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                List<Point> intersections = geometry.findIntersections(camera.constructRay(column, row, j, i));
                if (intersections != null)
                    sum += intersections.size();
            }
        }
        return sum;
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Sphere intersections
     */
    @Test
    public void testCameraRayIntegretionSphere() {
        Camera camera1 = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, -1, 0));
        Camera camera2 = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, -1, 0));
        // TC01: Small Sphere 2 points
       // assertEquals(countIntersections(camera1, new Sphere(1, new Point(0, 0, -3))), 2);

        // TC02: Sphere- 18 intersection points (Large sphere)
        assertEquals(countIntersections(camera2, new Sphere(2.5, new Point(0, 0, -2.5))), 18);

        // TC03: Sphere -10 intersection points (Medium sphere)
        assertEquals(countIntersections(camera2, new Sphere(2, new Point(0, 0, -2))), 10);

        // TC04: Sphere : 9 intersection points (the camera inside the sphere)
        assertEquals(countIntersections(camera2, new Sphere(4, new Point(0, 0, -1))), 9);

        // TC05: sphere: 0 intrsection points
        assertEquals(countIntersections(camera1, new Sphere(0.5, new Point(0, 0, 1))), 0);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Plane intersections
     */
    @Test
    public void testCameraRayIntegretionPlane() {
        Camera camera = new Camera(new Point(0, 0, 1), new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Plane 9  intersecion points- the camera is front of the plane
        assertEquals(countIntersections(camera, new Plane(new Point(0, 0, -3), new Vector(0, 0, 1))), 9);

        // TC02:  plane 9  intersection points, low angle against the camera
        assertEquals(countIntersections(camera, new Plane(new Point(0, 0, -10), new Vector(0, 1, 3))), 9);

        // TC03: Plane 6 intersecion points high angle against the camera
        assertEquals(countIntersections(camera, new Plane(new Point(0, 0, -10), new Vector(0, 2, 1))), 6);

        // TC04: Beyond Plane 0 points
        assertEquals(countIntersections(camera, new Plane(new Point(0, 0, -10), new Vector(0, -1, 0))), 0);

    }
    /**
     * Integration tests of Camera Ray construction with Ray-Triangle intersections
     */
    @Test
    public void testCameraRayIntegretionTriangle() {
        Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Triangle: 1 intersection point
        assertEquals(countIntersections(camera, new Triangle(new Point(0, 1, -2), new Point(1, -1, -2), new Point(-1, -1, -2))), 1);
        // TC02: Triangle:  2 interssection points
        assertEquals(countIntersections(camera, new Triangle(new Point(-1, -1, -2), new Point(1, -1, -2), new Point(0, 20, -2))), 2);

    }
}

