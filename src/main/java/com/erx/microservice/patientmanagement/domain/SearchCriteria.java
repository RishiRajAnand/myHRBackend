package com.erx.microservice.patientmanagement.domain;

/**
 * Created by mkpatil on 28/12/17.
 */
public class SearchCriteria {

    /**
     * Order by
     */
    private String orderBy;

    /**
     * Search type
     */
    private String searchType;


    /**
     * Search value
     */
    private String searchValue;

    /**
     * clinicId
     */
    private Long clinicId;

    /**
     * clinicLocationId
     */
    private Long clinicLocationId;

    /**
     * StartDate
     */

    private String startDate;

    /**
     * EndDate
     */

    private String endDate;

    /**
     * userId
     */

    private Long userId;

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
