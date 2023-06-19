package renderer;

import geometries.*;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;
/**
 * The ChairTest class is used to test the rendering of a chair in a scene.
 */
class ChairTest {

    private Scene scene = new Scene.SceneBuilder("Test scene").build();



    /**
     * Produce a picture of a sphere lighted by a spot light.
     * The camera position, view direction, and other elements of the scene are set up to render the chair.
     */
    @Test
    public void ChairTest1() {
        Camera camera = new Camera(new Point(-50, -50, 50), new Vector(1, 0.3, 0), new Vector(0, 0, 1)) //
                .setVPSize(150, 150).setVPDistance(50);
        scene.setAmbientLight(new AmbientLight(new Color(300, 150, 240), new Double3(0.1)));

        // Create a table and add its geometries to the scene
        Table t1 = new Table(new Point(0, -10, 0), 5, 5, 15, new Color(120, 120, 0), 30, 15);

        //add all the geometries which are composite the Table
        for (var sq : t1.getsquared3DList()) {//move all the square3d
            for (var edge : sq.getGeometryList()) {//move all over the polygons
                scene.geometries.add(edge.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)));
            }
        }

        // Create a chair and add its geometries to the scene
        Chair c = new Chair(new Point(10, -30, 0), 1, 1, 10, new Color(120, 120, 0), 8, 8);

        for (var s : c.getsquared3DList()) {//move all over the Square3D
            for (var edge : s.getGeometryList()) {//move all over the polygons and add them to the geometries
                scene.geometries.add(edge.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)));
            }
        }

        //the floor
        scene.geometries.add(new Plane(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0))
                .setEmission(new Color(102, 51, 0)).setMaterial(new Material().setKd(0.6).setShininess(10)));
        //The wall is opposite
        scene.geometries.add(new Plane(new Point(80, 0, 0), new Point(80, 80, 0), new Point(80, 80, 80))
                .setEmission(new Color(192, 192, 192)).setMaterial(new Material().setKd(0.6).setShininess(10)));
        //The wall on the right
        scene.geometries.add(new Plane(new Point(40, 40, 0), new Point(0, 40, 0), new Point(40, 40, 40))
                .setEmission(new Color(192, 192, 192)).setMaterial(new Material().setKd(0.6).setShininess(10)));
        //The morrow
        scene.geometries.add(new Polygon(new Point(-10, 39, 10), new Point(50, 39, 10), new Point(50, 39, 110), new Point(-10, 39, 70)).
                setMaterial(new Material().setkR(0.5).setKd(0.5).setKs(0.3)));
        //The window
        scene.geometries.add(new Polygon(new Point(79.5, 20, 60), new Point(79.5, -60, 60), new Point(79.5, -60, 140), new Point(79.5, 20, 140)).setEmission(new Color(51, 255, 255))
                .setMaterial(new Material().setShininess(10).setKd(0.3)));

        //The water mellon
        scene.geometries.add(new Sphere(5, new Point(10, 0, 25)).setEmission(new Color(0, 153, 0)).setMaterial(new Material().setkT(0.1).setKd(0.2).setKs(0.6)));

        //add the lights
        for (int i=0;i<4;i++) {
            scene.lights.add(new SpotLight(new Color(950, 550, 0), new Point(-95, 0, 50), new Vector(82, -18, -25)).setKl(0.1).setKq(0.0001));
            scene.lights.add(new SpotLight(new Color(102, 255, 255), new Point(79, -30, 100), new Vector(-5, 0, -14)).setKl(0.1).setKq(0.0001));
        }



        //create the image
        camera.setImageWriter(new ImageWriter("chairTEST1", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

}

