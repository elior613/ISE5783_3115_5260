package primitives;

import java.util.Objects;

public class Ray {
    Point po;
    Vector dir;

    public Ray(Point po, Vector dir) {
        this.po = po;
        this.dir = dir.normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return po.equals(ray.po) && dir.equals(ray.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(po, dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "po=" + po +
                ", dir=" + dir +
                '}';
    }
}
