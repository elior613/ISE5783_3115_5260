package renderer;

import geometries.*;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;
import static org.junit.jupiter.api.Assertions.*;

class  Squared3DTest {

    private Scene scene = new Scene.SceneBuilder("Test scene").build();

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void Squared3DTest1() {
        Camera camera = new Camera(new Point(-50, -50, 50), new Vector(1, 0.3, 0), new Vector(0,0,1 )) //
                .setVPSize(150, 150).setVPDistance(50);
        scene.setAmbientLight(new AmbientLight(new Color(300, 150, 240), new Double3(0.1)));
//        Camera camera1= new Camera(new Point(1000, 800, 950), new Vector(0, -0.432, -0.901), new Vector(0, 0.901, -0.432)) //
//                .setVPSize(150, 150).setVPDistance(1000);
//        Squared3D s= new Squared3D(new Point(0,0,0),5,5,15, new Color(120,0,0));
//
//        Squared3D s1= new Squared3D(new Point(15,0,0),5,5,15, new Color(120,0,0));
//
//        Squared3D s2= new Squared3D(new Point(0,15,0),5,5,15, new Color(120,0,0));
//        Squared3D s3= new Squared3D(new Point(15,15,0),5,5,15, new Color(120,0,0));


        Table t1=new Table(new Point(0,-10,0),5,5,15, new Color(120,120,0),30,15);

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

            //the floor
            scene.geometries.add(new Plane(new Point(0,0,0), new Point(1,0,0), new Point(0,1,0))
                    .setEmission(new Color(102,51,0)).setMaterial(new Material().setKd(0.6).setShininess(10)));
            //The wall is opposite
            scene.geometries.add(new Plane(new Point(80,0,0), new Point(80,80,0), new Point(80,80,80))
                    .setEmission(new Color(192,192,192)).setMaterial(new Material().setKd(0.6).setShininess(10)));
            //The wall on the right
            scene.geometries.add(new Plane(new Point(40,40,0), new Point(0,40,0), new Point(40,40,40))
                    .setEmission(new Color(192,192,192)).setMaterial(new Material().setKd(0.6).setShininess(10)));
            scene.geometries.add(new Polygon(new Point(-10, 39, 10), new Point(50, 39, 10),new Point(50, 39, 110), new Point(-10, 39, 70)).
                    setMaterial(new Material().setkR(0.5).setKd(0.5).setKs(0.3)));
            scene.geometries.add(new Polygon(new Point(79.5, 20, 60), new Point(79.5,-60 , 60),new Point(79.5, -60, 140), new Point(79.5, 20, 140)).setEmission(new Color(51,255,255))
                    .setMaterial(new Material().setkT(0.3).setKd(0.3)));
//            Plane sky = (Plane) new Plane(new Point(-5000, -500, 190), new Vector(0, 0, -1))
//                    .setEmission(new Color(201, 226, 255)).setMaterial(new Material().setnShininess(10).setkS(0.2));
            scene.geometries.add(new Sphere(5, new Point(10, 0, 25)).setEmission(new Color(0, 153, 0)).setMaterial(new Material().setkT(0.1).setKd(0.2).setKs(0.6)));



            scene.lights.add(
                    new SpotLight(new Color(950,550,0), new Point(-95,0,50), new Vector(82,-18,-25)).setKl(0.1).setKq(0.0001));
            // new PointLight(new Color(RED), new Point(79,20,20)).setKl(0.1).setKq(0.0001);






            camera.setImageWriter(new ImageWriter("squared3DtEST1", 500, 500)) //
                    .setRayTracer(new RayTracerBasic(scene)) //
                    .renderImage() //
                    .writeToImage();
        }

    }}