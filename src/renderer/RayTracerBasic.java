package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;


import java.util.List;

/**
 * RayTracerBasic is a basic implementation of RayTracerBase for ray tracing in a scene.
 * It traces rays and calculates the color of the closest intersection point on objects in the scene.
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * Constructs a RayTracerBasic object with the specified scene.
     *
     * @param scene the scene to be rendered
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Traces a ray and calculates the color of the closest intersection point on objects in the scene.
     *
     * @param ray the ray to be traced
     * @return the color of the closest intersection Geopoint or the background color if there are no intersections
     */
    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            return scene.background;
        GeoPoint closest = ray.findClosestGeoPoint(intersections);
        return calcColor(closest);
    }

    /**
     * Calculates the color at a given Geopoint based on the scene's ambient light intensity.
     *
     * @param p the Geopoint at which to calculate the color
     * @return the color at the given point
     */
    private Color calcColor(GeoPoint p) {
        return scene.ambientLight.getIntensity().add(p.geometry.getEmission());
    }

}
