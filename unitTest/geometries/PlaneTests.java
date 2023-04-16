package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class PlaneTests {


    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        // ensure |result| = 1

        Point p1=new Point(2, 3, 4);
        Point p2=new Point(9, 8, 0);
        Point p3=new Point(6, 3, 5);
        Plane plane=new Plane(p1,p2,p3);
        Vector normal=plane.getNormal();

        assertEquals(1d, normal.length(), 0.00000001, "Plane's normal is not normalized");

        // =============== Edge cases Tests ==================
        //Tc10: 2 points are same
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 1), new Point(1, 0, 0) ),
                "ERROR DID NOT THROW THAT 2 POINTS ARE THE SAME");

        //Tc10: 2 points are in the same line
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(0, 0, 0), new Point(0, 0, 1), new Point(0, 0, 2) ),
                "ERROR DID NOT THROW THAT 2 POINTS ARE THE SAME");

        //Tc12: 2 points are same- לבדוק אם אנחנו רוצים אותו
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(1, 0, 0) ),
                "ERROR DID NOT THROW THAT 2 POINTS ARE THE SAME");
    }
}