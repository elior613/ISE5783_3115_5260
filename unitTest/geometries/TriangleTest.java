package geometries;

import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        // ensure |result| = 1

        Point p1=new Point(2, 3, 4);
        Point p2=new Point(9, 8, 0);
        Point p3=new Point(6, 3, 5);
        Triangle t=new Triangle(p1,p2,p3);
        Vector normal=t.getNormal();

        assertEquals(1d, normal.length(), 0.00000001, "Plane's normal is not normalized");


    }