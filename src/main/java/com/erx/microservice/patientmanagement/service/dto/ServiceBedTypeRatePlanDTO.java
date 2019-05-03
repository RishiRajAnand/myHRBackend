/*
 * @Copyright © 2017. eRx Solutions Pvt Ltd
 * @author john@erxindia.in
 * @project eRx
 * @version eRx v2.0
 * @module billing-microservice
 * @createdon 15-Nov-2017
 */
package com.erx.microservice.patientmanagement.service.dto;

import com.erx.microservice.patientmanagement.domain.BedTypeMaster;
import com.erx.microservice.patientmanagement.domain.PatientType;

public class ServiceBedTypeRatePlanDTO extends BaseModelDTO implements Comparable<ServiceBedTypeRatePlanDTO>{

    private MinimalDTO serviceCatalogue;
    private PatientType patientType;
    private BedTypeMaster bedType;
    private double rate;
    private double charges;

    //Getter and Setter

    public MinimalDTO getServiceCatalogue ( ) {
        return serviceCatalogue;
    }

    public void setServiceCatalogue (MinimalDTO serviceCatalogue) {
        this.serviceCatalogue = serviceCatalogue;
    }

    public PatientType getPatientType ( ) {
        return patientType;
    }

    public void setPatientType (PatientType patientType) {
        this.patientType = patientType;
    }

    public BedTypeMaster getBedType() {
        return bedType;
    }

    public void setBedType(BedTypeMaster bedType) {
        this.bedType = bedType;
    }

    public double getRate ( ) {
        return rate;
    }

    public void setRate (double rate) {
        this.rate = rate;
    }

    public double getCharges ( ) {
        return charges;
    }

    public void setCharges (double charges) {
        this.charges = charges;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p>
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p>
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p>
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(ServiceBedTypeRatePlanDTO o) {
        return Long.compare(o.getId(), this.getId());
    }
}
