package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;


import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * RayTracerBasic is a basic implementation of RayTracerBase for ray tracing in a scene.
 * It traces rays and calculates the color of the closest intersection point on objects in the scene.
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * A small value used for shadow ray calculations to prevent self-shadowing artifacts.
     */
    private static final double DELTA = 0.1;
    /**
     * The maximum level of color calculation recursion.
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /**
     * The minimum value of the reflection/refraction coefficient for color calculation termination.
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * The initial reflection/refraction coefficient for color calculation.
     */
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
     * Calculates the color at a given intersection point with the provided ray.
     * Performs recursive color calculations by considering local effects and global effects.
     *
     * @param intersection the intersection point and associated geometry
     * @param ray          the ray from the camera
     * @param level        the current recursion level
     * @param k            the reflection/refraction coefficient
     * @return the calculated color at the intersection point
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(intersection, ray,k);
        return level == 1 ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    /**
     * Calculates the color for a given intersection point with the provided ray.
     * Uses the maximum level of recursion and the initial reflection/refraction coefficient.
     * Adds the intensity of the ambient light in the scene.
     *
     * @param geopoint the intersection point and associated geometry
     * @param ray      the ray from the camera
     * @return the calculated color at the intersection point
     */
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
    private Color calcLocalEffects(GeoPoint gp, Ray ray,Double3 k) {
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
                Double3 ktr = transparency(gp,lightSource,l,n);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);
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

    /**
     * Calculates the global effects (reflection and refraction) at a given intersection point with the provided ray.
     * Performs recursive calculations for reflection and refraction rays.
     *
     * @param gp    the intersection point and associated geometry
     * @param ray   the ray from the camera
     * @param level the current recursion level
     * @param k     the reflection/refraction coefficient
     * @return the calculated color from global effects
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector v = ray.getDirection();
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        return calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, k, material.kR)
                .add(calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, k, material.kT));
    }

    /**
     * Calculates the color from a single global effect (reflection or refraction) at a given ray.
     * Performs recursive calculations for the reflection or refraction ray.
     *
     * @param ray   the reflection or refraction ray
     * @param level the current recursion level
     * @param k     the reflection/refraction coefficient
     * @param kx    the reflection/refraction color coefficient
     * @return the calculated color from the global effect
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) {
            return Color.BLACK;
        }
        GeoPoint gp = findClosestIntersection(ray);
        if (gp == null) {
            return scene.background.scale(kx);
        }
        if (isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getDirection()))) {
            return Color.BLACK;
        }
        return calcColor(gp, ray, level - 1, kkx).scale(kx);
    }

    /**
     * Constructs a refracted ray from the given intersection point, incoming direction, and surface normal.
     *
     * @param geoPoint the intersection point
     * @param v        the incoming direction
     * @param n        the surface normal
     * @return the constructed refracted ray
     */
    private Ray constructRefractedRay(Point geoPoint, Vector v, Vector n) {
        return new Ray(geoPoint, n, v);
    }

    /**
     * Constructs a reflected ray from the given intersection point, incoming direction, and surface normal.
     *
     * @param geoPoint the intersection point
     * @param v        the incoming direction
     * @param n        the surface normal
     * @return the constructed reflected ray
     */
    private Ray constructReflectedRay(Point geoPoint, Vector v, Vector n) {
        // The formula is: r = v - 2 * (v.n) * n
        double vn = v.dotProduct(n);

        if (vn == 0) {
            return null;
        }

        Vector r = v.subtract(n.scale(2 * vn)).normalize();
        return new Ray(geoPoint, n, r);
    }


    /**
     * Checks if a given point on a geometry surface is unshaded by a specific light source.
     *
     * @param gp           the intersection point and associated geometry
     * @param lightSource  the light source being checked
     * @param l            the direction from the intersection point to the light source
     * @param n            the surface normal at the intersection point
     * @return true if the point is unshaded by the light source, false otherwise
     */
    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1d); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection,n);
        double maxDistance = lightSource.getDistance(gp.point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, maxDistance);
        return intersections == null;
    }

    /**
     * Calculates the transparency coefficient (kTr) for a given intersection point and light source.
     * Determines the transparency of the material along the path from the intersection point to the light source.
     *
     * @param geoPoint    the intersection point and associated geometry
     * @param lightSource the light source
     * @param l           the vector from the intersection point to the light source
     * @param n           the surface normal at the intersection point
     * @return the transparency coefficient (kTr)
     */
    private Double3 transparency(GeoPoint geoPoint, LightSource lightSource, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1d); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, n, lightDirection);
        double maxDistance = lightSource.getDistance(geoPoint.point);
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay, maxDistance);

        if (intersections == null) {
            return Double3.ONE;
        }

        Double3 ktr = Double3.ONE;
        // Scan all geoPoint intersections and for every intersection which is closer to the point than the light source,
        // multiply ktr by kT of its geometry.
        // Performance: if ktr < MIN_CALC_COLOR_K, it means that we are close to zero, and we return Double3.ZERO.
        for (GeoPoint gpIntersection : intersections) {
            ktr = ktr.product(gpIntersection.geometry.getMaterial().kT);
            if (ktr.lowerThan(MIN_CALC_COLOR_K)) {
                return Double3.ZERO;
            }
        }
        return ktr;
    }


    /**
     * Finds the closest intersection point between the given ray and the geometries in the scene.
     *
     * @param ray the ray to intersect with the scene's geometries
     * @return the closest intersection point as a GeoPoint object, or null if there are no intersections
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> geoPointIntersections = scene.getGeometries().findGeoIntersections(ray);

        // If there are no intersections, return null
        if (geoPointIntersections == null) {
            return null;
        }

        // Return the closest intersection point using the ray's findClosestGeoPoint method
        return ray.findClosestGeoPoint(geoPointIntersections);
    }


}