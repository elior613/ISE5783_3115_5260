package geometries;

import primitives.Color;
import primitives.Point;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a table composed of multiple Squared3D objects.
 */
public class Table {

    protected LinkedList<Squared3D> squared3DList;

    /**
     * Constructs a Table object with the specified parameters.
     *
     * @param position    The position of the table.
     * @param depth       The depth of the table.
     * @param width       The width of the table.
     * @param height      The height of the table.
     * @param color       The color of the table.
     * @param spaceWidth  The space width of the table.
     * @param spaceDepth  The space depth of the table.
     */
    public Table(Point position, int depth, int width, int height, Color color, int spaceWidth, int spaceDepth) {
        Squared3D s1 = new Squared3D(position, depth, width, height, color);
        Squared3D s2 = new Squared3D(new Point(position.getX() + spaceWidth, position.getY(), position.getZ()), depth, width, height, color);
        Squared3D s3 = new Squared3D(new Point(position.getX(), position.getY() + spaceDepth, position.getZ()), depth, width, height, color);
        Squared3D s4 = new Squared3D(new Point(position.getX() + spaceWidth, position.getY() + spaceDepth, position.getZ()), depth, width, height, color);
        Squared3D s5 = new Squared3D(new Point(position.getX(), position.getY(), position.getZ() + height), spaceDepth + depth, spaceWidth + width, width, color);
        squared3DList = new LinkedList<>();
        squared3DList.add(s1);
        squared3DList.add(s2);
        squared3DList.add(s3);
        squared3DList.add(s4);
        squared3DList.add(s5);
    }

    /**
     * Returns the list of Squared3D objects that form the table.
     *
     * @return The list of Squared3D objects.
     */
    public List<Squared3D> getSquared3DList() {
        return squared3DList;
    }

    /**
     * Adds a Squared3D object to the table.
     *
     * @param squared3D The Squared3D object to add.
     */
    public void addSquared3DList(Squared3D squared3D) {
        squared3DList.add(squared3D);
    }

    /**
     * Returns the list of Squared3D objects that form the table.
     *
     * @return The list of Squared3D objects.
     * @deprecated Use {@link #getSquared3DList()} instead.
     */
    @Deprecated
    public LinkedList<Squared3D> getsquared3DList() {
        return squared3DList;
    }
}
