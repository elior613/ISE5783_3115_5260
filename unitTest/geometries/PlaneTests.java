package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class PlaneTests {

    Point p1=new Point(2, 3, 4);
    Point p2=new Point(9, 8, 0);
    Point p3=new Point(6, 3, 5);
    Plane plane=new Plane(p1,p2,p3);
    Vector normal=plane.getNormal();
    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        // ensure |result| = 1
        assertEquals(1d, normal.length(), 0.00000001, "Plane's normal is not normalized");

        // =============== Edge cases Tests ==================
        //Tc10: ensure there are no exceptions
        assertThrows(() ->   if(!isZero(v1.dotProduct(v2))), "");
    }
}