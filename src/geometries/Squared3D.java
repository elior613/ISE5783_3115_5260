package geometries;

import primitives.Color;
import primitives.Point;
import java.util.List;

/**
 * Represents a 3D squared shape composed of six polygons: front, back, left, right, down, and up.
 */
public class Squared3D {

    List<Polygon> geometryList;

    /**
     * Constructs a Squared3D object with the specified parameters.
     *
     * @param position The position of the Squared3D.
     * @param depth    The depth of the Squared3D.
     * @param width    The width of the Squared3D.
     * @param height   The height of the Squared3D.
     * @param color    The color of the Squared3D.
     */
    public Squared3D(Point position, int depth, int width, int height, Color color) {

        // Initialize the walls

        // Front wall
        Polygon front = (Polygon) new Polygon(position,
                new Point(position.getX() + width, position.getY(), position.getZ()),
                new Point(position.getX() + width, position.getY(), position.getZ() + height),
                new Point(position.getX(), position.getY(), position.getZ() + height))
                .setEmission(color);

        // Back wall
        Polygon back = (Polygon) new Polygon(new Point(position.getX(), position.getY() + depth, position.getZ()),
                new Point(position.getX() + width, position.getY() + depth, position.getZ()),
                new Point(position.getX() + width, position.getY() + depth, position.getZ() + height),
                new Point(position.getX(), position.getY() + depth, position.getZ() + height)).setEmission(color);

        // Left wall
        Polygon left = (Polygon) new Polygon(position,
                new Point(position.getX(), position.getY(), position.getZ() + height),
                new Point(position.getX(), position.getY() + depth, position.getZ() + height),
                new Point(position.getX(), position.getY() + depth, position.getZ()))
                .setEmission(color);

        // Right wall
        Polygon right = (Polygon) new Polygon(new Point(position.getX() + width, position.getY(), position.getZ()),
                new Point(position.getX() + width, position.getY(), position.getZ() + height),
                new Point(position.getX() + width, position.getY() + depth, position.getZ() + height),
                new Point(position.getX() + width, position.getY() + depth, position.getZ()))
                .setEmission(color);

        // Bottom wall
        Polygon down = (Polygon) new Polygon(new Point(position.getX(), position.getY(), position.getZ()),
                new Point(position.getX() + width, position.getY(), position.getZ()),
                new Point(position.getX() + width, position.getY() + depth, position.getZ()),
                new Point(position.getX(), position.getY() + depth, position.getZ()))
                .setEmission(color);

        // Top wall
        Polygon up = (Polygon) new Polygon(new Point(position.getX(), position.getY(), position.getZ() + height),
                new Point(position.getX() + width, position.getY(), position.getZ() + height),
                new Point(position.getX() + width, position.getY() + depth, position.getZ() + height),
                new Point(position.getX(), position.getY() + depth, position.getZ() + height))
                .setEmission(color);

        geometryList = List.of(front, back, left, right, down, up);
    }

    /**
     * Returns the list of polygons that form the Squared3D.
     *
     * @return The list of polygons.
     */
    public List<Polygon> getGeometryList() {
        return geometryList;
    }
}
