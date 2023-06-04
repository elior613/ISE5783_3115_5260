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
     * A small value used for shadow ray calculations to prevent self-shadowing artifacts.
     */
    private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = Double3.ONE;



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
        GeoPoint closestPoint = findClosestIntersection(ray);return closestPoint == null ? scene.background
                : calcColor(closestPoint, ray);
    }

    /**
     * Calculates the color at a given Geopoint based on the scene's ambient light intensity.
     *
     * @param gp the Geopoint at which to calculate the color
     * @return the color at the given point
     */
    private Color calcColor(GeoPoint intersection, Ray ray,int level, Double3 k) {Color color = calcLocalEffects(intersection, ray);
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));}

    private Color calcColor(GeoPoint geopoint, Ray ray) {
        return calcColor(findClosestIntersection(ray), ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
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

    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector v = ray.getDirection();
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        return calcColorGLobalEffect(constructReflectedRay(gp, v, n),level, k, material.kr).add(calcColorGLobalEffect(constructRefractedRay(gp, v, n),level, k, material.kt));}
    private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint gp = findClosestIntersection(ray);
        if (gp == null) return scene.background.scale(kx);
        return isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getDir()))? Color.BLACK : calcColor(gp, ray, level – 1, kkx).scale(kx);}


    /**
     * Checks if a given point on a geometry surface is unshaded by a specific light source.
     *
     * @param gp           the intersection point and associated geometry
     * @param lightSource  the light source being checked
     * @param l            the direction from the intersection point to the light source
     * @param n            the surface normal at the intersection point
     * @param nl           the dot product of the surface normal and the light direction
     * @return true if the point is unshaded by the light source, false otherwise
     */
    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector l, Vector n, double nl) {
        Vector lightDirection = l.scale(-1d); // from point to light source
        Vector epsVector = n.scale(nl < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        double maxDistance = lightSource.getDistance(gp.point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, maxDistance);
        return intersections == null;
    }
    private GeoPoint findClosestIntersection(Ray ray){
        List<GeoPoint> geoPointIntersections = scene.getGeometries().findGeoIntersections(ray);
        //there is no intersections
        if (geoPointIntersections == null) {
            return null;
        }
        return ray.findClosestGeoPoint(geoPointIntersections);
    }

}