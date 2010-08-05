package org.talframework.util.test.bean;

/**
 * Bean with basic types for testing
 *
 * @author Tom Spencer
 */
public class SimpleBean {
    
    private String name;
    private double dbl;
    private float flt;
    private long lng;
    private int integer;
    private short shrt;
    private boolean bool;
    private char chr;
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * Setter for the name field
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the dbl
     */
    public double getDbl() {
        return dbl;
    }
    /**
     * Setter for the dbl field
     *
     * @param dbl the dbl to set
     */
    public void setDbl(double dbl) {
        this.dbl = dbl;
    }
    /**
     * @return the flt
     */
    public float getFlt() {
        return flt;
    }
    /**
     * Setter for the flt field
     *
     * @param flt the flt to set
     */
    public void setFlt(float flt) {
        this.flt = flt;
    }
    /**
     * @return the lng
     */
    public long getLng() {
        return lng;
    }
    /**
     * Setter for the lng field
     *
     * @param lng the lng to set
     */
    public void setLng(long lng) {
        this.lng = lng;
    }
    /**
     * @return the integer
     */
    public int getInteger() {
        return integer;
    }
    /**
     * Setter for the integer field
     *
     * @param integer the integer to set
     */
    public void setInteger(int integer) {
        this.integer = integer;
    }
    /**
     * @return the shrt
     */
    public short getShrt() {
        return shrt;
    }
    /**
     * Setter for the shrt field
     *
     * @param shrt the shrt to set
     */
    public void setShrt(short shrt) {
        this.shrt = shrt;
    }
    /**
     * @return the bool
     */
    public boolean isBool() {
        return bool;
    }
    /**
     * Setter for the bool field
     *
     * @param bool the bool to set
     */
    public void setBool(boolean bool) {
        this.bool = bool;
    }
    /**
     * @return the chr
     */
    public char getChr() {
        return chr;
    }
    /**
     * Setter for the chr field
     *
     * @param chr the chr to set
     */
    public void setChr(char chr) {
        this.chr = chr;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (bool ? 1231 : 1237);
        result = prime * result + chr;
        long temp;
        temp = Double.doubleToLongBits(dbl);
        result = prime * result + (int)(temp ^ (temp >>> 32));
        result = prime * result + Float.floatToIntBits(flt);
        result = prime * result + integer;
        result = prime * result + (int)(lng ^ (lng >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + shrt;
        return result;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if( this == obj ) return true;
        if( obj == null ) return false;
        if( getClass() != obj.getClass() ) return false;
        SimpleBean other = (SimpleBean)obj;
        if( bool != other.bool ) return false;
        if( chr != other.chr ) return false;
        if( Double.doubleToLongBits(dbl) != Double.doubleToLongBits(other.dbl) ) return false;
        if( Float.floatToIntBits(flt) != Float.floatToIntBits(other.flt) ) return false;
        if( integer != other.integer ) return false;
        if( lng != other.lng ) return false;
        if( name == null ) {
            if( other.name != null ) return false;
        }
        else if( !name.equals(other.name) ) return false;
        if( shrt != other.shrt ) return false;
        return true;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SimpleBean [bool=" + bool + ", chr=" + chr + ", dbl=" + dbl + ", flt=" + flt + ", integer=" + integer + ", lng=" + lng + ", name=" + name
                + ", shrt=" + shrt + "]";
    }
}
