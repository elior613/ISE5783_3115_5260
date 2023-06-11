package renderer;

import geometries.Polygon;
import geometries.Sphere;
import geometries.Squared3D;
import geometries.Table;
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
        Camera camera = new Camera(new Point(-50, -50, 50), new Vector(1, 0.3, 0), new Vector(0,0,1 )) //
                .setVPSize(150, 150).setVPDistance(50);
//        Camera camera1= new Camera(new Point(1000, 800, 950), new Vector(0, -0.432, -0.901), new Vector(0, 0.901, -0.432)) //
//                .setVPSize(150, 150).setVPDistance(1000);
//        Squared3D s= new Squared3D(new Point(0,0,0),5,5,15, new Color(120,0,0));
//
//        Squared3D s1= new Squared3D(new Point(15,0,0),5,5,15, new Color(120,0,0));
//
//        Squared3D s2= new Squared3D(new Point(0,15,0),5,5,15, new Color(120,0,0));
//        Squared3D s3= new Squared3D(new Point(15,15,0),5,5,15, new Color(120,0,0));


        Table t1=new Table(new Point(0,0,0),5,5,15, new Color(120,120,0),30,15);

        for (var sq: t1.getsquared3DList()) {
            for (var edge: sq.getGeometryList()) {
                scene.geometries.add(edge.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)));
//        }
        }
//
//        for (var edge: s.getGeometryList()) {
//            scene.geometries.add(edge.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)));
//        }
//        for (var edge: s1.getGeometryList()) {
//            scene.geometries.add(edge.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)));
//        }
//        for (var edge: s2.getGeometryList()) {
//            scene.geometries.add(edge.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)));
//        }
//        for (var edge: s3.getGeometryList()) {
//            scene.geometries.add(edge.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)));
//        }

        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setKl(0.0004).setKq(0.0000006));


        camera.setImageWriter(new ImageWriter("squared3DtEST1", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

}}