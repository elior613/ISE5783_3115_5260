package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;


import java.util.List;

import static primitives.Util.alignZero;

/**
 * RayTracerBasic is a basic implementation of RayTracerBase for ray tracing in a scene.
 * It traces rays and calculates the color of the closest intersection point on objects in the scene.
 */
public class RayTracerBasic extends RayTracerBase {
    /**
     * add a constant for the size of moving the beginning of rays for shading rays
     */
    private static final double DELTA = 0.1;


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
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(ray);
        if (intersections == null)
            return scene.getBackground();
        GeoPoint closest = ray.findClosestGeoPoint(intersections);
        return calcColor(closest, ray);
    }

    /**
     * Calculates the color at a given Geopoint based on the scene's ambient light intensity.
     *
     * @param gp the Geopoint at which to calculate the color
     * @return the color at the given point
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return scene.getAmbientLight().getIntensity().add(calcLocalEffects(gp, ray));
    }

    /**
     * Calculates the local effects (diffuse and specular reflections) for a given intersection point and ray.
     *
     * @param gp  the intersection point and associated geometry
     * @param ray the ray that intersects with the geometry
     * @return the color resulting from the local effects
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Vector v = ray.getDirection();
        Vector n = gp.geometry.getNormal(gp.point);
        Color color = gp.geometry.getEmission();
        double nv = alignZero(n.dotProduct(v));
        //there is no effect on the color
        if (nv == 0)
            return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            // check if sign(nl) == sign(nv)
            if (nl * nv > 0) {
                //if yes, there is effect on the color
               if(unshaded(gp,lightSource,l,n,nl)) {
                    Color iL = lightSource.getIntensity(gp.point);
                    color = color.add(iL.scale(calcDiffuse(material, nl)), iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }
        return color;
    }

    /**
     * Calculates the specular reflection for a given material, normal, light direction, and view direction.
     *
     * @param material the material properties
     * @param n        the surface normal
     * @param l        the light direction
     * @param nl       the dot product of the normal and light direction
     * @param v        the view direction
     * @return the specular reflection color
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(2 * nl));
        double max = -r.dotProduct(v);
        if (alignZero(max) > 0)
            return material.kS.scale(Math.pow(max, material.nShininess));
        return Double3.ZERO;
    }

    /**
     * Calculates the diffuse reflection for a given material and dot product of the normal and light direction.
     *
     * @param material the material properties
     * @param nl       the dot product of the normal and light direction
     * @return the diffuse reflection color
     */
    private Double3 calcDiffuse(Material material, double nl) {
        //return the positive nl value
        if(nl<0)
            return material.kD.scale(-nl);
        return material.kD.scale(nl);
    }

    private static final double EPS = 0.1;

    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector l, Vector n, double nl)
    {
        Vector lightDirection = l.scale(-1d); // from point to light source
        Vector epsVector = n.scale(nl < 0 ? EPS : -EPS);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        double maxdistance = lightSource.getDistance(gp.point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay,maxdistance);
        if(intersections == null)
            return true;
        return false;
    }
}