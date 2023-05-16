package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

public class Scene {
    public final String name;
    public final Color background ;

    public final Geometries geometries;

    public AmbientLight ambientLight;

    public String getName() {
        return name;
    }

    public Color getBackground() {
        return background;
    }

    public Geometries getGeometries() {
        return geometries;
    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    private Scene(SceneBuilder builder ){
        name = builder.name;
        background = builder.background;
        ambientLight = builder.ambientLight;
        geometries = builder.geometries;
    }

    public static class SceneBuilder {
        private final String name;
        public  Color background  = Color.BLACK;

        public  Geometries geometries = new Geometries();

        public AmbientLight ambientLight = AmbientLight.NONE;

        public SceneBuilder(String name) {
            this.name = name;
        }

        public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }

        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        public Scene build(){
            return new Scene(this);
        }
    }
}
