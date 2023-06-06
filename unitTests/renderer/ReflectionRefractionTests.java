/**
 * 
 */
package renderer;

import static java.awt.Color.*;

import geometries.*;
import lighting.*;
import org.junit.jupiter.api.Test;

import primitives.*;
import renderer.*;
import scene.Scene;

/** Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 * @author dzilb */
public class ReflectionRefractionTests {
   private Scene scene = new Scene.SceneBuilder("Test scene").build();

   /**
    * Produce a picture of a sphere lighted by a spot light
    */
   @Test
   public void twoSpheres() {
      Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
              .setVPSize(150, 150).setVPDistance(1000);

      scene.geometries.add( //
              new Sphere(50d, new Point(0, 0, -50)).setEmission(new Color(BLUE)) //
                      .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setkT(0.3)),
              new Sphere(25d, new Point(0, 0, -50)).setEmission(new Color(RED)) //
                      .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
      scene.lights.add( //
              new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                      .setKl(0.0004).setKq(0.0000006));

      camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
              .setRayTracer(new RayTracerBasic(scene)) //
              .renderImage() //
              .writeToImage();
   }

   /**
    * Produce a picture of a sphere lighted by a spot light
    */
   @Test
   public void twoSpheresOnMirrors() {
      Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
              .setVPSize(2500, 2500).setVPDistance(10000); //

      scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

      scene.geometries.add( //
              new Sphere(400d, new Point(-950, -900, -1000)).setEmission(new Color(0, 50, 100)) //
                      .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)
                              .setkT(new Double3(0.5, 0, 0))),
              new Sphere(200d, new Point(-950, -900, -1000)).setEmission(new Color(100, 50, 20)) //
                      .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
              new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                      new Point(670, 670, 3000)) //
                      .setEmission(new Color(20, 20, 20)) //
                      .setMaterial(new Material().setkR(1)),
              new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                      new Point(-1500, -1500, -2000)) //
                      .setEmission(new Color(20, 20, 20)) //
                      .setMaterial(new Material().setkR(new Double3(0.5, 0, 0.4))));

      scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
              .setKl(0.00001).setKq(0.000005));

      ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
      camera.setImageWriter(imageWriter) //
              .setRayTracer(new RayTracerBasic(scene)) //
              .renderImage() //
              .writeToImage();
   }

   /**
    * Produce a picture of a two triangles lighted by a spot light with a
    * partially
    * transparent Sphere producing partial shadow
    */
   @Test
   public void trianglesTransparentSphere() {
      Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
              .setVPSize(200, 200).setVPDistance(1000);

      scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

      scene.geometries.add( //
              new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                      new Point(75, 75, -150)) //
                      .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
              new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                      .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
              new Sphere(30d, new Point(60, 50, -50)).setEmission(new Color(BLUE)) //
                      .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6)));

      scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
              .setKl(4E-5).setKq(2E-7));

      ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
      camera.setImageWriter(imageWriter) //
              .setRayTracer(new RayTracerBasic(scene)) //
              .renderImage() //
              .writeToImage();
   }


