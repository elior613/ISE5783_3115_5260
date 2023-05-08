package integretionTests;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        // TC02: Big Sphere 18 points
        assertEquals(countIntersections(camera2, new Sphere(2.5, new Point(0, 0, -2.5))), 18);

        // TC03: Medium Sphere 10 points
        assertEquals(countIntersections(camera2, new Sphere(2, new Point(0, 0, -2))), 10);

        // TC04: Inside Sphere 9 points
        assertEquals(countIntersections(camera2, new Sphere(4, new Point(0, 0, -1))), 9);

        // TC05: Beyond Sphere 0 points
        assertEquals(countIntersections(camera1, new Sphere(0.5, new Point(0, 0, 1))), 0);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Plane intersections
     */
    @Test
    public void testCameraRayIntegretionPlane() {
        Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Plane against camera 9 points
        assertEquals(countIntersections(camera, new Plane(new Point(0, 0, -3), new Vector(0, 0, 1))), 9);

        // TC02: Plane with small angle 9 points
        assertEquals(countIntersections(camera, new Plane(new Point(0, 0, -10), new Vector(0, 1, 3))), 9);

        // TC03: Plane parallel to lower rays 6 points
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

        // TC01: Small triangle 1 point
        assertEquals(countIntersections(camera, new Triangle(new Point(0, 1, -2), new Point(1, -1, -2), new Point(-1, -1, -2))), 1);
        // TC02: Medium triangle 2 points
        assertEquals(countIntersections(camera, new Triangle(new Point(-1, -1, -2), new Point(1, -1, -2), new Point(0, 20, -2))), 2);

    }
}