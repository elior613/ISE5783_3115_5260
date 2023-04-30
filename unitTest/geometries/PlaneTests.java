/**

 The geometries package contains classes for defining geometric shapes in 3D space.
 */
package geometries;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**

 Unit tests for the Plane class.
 */
class PlaneTests {

    /**

     Test method for the Plane class constructor.
     */
    @Test
    public void testConstructor() {

// =============== Edge cases Tests ==================

//Tc10: 2 points are same
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 1), new Point(1, 0, 0) ),
                "ERROR DID NOT THROW THAT 2 POINTS ARE THE SAME");

//Tc10: 2 points are in the same line
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(0, 0, 0), new Point(0, 0, 1), new Point(0, 0, 2) ),
                "ERROR DID NOT THROW THAT 2 POINTS ARE THE SAME");

//Tc12: 2 points are same
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(1, 0, 0) ),
                "ERROR DID NOT THROW THAT 2 POINTS ARE THE SAME");
    }

    /**

     Test method for the {@link primitives.Vector#length()} method.
     */
    @Test
    public void testLength() {

// ============ Equivalence Partitions Tests ==============

// TC01: There is a simple single test here - using a quad
// ensure |result| = 1
//initial plane
        Point p1=new Point(2, 3, 4);
        Point p2=new Point(9, 8, 0);
        Point p3=new Point(6, 3, 5);
        Plane plane=new Plane(p1,p2,p3);
        Vector normal=plane.getNormal();
        assertEquals(1d, normal.length(), 0.00000001, "Plane's normal is not normalized");

    }

    /**

     Test method for the {@link geometries.Plane#getNormal(primitives.Point)} method.
     */
    @Test
    public void testGetNormal() {

// ============ Equivalence Partitions Tests ==============

// TC01: calculate the normal of the plane
//
        Plane p1=new Plane(new Point(0,0,0), new Point(1,0,0),new Point(0,1,0));
        Vector checkPositive=new Vector(0, 0, 1);
        Vector checkNegative=new Vector(0, 0, -1);
        Vector normal= p1.getNormal().normalize();
        boolean flag=normal.equals(checkPositive)||normal.equals(checkNegative);
        assertTrue(flag, "Bad normal to plane");
    }
    /**
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Plane plane = new Plane(new Point (0, 1, 0),new Point (2, 3, 0), new Point (1, 0, 0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the plane, intersects,  not parallel and does not form a right angle with the plane (1 point)
        List<Point> result = plane.findIntersections(new Ray(new Point(0, 0, -1),
                new Vector(1, 1, 1)));
        assertEquals(1, result.size(), "Wrong number of points");;
        assertEquals(new Point(1,1,0), result.get(0), "Ray crosses sphere");

        // TC02: Ray's line is outside the plane, not intersects,  not parallel and does not form a right angle with the plane (1 point)
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 1), new Vector(1, 1, 1))),
                "Ray's line out of plane");

        // =============== Boundary Values Tests ==================
        // **** Ray is parallel to the plane

        // TC11: Ray included in the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(2, 3, 0), new Vector(1, 1, 0))),
                "Ray's line included in the plane");
        // TC12: Ray doesn't included in the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(2, 3, 1), new Vector(1, 1, 0))),
                "Ray's line parallels to the plane");

        // **** Group: Ray is orthogonal to the plane
        // TC13: Ray starts at plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(2, 3, 0), new Vector(0, 0, 1))),
                "Ray's point at plane");
        // TC14: Ray starts after the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(2, 3, 1), new Vector(0, 0, 1))),
                "Ray's point after the plane");
        // TC15: Ray starts before (1 points)
        result = plane.findIntersections(new Ray(new Point(1, 1, -1),
                new Vector(0, 0, 1)));
        assertEquals(1, result.size(), "Wrong number of points");;
        assertEquals(new Point(1,1,0), result.get(0), "Ray crosses plane");
        // TC16: Ray starts at the center (1 points)
}