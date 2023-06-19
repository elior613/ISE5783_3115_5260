package geometries;

import primitives.Color;
import primitives.Point;

import java.util.List;

/**
 * Represents a chair that extends a table.
 */
public class Chair extends Table {

    private Squared3D backrest;

    /**
     * Constructs a chair with the specified parameters.
     *
     * @param position   The position of the chair.
     * @param depth      The depth of the chair.
     * @param width      The width of the chair.
     * @param height     The height of the chair.
     * @param color      The color of the chair.
     * @param spaceWidth The space width of the chair.
     * @param spaceDepth The space depth of the chair.
     */
    public Chair(Point position, int depth, int width, int height, Color color, int spaceWidth, int spaceDepth) {
        super(position, depth, width, height, color, spaceWidth, spaceDepth);

        backrest = new Squared3D(new Point(position.getX(), position.getY(), position.getZ() + height), depth, width + spaceWidth, height, color);
        super.addSquared3DList((Squared3D) backrest);
    }
}
