package com.erx.microservice.patientmanagement.repository;

import com.erx.microservice.patientmanagement.PatientManagementApp;
import com.erx.microservice.patientmanagement.domain.IpAdmission;
import com.erx.microservice.patientmanagement.domain.Patient;
import com.erx.microservice.patientmanagement.domain.PatientAppointment;
import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PatientManagementApp.class)
public class ITPatientSearchRepositoryTest {

    @Autowired
    PatientRepository patientRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPatientSearchRepository() throws Exception {
        Patient patient = new Patient();
        PatientAppointment patientAppointment = new PatientAppointment();
        IpAdmission ipAdmission = new IpAdmission();
        PatientSearchCriteria patientSearchCriteria = new PatientSearchCriteria();
        patientSearchCriteria.setSearchValue("00241-032");
        patientSearchCriteria.setClinicId(Long.valueOf(32));
        String testQuery = "select p,ip,ibm,pa from Patient p " +
                "left join p.ipAdmission ip " +
                "with trim(upper(ip.ipAdmissionStatus)) != 'DISCHARGED' " +
                "left join ip.ipAdmissionBedMovements ibm with ibm.currentBed = 1  " +
                "left join p.patientAppointments pa ";
        String whereClause = new String();
        whereClause = " where p.recordStatus= 1 and p.clinic.id= " + patientSearchCriteria.getClinicId() + " and p.patientIdExternal = '" + patientSearchCriteria.getSearchValue() + "'";
                /*" p.firstName like 'manish' ";*/
        List<Object[]> patientFinalDetails = null;
        List<Object[]> patientResults = entityManager.createQuery(testQuery + " " + whereClause).getResultList();
        for (Object[] ipAdmissionDetail : patientResults) {

            patient = (Patient) ipAdmissionDetail[0];
            if (ipAdmissionDetail[1] != null) {
                ipAdmission = (IpAdmission) ipAdmissionDetail[1];
            }
        }
    }
}
