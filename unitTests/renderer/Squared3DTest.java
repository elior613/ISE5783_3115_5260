package renderer;

import geometries.Polygon;
import geometries.Sphere;
import geometries.Squared3D;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;
import static org.junit.jupiter.api.Assertions.*;

class Squared3DTest {

    private Scene scene = new Scene.SceneBuilder("Test scene").build();

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void Squared3DTest1() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);
        Squared3D s= new Squared3D(new Point(0,0,0),100,200,300, new Color(120,120,120));

        scene.geometries.add( //

                s.getBack(),
                s.getFront(),
                s.getDown(),
                s.getUp(),
                s.getLeft(),
                s.getRight());

//                new Sphere(50d, new Point(0, 0, -50)).setEmission(new Color(BLUE)) //
//                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setkT(0.3)),
//                new Sphere(25d, new Point(0, 0, -50)).setEmission(new Color(RED)) //
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setKl(0.0004).setKq(0.0000006));

        camera.setImageWriter(new ImageWriter("squared3DtEST1", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

}