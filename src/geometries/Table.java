package geometries;

import primitives.Color;
import primitives.Point;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Table {
List<Squared3D>squared3DList=new LinkedList<>();

    public Table(Point position, int depth, int width, int height , Color color, int spaceWidth, int spaceDepth) {
        Squared3D s1=new Squared3D(position,depth,width,height,color);
        Squared3D s2=new Squared3D(new Point(position.getX()+spaceWidth, position.getY(), position.getZ()),depth,width,height,color);
        Squared3D s3=new Squared3D(new Point(position.getX(), position.getY()+spaceDepth, position.getZ()),depth,width,height,color);
        Squared3D s4=new Squared3D(new Point(position.getX()+spaceWidth, position.getY()+spaceDepth, position.getZ() ),depth,width,height,color);
        Squared3D s5=new Squared3D(new Point(position.getX(), position.getY(), position.getZ()+height),spaceDepth+depth,spaceWidth+ width,width,color);
        squared3DList=List.of((Squared3D) s1,(Squared3D) s2,(Squared3D) s3,(Squared3D) s4,(Squared3D)s5 );

    }

    public Table setToSquared3DList(Squared3D squared3D) {
        this.squared3DList.add(squared3D);
        return this;
    }

    public List<Squared3D> getsquared3DList() {
        return squared3DList;
    }
}
