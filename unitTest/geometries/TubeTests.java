package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class TubeTests {
    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
            // ============ Equivalence Partitions Tests ==============
            // TC01 - simple case

            //initialize a tube
            Point p = new Point(0.5,0,1);
            Tube x = new Tube(1,new Ray(new Point(0,0,0), new Vector(1,0,0)));
            //ensure that the normal correct
            assertEquals( new Vector(0,0,1), x.getNormal(p),"bad normal for tube");

            // =============== Boundary Values Tests ==================
            // TC10: vector pp0 is orthogonal to v
            p = new Point(0,1,0);
            x = new Tube(1,new Ray(new Point(0,0,0), new Vector(1,0,0)));
            //ensure that the normal correct
            assertEquals( new Vector(0,1,0), x.getNormal(p),"bad normal for base tube");
        }
    }
