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
        return calcColor(closest,ray);
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
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDirection ();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(material,nl)),iL.scale(calcSpecular(material,n,l,nl,v)));
            }
        }
        return color;
    }

    private Double3 calcSpecular(Material material,Vector n,Vector l, double nl,Vector v) {
    Vector r=l.subtract(n.scale(2*nl));
    double max=-r.dotProduct(v);
    if(alignZero(max)>0)
        return material.kS.scale(Math.pow(max,material.nShininess));
    return Double3.ZERO;
    }

    private Double3 calcDiffusive(Material material,double nl) {
        return material.kD.scale(nl<0?-nl:nl);
    }


}
