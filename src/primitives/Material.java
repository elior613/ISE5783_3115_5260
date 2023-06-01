package primitives;

/**
 * The Material class represents the material properties of a geometry object.
 */
public class Material {
    /**Diffuse reflection coefficient*/
    public Double3 kD = Double3.ZERO;
    /** Specular reflection coefficient*/
    public Double3 kS = Double3.ZERO;
    /**Transparency coefficient*/
    public Double3 kT=Double3.ZERO;
    /** Reflection coefficient*/
    public Double3 kR=Double3.ZERO;
    /** Shininess factor*/
    public int nShininess = 0;

    /**
     * Sets the diffuse reflection coefficient of the material.
     *
     * @param kD the diffuse reflection coefficient to set
     * @return the Material object with the updated diffuse reflection coefficient
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Sets the specular reflection coefficient of the material.
     *
     * @param kS the specular reflection coefficient to set
     * @return the Material object with the updated specular reflection coefficient
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }
    /**
     * Sets the transparency coefficient of the material.
     *
     * @param kT the transparency coefficient to set
     * @return the Material object with the updated transparency coefficient
     */
    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * Sets the reflection coefficient of the material.
     *
     * @param kR the reflection coefficient to set
     * @return the Material object with the updated reflection coefficient
     */
    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }


    /**
     * Sets the shininess factor of the material.
     *
     * @param nShininess the shininess factor to set
     * @return the Material object with the updated shininess factor
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * Sets the diffuse reflection coefficient of the material.
     *
     * @param kD the diffuse reflection coefficient to set
     * @return the Material object with the updated diffuse reflection coefficient
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Sets the specular reflection coefficient of the material.
     *
     * @param kS the specular reflection coefficient to set
     * @return the Material object with the updated specular reflection coefficient
     */
    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }
    /**
     * Sets the transparency coefficient of the material.
     *
     * @param kT the transparency coefficient to set
     * @return the Material object with the updated transparency coefficient
     */
    public Material setkT(double kT) {
        this.kT= new Double3(kT);
        return this;
    }

    /**
     * Sets the reflection coefficient of the material.
     *
     * @param kR the reflection coefficient to set
     * @return the Material object with the updated reflection coefficient
     */
    public Material setkR(double kR) {
        this.kR = new Double3(kR);
        return this;
    }


}
