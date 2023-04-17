package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTests {

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here -
        // ensure |result| = 1
        Sphere s = new Sphere( 1, new Point(0,0,0));
        Vector normal = s.getNormal(new Point(1,0,0));
        assertEquals(normal, new Vector(1,0,0), "Bad normal to sphere");
        assertEquals(1d, normal.length(), 0.00000001, "Sphere's normal is not normalized");



    }
}