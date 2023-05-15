package primitives;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void findClosestPoint() {
        Ray ray=new Ray(new Point(0,0,1),new Vector(0,0,1));
        List<Point> pointList=new LinkedList<Point>() ;

        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test

        pointList=(Arrays.asList(new Point(10,10,10), new Point(10,2,10),new Point(0,0,2),new Point(5,5,5),new Point(8,8,0)));
        assertEquals(new Point(0, 0,2), ray.findClosestPoint(pointList), "Wrong closest point");

        // =============== Boundary Values Tests ==================
        // TC11: Empty List
        pointList=new LinkedList<Point>() {};
        assertNull( ray.findClosestPoint(pointList), "null list");

        // =============== Boundary Values Tests ==================
        // TC12: first point in the list is the closet point to the ray
        pointList=(Arrays.asList(new Point(0,0,2),new Point(10,10,10), new Point(10,2,10),new Point(5,5,5),new Point(8,8,0)));
        assertEquals(new Point(0, 0,2), ray.findClosestPoint(pointList), "Wrong closest point");

        // =============== Boundary Values Tests ==================
        // TC13: last point in the list is the closet point to the ray
        pointList=(Arrays.asList(new Point(10,10,10), new Point(10,2,10),new Point(5,5,5),new Point(8,8,0),new Point(0,0,2)));
        assertEquals(new Point(0, 0,2), ray.findClosestPoint(pointList), "Wrong closest point");

    }
}