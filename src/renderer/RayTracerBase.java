/**
 * The RayTracerBase class is an abstract base class for ray tracing algorithms.
 * It provides a framework for tracing rays and calculating the color of intersections in a scene.
 */
package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public abstract class RayTracerBase {
    /**

     The scene represents the scene object containing the geometries, lights, and camera.
     It provides the necessary information for ray tracing calculations.
     */
    protected Scene scene;

    /**
     * Constructs a RayTracerBase object with the specified scene.
     *
     * @param scene the scene to be rendered
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Traces a ray and calculates the color of the intersections in the scene.
     *
     * @param ray the ray to trace
     * @return the color of the intersections along the ray
     */
    public abstract Color traceRay(Ray ray);


    /**
     * Traces rays and calculates the color of the intersections in the scene.
     *
     * @param rays the list of rays to trace
     * @return the color of the intersections along the rays
     */
    public abstract Color traceRays(List<Ray> rays);


}
