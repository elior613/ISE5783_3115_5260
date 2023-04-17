
/**
 The Vector class represents a three-dimensional vector in space, with an x, y, and z component.
 It is a subclass of the Point class, since vectors and points are closely related in 3D graphics.
 */
package primitives;
public class  Vector extends Point{


    /**
     * Constructs a new Vector object with the specified x, y, and z components.
     * @param x the x component of the vector
     * @param y the y component of the vector
     * @param z the z component of the vector
     * @throws IllegalArgumentException if the vector is the zero vector (0, 0, 0)
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if(xyz.equals(Double3.ZERO)){
            throw  new IllegalArgumentException("Vector cannot be Vector(0,0,0");
        }
    }


    /**
     * Constructs a new Vector object from a Double3 object.
     * @param double3 the Double3 object containing the x, y, and z components of the vector
     */
    Vector(Double3 double3) {
        this(double3.d1,double3.d2,double3.d3);
    }


    /**
     * Determines whether the specified object is equal to this Vector object.
     * @param o the object to compare with this Vector object
     * @return true if the specified object is equal to this Vector object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o instanceof Vector vector))return super.equals(o);
        return false;
    }


    /**
     * Returns a string representation of this Vector object.
     * @return a string representation of this Vector object
     */
    @Override
    public String toString() {
        return "Vector{ " +Double3.ZERO.toString()+super.toString()
                +"}";
    }


    /**
     * Adds the specified vector to this vector and returns the result as a new Vector object.
     * @param vector the vector to add to this vector
     * @return a new Vector object representing the sum of this vector and the specified vector
     */
    public Vector add(Vector vector) {
        Point p=super.add(vector);
        Vector v=new Vector(p.xyz);
        return v;
    }


    /**
     * Scales this vector by the specified factor and returns the result as a new Vector object.
     * @param s the scaling factor to apply to this vector
     * @return a new Vector object representing the scaled vector
     */
    public Vector scale(Double s){
        Vector v=new Vector(xyz.scale(s));
        return v;
    }


    /**
     * Computes the dot product of this vector and the specified vector.
     * @param vector the vector to compute the dot product with
     * @return the dot product of this vector and the specified vector
     */
    public Double dotProduct(Vector vector){
        return (this.xyz.d1*vector.xyz.d1+this.xyz.d2*vector.xyz.d2+this.xyz.d3*vector.xyz.d3);
    }


    /**
     * Computes the cross product of this vector and the specified vector.
     * @param other the vector to compute the cross product with
     * @return a new Vector object representing the cross product of this vector and the specified vector
     */



    public Vector crossProduct(Vector other){
        Double d1=this.xyz.d2*other.xyz.d3-this.xyz.d3*other.xyz.d2;
        Double d2=this.xyz.d3*other.xyz.d1-this.xyz.d1*other.xyz.d3;
        Double d3=this.xyz.d1*other.xyz.d2-this.xyz.d2*other.xyz.d1;
        Vector cross=new Vector(d1,d2,d3);
        return cross;
    }
    /**
     @return the size of the vector
     */

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     @return the squared size of the vector
     */

    public double lengthSquared() {
        double x=  xyz.d1;
        double y = xyz.d2;
        double z = xyz.d3;
        return x*x+y*y+z*z;
    }

    /**
     * @return the normalized Vector
     */

    public Vector normalize() {
        //return new Vector(xyz.reduce(len));

        double len=length();
        double x=  xyz.d1/len;
        double y = xyz.d2/len;
        double z = xyz.d3/len;
        return new Vector(x,y,z);
    }
}
