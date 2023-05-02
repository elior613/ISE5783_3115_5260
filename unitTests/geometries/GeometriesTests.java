package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTests {



    /**
     * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: empty list
        Geometries geometries = new Geometries();
        Ray ray = new Ray(new Point(0,1, 1), new Vector(1,0,0));
        assertNull(geometries.findIntersections(ray), "TC01. Wrong number of points");

        // TC02: no intersections
        geometries = new Geometries(
                new Sphere(1,new Point(0,0,-4)),
                new Sphere(1,new Point(0,0 ,7))
        );
        ray = new Ray(new Point(0,0, 1), new Vector(1,0,0));
        assertNull(geometries.findIntersections(ray), "TC02: Wrong number of points");

        // TC02: one shape is intersected
        geometries = new Geometries(
                new Sphere(1,new Point(0,0,4)),
                new Sphere(1,new Point(0,0 ,7))
        );
        ray = new Ray(new Point(0,0, 4.5), new Vector(0,0,-1));
        assertEquals(1, geometries.findIntersections(ray).size(), "TC03: Wrong number of points");

        // TC03: Some of the shapes are intersected
        geometries = new Geometries(
                new Sphere(1,new Point(0,0,4)),
                new Sphere(1,new Point(0,0 ,7)),
                new Sphere(1,new Point(100, 40, 30))
        );
        ray = new Ray(new Point(0,0, -1), new Vector(0,0,1));
        assertEquals(4, geometries.findIntersections(ray).size(), "TC03: Wrong number of points");

        // TC04: all shapes are intersected
        geometries = new Geometries(
                new Sphere(1,new Point(0,0,4)),
                new Sphere(1,new Point(0,0 ,7))
        );
        ray = new Ray(new Point(0,0, -1), new Vector(0,0,1));
        assertEquals(4, geometries.findIntersections(ray).size(), "TC03: Wrong number of points");
    }


    }