package geometries;

import primitives.Color;
import primitives.Point;

import java.util.List;

public class Chair extends Table {

    private Squared3D backrest;
    public Chair(Point position, int depth, int width, int height, Color color, int spaceWidth, int spaceDepth) {
        super(position, depth, width, height, color, spaceWidth, spaceDepth);

        backrest=new Squared3D(new Point(position.getX(), position.getY(), position.getZ()+height),depth,width,height,color);
        setToSquared3DList((backrest));
    }

//    public List<Squared3D> getsquared3DList() {
//        return squared3DList;
//    }

}
