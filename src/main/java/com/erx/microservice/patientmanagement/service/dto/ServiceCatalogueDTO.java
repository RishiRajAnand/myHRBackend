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

import java.util.HashSet;
import java.util.Set;

public class ServiceCatalogueDTO extends BaseModelDTO implements Comparable<ServiceCatalogueDTO>{

    private String serviceCode;
    private String serviceName;
    private Double basePrice;
    private MinCatalogueCategoryDTO catalogueCategory;
    private MinimalDTO catalogueCategoryDepartments;
    private String status;
    private Boolean active;
    private Set<ServicePatientTypeRatePlanDTO> servicePatientTypeRatePlans;
    private Set<ServiceBedTypeRatePlanDTO> serviceBedTypeRatePlans;
    // private Set<ServiceOrderingDepartmentDTO> serviceOrderingDepartments;
    private Set<ServiceOrderingTypeDTO> serviceOrderingTypes;
    private double price;
    private BedTypeMaster selectedBedType;
    private PatientType selectedPatientType;
    private String departmentName;;
    private IdNameDTO doctor;
    private LookupValueDTO serviceCatalogueTax;
    private Boolean global;


    //Getter and Setter
    public String getServiceName ( ) {
        return serviceName;
    }

    public void setServiceName (String serviceName) {
        this.serviceName = serviceName;
    }

    public Double getBasePrice ( ) {
        return basePrice;
    }

    public void setBasePrice (Double basePrice) {
        this.basePrice = basePrice;
    }

    public MinCatalogueCategoryDTO getCatalogueCategory ( ) {
        return catalogueCategory;
    }

    public void setCatalogueCategory (MinCatalogueCategoryDTO catalogueCategory) {
        this.catalogueCategory = catalogueCategory;
    }

    public MinimalDTO getCatalogueCategoryDepartments ( ) {
        return catalogueCategoryDepartments;
    }

    public void setCatalogueCategoryDepartments (MinimalDTO catalogueCategoryDepartments) {
        this.catalogueCategoryDepartments = catalogueCategoryDepartments;
    }

    public String getStatus ( ) {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public Boolean getActive ( ) {
        return active;
    }

    public void setActive (Boolean active) {
        this.active = active;
    }

    public Set<ServicePatientTypeRatePlanDTO> getServicePatientTypeRatePlans ( ) {
        return servicePatientTypeRatePlans;
    }

    public void setServicePatientTypeRatePlans (HashSet<ServicePatientTypeRatePlanDTO> servicePatientTypeRatePlans) {
        this.servicePatientTypeRatePlans = servicePatientTypeRatePlans;
    }

    public Set<ServiceBedTypeRatePlanDTO> getServiceBedTypeRatePlans ( ) {
        return serviceBedTypeRatePlans;
    }

    public void setServiceBedTypeRatePlans (HashSet<ServiceBedTypeRatePlanDTO> serviceBedTypeRatePlans) {
        this.serviceBedTypeRatePlans = serviceBedTypeRatePlans;
    }

    /** Later if the ordering department concept is being used uncomment this piece of code */
    /*public Set<ServiceOrderingDepartmentDTO> getServiceOrderingDepartments ( ) {
        return serviceOrderingDepartments;
    }

    public void setServiceOrderingDepartments (Set<ServiceOrderingDepartmentDTO> serviceOrderingDepartments) {
        this.serviceOrderingDepartments = serviceOrderingDepartments;
    }*/

    public String getServiceCode ( ) {
        return serviceCode;
    }

    public void setServiceCode (String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public double getPrice ( ) {
        return price;
    }

    public void setPrice (double price) {
        this.price = price;
    }

    public BedTypeMaster getSelectedBedType ( ) {
        return selectedBedType;
    }

    public void setSelectedBedType (BedTypeMaster selectedBedType) {
        this.selectedBedType = selectedBedType;
    }

    public PatientType getSelectedPatientType ( ) {
        return selectedPatientType;
    }

    public void setSelectedPatientType (PatientType selectedPatientType) {
        this.selectedPatientType = selectedPatientType;
    }

    public Set<ServiceOrderingTypeDTO> getServiceOrderingTypes ( ) {
        return serviceOrderingTypes;
    }

    public void setServiceOrderingTypes (Set<ServiceOrderingTypeDTO> serviceOrderingTypes) {
        this.serviceOrderingTypes = serviceOrderingTypes;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public IdNameDTO getDoctor() {
        return doctor;
    }

    public void setDoctor(IdNameDTO doctor) {
        this.doctor = doctor;
    }

    public LookupValueDTO getServiceCatalogueTax() {
        return serviceCatalogueTax;
    }

    public void setServiceCatalogueTax(LookupValueDTO serviceCatalogueTax) {
        this.serviceCatalogueTax = serviceCatalogueTax;
    }

    public Boolean getGlobal() {
        return global;
    }

    public void setGlobal(Boolean global) {
        this.global = global;
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
     * @param serviceCatalogueDTO the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(ServiceCatalogueDTO serviceCatalogueDTO) {
        return Long.compare(serviceCatalogueDTO.getId(), this.getId());
    }
}
