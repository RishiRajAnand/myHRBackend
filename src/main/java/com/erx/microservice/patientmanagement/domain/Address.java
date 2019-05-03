package com.erx.microservice.patientmanagement.domain;

/*
* created by Latha on 28-11-2017
* */

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public abstract class Address {

    @Column(length = 150, name = "address1")
    @NotBlank(message = "{address1.required}")
    private String address1;
    @Column(length = 150, name = "address2")
    private String address2;


    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * Overridden equals method for object comparison. Compares based on
     * hashCode.
     *
     * @param o Object to compare
     * @return true/false based on hashCode
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }

        final Address address1 = (Address) o;

        return this.hashCode() == address1.hashCode();
    }

    @Transient
    public String getOnelineAddress() {
        List<String> parts = new ArrayList<String>();
        if (address1 != null)
            parts.add(address1);
        if (address2 != null)
            parts.add(address2);
       /*
        if (city != null)
            parts.add(city);
        if (state != null)
            parts.add(state);
        if (country != null)
            parts.add(country);
        if (postalCode != null)
            parts.add(postalCode);
            */
        return StringUtils.join(parts, ", ");
    }

    /**
     * Overridden hashCode method - compares on address, city, province, country
     * and postal code.
     *
     * @return hashCode
     */
    public int hashCode() {
        int result;
        result = (address1 != null ? address1.hashCode() : 0);
        result = 29 * result + (address2 != null ? address2.hashCode() : 0);
        /*
        result = 29 * result + (city != null ? city.hashCode() : 0);
        result = 29 * result + (state != null ? state.hashCode() : 0);
        result = 29 * result + (country != null ? country.hashCode() : 0);
        result = 29 * result + (postalCode != null ? postalCode.hashCode() : 0);
        */
        return result;
    }

    /**
     * Returns a multi-line String with key=value pairs.
     *
     * @return a String representation of this class.
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                // .append("country", this.country)
                .append("address1", this.address1)
                .append("address2", this.address2).toString();
        // .append("state", this.state)
        // .append("postalCode", this.postalCode)
        //  .append("city", this.city).toString();
    }

}
