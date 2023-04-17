package geometries;

import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here -
        // ensure |result| = 1

        Triangle pl = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        Vector normal = pl.getNormal(new Point(0, 1, 0));
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), normal, "Bad normal to triangle");
        assertEquals(1d, normal.length(), 0.00000001, "Triangle's normal is not normalized");
    }


}
