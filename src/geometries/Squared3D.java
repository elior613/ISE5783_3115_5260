

package geometries;


import primitives.Color;
import primitives.Point;


import java.util.List;


public class Squared3D {
//
//    Geometry front;
//
//    Geometry back;
//    Geometry left;
//    Geometry right;
//    Geometry down;
//    Geometry up;

    List<Polygon> geometryList;

    public Squared3D(Point position, int depth,int width,int height ,Color color) {

        //initialize the walls
        //front=geometryList [0]
        Polygon front= (Polygon) new Polygon(position,new Point(position.getX()+width, position.getY(), position.getZ()),new Point(position.getX()+width, position.getY(), position.getZ()+height),new Point(position.getX(), position.getY(), position.getZ()+height)).setEmission(color);

        //back = geometryList[1]
        Polygon back =((Polygon)new Polygon(new Point(position.getX(), position.getY()+depth, position.getZ()),new Point(position.getX()+width, position.getY()+depth, position.getZ()),new Point(position.getX()+width, position.getY()+depth, position.getZ()+height),new Point(position.getX(), position.getY()+depth, position.getZ()+height)).setEmission(color));

        //left = geometryList[2]
        Polygon left= (Polygon) new Polygon(position,new Point(position.getX(), position.getY(), position.getZ()+height),new Point(position.getX(), position.getY()+depth, position.getZ()+height),new Point(position.getX(), position.getY()+depth, position.getZ())).setEmission(color);

        //right=geometryList[3]
        Polygon right= (Polygon) new Polygon(new Point(position.getX()+width, position.getY(), position.getZ()),new Point(position.getX()+width, position.getY(), position.getZ()+height),new Point(position.getX()+width, position.getY()+depth, position.getZ()+height),new Point(position.getX()+width, position.getY()+depth, position.getZ())).setEmission(color);

        //down =geometryList[4]
        Polygon down =(Polygon) new Polygon(new Point(position.getX(), position.getY(), position.getZ()),new Point(position.getX()+width,position.getY(), position.getZ()),new Point(position.getX()+width,position.getY()+depth, position.getZ() ),new Point(position.getX(), position.getY()+depth, position.getZ())).setEmission(color);

        //up=geometryList[5]
        Polygon up=(Polygon) new Polygon(new Point(position.getX(), position.getY(), position.getZ()+height),new Point(position.getX()+width,position.getY(), position.getZ()+height),new Point(position.getX()+width,position.getY()+depth, position.getZ()+height ),new Point(position.getX(), position.getY()+depth, position.getZ()+height)).setEmission(color);

        geometryList = List.of(front, back,left, right, down, up);
    }

    public List<Polygon> getGeometryList() {
        return geometryList;
    }


}
