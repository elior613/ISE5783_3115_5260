

package geometries;


import primitives.Color;
import primitives.Point;


import java.util.List;


public class Squared3D extends Polygon{


    Polygon front= new Polygon(new Point(0,0,0),new Point(0,0,1),new Point(0,1,1));

    Polygon back= new Polygon(new Point(0,0,0),new Point(0,0,1),new Point(0,1,1));
    Polygon left= new Polygon(new Point(0,0,0),new Point(0,0,1),new Point(0,1,1));;
    Polygon right= new Polygon(new Point(0,0,0),new Point(0,0,1),new Point(0,1,1));;
    Polygon down= new Polygon(new Point(0,0,0),new Point(0,0,1),new Point(0,1,1));;
    Polygon up= new Polygon(new Point(0,0,0),new Point(0,0,1),new Point(0,1,1));;

    public Squared3D(Point position, int depth,int width,int height ,Color color) {


        //initialize the walls

        //front=list 0
        front =((Polygon) new Polygon(position,new Point(position.getX()+width, position.getY(), position.getZ()),new Point(position.getX()+width, position.getY(), position.getZ()+height),new Point(position.getX(), position.getY(), position.getZ()+height)).setEmission(color));
        back= (Polygon) new Polygon(new Point(position.getX(), position.getY()+depth, position.getZ()),new Point(position.getX()+width, position.getY()+depth, position.getZ()),new Point(position.getX()+width, position.getY()+depth, position.getZ()+height),new Point(position.getX(), position.getY()+depth, position.getZ()+height)).setEmission(color);
        left= (Polygon) new Polygon(position,new Point(position.getX(), position.getY(), position.getZ()+height),new Point(position.getX(), position.getY()+depth, position.getZ()+height),new Point(position.getX(), position.getY()+depth, position.getZ())).setEmission(color);
        right= (Polygon) new Polygon(new Point(position.getX()+width, position.getY(), position.getZ()),new Point(position.getX()+width, position.getY(), position.getZ()+height),new Point(position.getX()+width, position.getY()+depth, position.getZ()+height),new Point(position.getX()+width, position.getY()+depth, position.getZ())).setEmission(color);
        down = (Polygon) new Polygon(new Point(position.getX(), position.getY(), position.getZ()),new Point(position.getX()+width,position.getY(), position.getZ()),new Point(position.getX()+width,position.getY()+depth, position.getZ() ),new Point(position.getX(), position.getY()+depth, position.getZ())).setEmission(color);
        up = (Polygon) new Polygon(new Point(position.getX(), position.getY(), position.getZ()+height),new Point(position.getX()+width,position.getY(), position.getZ()+height),new Point(position.getX()+width,position.getY()+depth, position.getZ()+height ),new Point(position.getX(), position.getY()+depth, position.getZ()+height)).setEmission(color);


    }

    public Polygon getFront() {
        return front;
    }

    public Squared3D setFront(Polygon front) {
        this.front = front;
        return this;
    }

    public Polygon getBack() {
        return back;
    }

    public Squared3D setBack(Polygon back) {
        this.back = back;
        return this;
    }

    public Polygon getLeft() {
        return left;
    }

    public Squared3D setLeft(Polygon left) {
        this.left = left;
        return this;
    }

    public Polygon getRight() {
        return right;
    }

    public Squared3D setRight(Polygon right) {
        this.right = right;
        return this;
    }

    public Polygon getDown() {
        return down;
    }

    public Squared3D setDown(Polygon down) {
        this.down = down;
        return this;
    }

    public Polygon getUp() {
        return up;
    }

    public Squared3D setUp(Polygon up) {
        this.up = up;
        return this;
    }

}
