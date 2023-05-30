package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * The Scene class represents a scene containing geometries, lights, and ambient light.
 * It provides methods for accessing and setting scene properties.
 */
public class Scene {

    /**
     * The name of the scene.
     */
    public final String name;

    /**
     * The background color of the scene.
     */
    public final Color background;

    /**
     * The geometries in the scene.
     */
    public final Geometries geometries;

    /**
     * The ambient light in the scene.
     */
    /**
     * The ambient light in the scene.
     */
    public AmbientLight ambientLight;

    /**
     * The list of light sources in the scene.
     */
    public List<LightSource> lights = new LinkedList<>();

    /**
     * Constructs a Scene object with the specified name, background color, geometries, and ambient light.
     *
     * @param builder the SceneBuilder object used to construct the Scene
     */
    private Scene(SceneBuilder builder) {
        name = builder.name;
        background = builder.background;
        ambientLight = builder.ambientLight;
        geometries = builder.geometries;
    }

    /**
     * Returns the name of the scene.
     *
     * @return the name of the scene
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the background color of the scene.
     *
     * @return the background color of the scene
     */
    public Color getBackground() {
        return background;
    }

    /**
     * Returns the geometries in the scene.
     *
     * @return the geometries in the scene
     */
    public Geometries getGeometries() {
        return geometries;
    }

    /**
     * Returns the ambient light in the scene.
     *
     * @return the ambient light in the scene
     */
    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    /**
     * Sets the ambient light in the scene.
     *
     * @param ambientLight the ambient light to set
     * @return the scene with the updated ambient light
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Sets the lights in the scene.
     *
     * @param lights the lights to set
     * @return the scene with the updated lights
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
    /**
     * The SceneBuilder class is a builder pattern class for constructing Scene objects.
     */
    public static class SceneBuilder {
        private final String name;
        public Color background = Color.BLACK;
        public Geometries geometries = new Geometries();
        public AmbientLight ambientLight = AmbientLight.NONE;

        /**
         * Constructs a SceneBuilder object with the specified name.
         *
         * @param name the name of the scene
         */
        public SceneBuilder(String name) {
            this.name = name;
        }

        /**
         * Sets the background color of the scene.
         *
         * @param background the background color to set
         * @return the SceneBuilder object with the updated background color
         */
        public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }

        /**
         * Sets the geometries in the scene.
         *
         * @param geometries the geometries to set
         * @return the SceneBuilder object with the updated geometries
         */
        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        /**
         * Sets the ambient light in the scene.
         *
         * @param ambientLight the ambient light to set
         * @return the SceneBuilder object with the updated ambient light
         */
        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        /**
         * Builds and returns a new Scene object with the configured properties.
         *
         * @return a new Scene object
         */
        public Scene build() {
            return new Scene(this);
        }
    }


}
