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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o instanceof Vector vector))return super.equals(o);
        return false;
        //check this function
    }

    @Override
    public String toString() {
        return "Vector{ " +Double3.ZERO.toString()+super.toString()
                +"}";
    }

    public Vector add(Vector vector) {
       Point p=super.add(vector);
       Vector v=new Vector(p.xyz);
       return v;
    }

    public Vector scale(Double s){
        Vector v=new Vector(xyz.scale(s));
        return v;
    }
public Double dotProduct(Vector vector){
        return (this.xyz.d1*vector.xyz.d1+this.xyz.d2*vector.xyz.d2+this.xyz.d3*vector.xyz.d3);
}

public Vector crossProduct(Vector other){
        Double d1=this.xyz.d2*other.xyz.d3-this.xyz.d3*other.xyz.d2;
        Double d2=this.xyz.d3*other.xyz.d1-this.xyz.d1*other.xyz.d3;
        Double d3=this.xyz.d1*other.xyz.d2-this.xyz.d2*other.xyz.d1;
        Vector cross=new Vector(d1,d2,d3);
        return cross;
}

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public double lengthSquared() {
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
