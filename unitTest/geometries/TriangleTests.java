package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTests {

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here -
        // ensure |result| = 1

        Triangle pl = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        Vector normal = pl.getNormal(new Point(0, 1, 0));
        //check 2 options of the normal
        Vector checkPositive=new Vector(sqrt3, sqrt3, sqrt3);
        Vector checkNegative=new Vector(-sqrt3, -sqrt3, -sqrt3);
        boolean flag=normal.equals(checkPositive)||normal.equals(checkNegative);


        assertTrue(flag, "Bad normal to triangle");
        assertEquals(1d, normal.length(), 0.00000001, "Triangle's normal is not normalized");
    }
}
