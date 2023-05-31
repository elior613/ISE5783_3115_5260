package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTests {

    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here

        //initalize triangle
        Triangle pl = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        Vector normal = pl.getNormal(new Point(0, 1, 0));
        //check 2 options of the normal (both sizes of normal)
        Vector checkPositive=new Vector(sqrt3, sqrt3, sqrt3);
        Vector checkNegative=new Vector(-sqrt3, -sqrt3, -sqrt3);
        boolean flag=normal.equals(checkPositive)||normal.equals(checkNegative);

        //check if the vector normal is correct as we expected
        assertTrue(flag, "Bad normal to triangle");

        // ensure |result| = 1
        assertEquals(1d, normal.length(), 0.00000001, "Triangle's normal is not normalized");
    }
    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Triangle triangle = new Triangle(new Point(1, 0, 0), new Point(1, 10, 0), new Point(11, 0, 0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray Inside triangle (1 point)
        List<Point> result = triangle.findIntersections(new Ray(new Point(4, 4, -1),
                new Vector(1, 1, 1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point(5, 5, 0), result.get(0), "Ray crosses sphere");

        // TC02: Ray outside against edge (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point(0, 12, -1), new Vector(0, 1, 1))),
                "Ray's line out of triangle");

        // TC03: Ray outside against vertex (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point(5, -2, -1), new Vector(0, 1, 1))),
                "Ray's line out of triangle");

        // =============== Boundary Values Tests ==================
        // **** Group: the ray begins "before" the plane
        // TC11: Ray on the  edge (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point(6, -1, -1), new Vector(0, 1, 1))),
                "Ray's line out of triangle");
        // TC12: Ray in the vertex (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point(11, -1, -1), new Vector(0, 1, 1))),
                "Ray's line out of triangle");
        // TC13: Ray on edge's continuation(0 points)
        assertNull(triangle.findIntersections(new Ray(new Point(12, -1, -1), new Vector(0, 1, 1))),
                "Ray's line out of triangle");
    }
    /**
     * Test method for {@link geometries.Triangle#findGeoIntersections(primitives.Ray,double)}.
     */
    @Test
    // TC01: checking the maxDistance parameter
    public void testFindGeoIntersections() {
        Triangle triangle = new Triangle(new Point(0,0,1), new Point(0,4,1), new Point(4,0,1));
        List<Intersectable.GeoPoint> result = triangle.findGeoIntersections(new Ray(new Point(1,1,0), new Vector(0,0,1)),0.5);
        assertNull(result, "Wrong number of points");

    }
}
