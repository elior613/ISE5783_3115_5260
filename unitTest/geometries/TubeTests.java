package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class TubeTests {

    @Test
    void testGetNormal() {
            // ============ Equivalence Partitions Tests ==============
            // TC01 - simple case
            Point p = new Point(0.5,0,1);
            Tube x = new Tube(1,new Ray(new Point(0,0,0), new Vector(1,0,0)));
            assertEquals( new Vector(0,0,1), x.getNormal(p));

            // =============== Boundary Values Tests ==================
            // TC10: vector pp0 is orthogonal to v
            p = new Point(0,1,0);
            x = new Tube(1,new Ray(new Point(0,0,0), new Vector(1,0,0)));
            assertEquals( new Vector(0,1,0), x.getNormal(p));
        }
    }
