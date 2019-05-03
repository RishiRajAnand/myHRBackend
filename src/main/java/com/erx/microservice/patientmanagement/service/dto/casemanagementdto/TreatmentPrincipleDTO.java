package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 25-08-2018
* */

public class TreatmentPrincipleDTO {

    private boolean shodhanam;
    private boolean shamanam;

    public boolean isShodhanam() {
        return shodhanam;
    }

    public void setShodhanam(boolean shodhanam) {
        this.shodhanam = shodhanam;
    }

    public boolean isShamanam() {
        return shamanam;
    }

    public void setShamanam(boolean shamanam) {
        this.shamanam = shamanam;
    }
}
