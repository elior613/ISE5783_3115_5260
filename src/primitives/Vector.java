package primitives;

public class Vector extends Point{

    public Vector(double x, double y, double z) {
        super(x, y, z);
        if(xyz.equals(Double3.ZERO)){
            throw  new IllegalArgumentException("Vector cannot be Vector(0,0,0");
        }
    }

    Vector(Double3 double3) {
        //super(double3);
        //if(xyz.equals(Double3.ZERO)){
        //  throw  new IllegalArgumentException("Vector cannot be Vector(0,0,0");
        this(double3.d1,double3.d2,double3.d3);
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    private double lengthSquared() {
        double x=  xyz.d1;
        double y = xyz.d2;
        double z = xyz.d3;
        return x*x+y*y+z*z;
    }

    public Vector normalize() {
        //return new Vector(xyz.reduce(len));

        double len=length();
        double x=  xyz.d1/len;
        double y = xyz.d2/len;
        double z = xyz.d3/len;
        return new Vector(x,y,z);
    }
}