//      public void ourImage() {
//         Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
//                 .setVPSize(200, 200).setVPDistance(1000);
//         // Set the background color
//         scene.setAmbientLight(new AmbientLight(new Color(black), 0.15));
//
//         // Create and add objects to the scene
//         scene.geometries.add( new Sphere(5,new Point(0,0,-10)).setEmission(new Color(red)) //
//                 .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6)));
//
//
//         // Create a light source and add it to the scene
//            @Override
//            public Color getIntensity(Point p) {
//               return null;
//            }
//
//            @Override
//            public Vector getL(Point p) {
//               return null;
//            }
//
//            @Override
//            public double getDistance(Point point) {
//               return 0;
//            }
//         };
//         scene.addLight(light);
//
//         // Create a camera
//         Camera camera = new Camera();
//
//         // Create a ray tracer
//         RayTracerBasic rayTracer = new RayTracerBasic(scene);
//
//         // Set the image dimensions
//         int imageWidth = 800;
//         int imageHeight = 600;
//
//         // Create an image with the specified dimensions
//         ImageWriter imageWriter = new ImageWriter("output.png", imageWidth, imageHeight);
//
//         // Iterate over each pixel in the image
//         for (int i = 0; i < imageWidth; i++) {
//            for (int j = 0; j < imageHeight; j++) {
//               // Calculate the ray from the camera to the current pixel
//               Ray ray = camera.constructRayThroughPixel(imageWidth, imageHeight, i, j);
//
//               // Trace the ray and get the color of the closest intersection point
//               Color pixelColor = rayTracer.traceRay(ray);
//
//               // Set the color of the pixel in the image
//               imageWriter.writePixel(i, j, pixelColor);
//            }
//         }
//
//         // Save the image
//         imageWriter.save();
//      }

   /**
    * test of a small illustration
    */
   @Test
   void PalmTest() {
      scene = new Scene.SceneBuilder("TestScene").setBackground(new Color(0, 102, 255)).build();
      Camera camera = new Camera(new Point(0, -100, 10),
              new Vector(0, 1, 0),
              new Vector(0, 0, 1))
              .setVPDistance(40).setVPSize(50, 50);
             /*.moveRightLeft(80)
             .moveNearAway(100)
             .moveUpDown(90)
             .spinAroundVUp(90)
             .spinAroundVRight(-40);*/
      //.moveNearAway(150)
      //.spinAroundVUp(180);
      //the body
      scene.geometries.add(new Sphere(20, new Point(0, 10, 0))
                      .setEmission(new Color(255, 51, 0))
                      .setMaterial(new Material().setShininess(10).setKd(0.5).setKs(0.5)),
//
//            new Polygon(new Point(20,20,0),new Point(20,-20,0),new Point(-20,-20,0),new Point(-20,20,0)) .setEmission(new Color(255, 51, 0))
//                    .setMaterial(new Material().setShininess(10).setKd(0.5).setKs(0.5)),
//            new Triangle(new Point(20,20,0),new Point(20,-20,0),new Point(0,0,-20)).setEmission(new Color(255, 51, 0))
//                    .setMaterial(new Material().setShininess(10).setKd(0.5).setKs(0.5)),
//              new Triangle(new Point(20,-20,0),new Point(-20,-20,0),new Point(0,0,-20)).setEmission(new Color(255, 51, 0))
//                      .setMaterial(new Material().setShininess(10).setKd(0.5).setKs(0.5)),
//              new Triangle(new Point(-20,-20,0),new Point(-20,20,0),new Point(0,0,-20)).setEmission(new Color(255, 51, 0))
//                      .setMaterial(new Material().setShininess(10).setKd(0.5).setKs(0.5)),
//              new Triangle(new Point(-20,20,0),new Point(20,20,0),new Point(0,0,-20)).setEmission(new Color(255, 51, 0))
//                      .setMaterial(new Material().setShininess(10).setKd(0.5).setKs(0.5)),
//            //  first eye
//              new Sphere(2,new Point(-11, -2, 10))
//                      .setEmission(new Color(0, 0, 0)),
//                       /*new Sphere(new Point(-11,-12,10), 5)
//                               .setEmission(new Color(255,255,255))
//                               .setMaterial(new Material().setKt(1).setKs(0.5)),*/
//              //second eye
//              new Sphere(2,new Point(-15, 13, 12))
//                      .setEmission(new Color(0, 0, 0)),
//                       /*new Sphere(new Point(-15,3,12), 5)
//                               .setEmission(new Color(255,255,255))
//                               .setMaterial(new Material().setKt(1).setKs(0.5)),*/
//              new Triangle(new Point(19, 0, 0), new Point(28, 0, -10), new Point(26, 0, 0))
//                      .setEmission(new Color(204, 51, 0))
//                      .setMaterial(new Material().setKs(1).setKd(1)),
//              new Triangle(new Point(19, 0, 0), new Point(28, 0, 10), new Point(26, 0, 0))
//                      .setEmission(new Color(204, 51, 0))
//                      .setMaterial(new Material().setKs(1).setKd(1)),
              new Plane(new Point(1, 2, -40), new Point(-2, 3, -40), new Point(3, -4, -40))
                      .setEmission(new Color(102, 204, 255))
                      .setMaterial(new Material().setkR(0.8).setKd(0.7).setShininess(30)),
              //mirrow


              new Triangle(new Point(-30, 40, -20), new Point(-30, -40, -20), new Point(-45, 0, 30)).
                      setMaterial(new Material().setkR(0.5).setKd(0.5).setKs(0.3)),
              new Tube(3, new Ray(new Point(-30, 25, -20), new Vector(1, 1, 0)))
                      .setEmission(new Color(102, 255, 102)).setMaterial(new Material().setkT(0.5).setKd(0.2).setKs(0.6)),
              new Tube(3, new Ray(new Point(-30, -25, -20), new Vector(1, 1, 0)))
                      .setEmission(new Color(255, 255, 0)).setMaterial(new Material().setkT(0.5).setKd(0.2).setKs(0.6)),
              new Tube(3, new Ray(new Point(-45, 0, 30), new Vector(1, 1, 0)))
                      .setEmission(new Color(204, 0, 204)).setMaterial(new Material().setkT(0.5).setKd(0.2).setKs(0.6)),
              new Tube(3, new Ray(new Point(-50, -15, -10), new Vector(1, 1, 0)))
                      .setEmission(new Color(255, 108, 255)).setMaterial(new Material().setkT(0.5).setKd(0.2).setKs(0.6)),
              new Tube(3, new Ray(new Point(-5, 10, 30), new Vector(1, 1, 0)))
                      .setEmission(new Color(255, 0, 0)).setMaterial(new Material().setkT(0.5).setKd(0.2).setKs(0.6)),
              new Sphere(10, new Point(20, -50, 1)).setEmission(new Color(0, 0, 255)).setMaterial(new Material().setkT(0.1).setKd(0.2).setKs(0.6)));


      scene.lights.add(new PointLight(new Color(255, 255, 255), new Point(-30, 0, 30)).setKq(0.0001).setKl(0.0001));
      scene.lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(1, 1, 0)));


      ImageWriter imageWriter = new ImageWriter("Palm Test4", 500, 500);
      camera.setImageWriter(imageWriter) //
              .setRayTracer(new RayTracerBasic(scene)) //
              .renderImage() //
              .writeToImage();


   }
}
