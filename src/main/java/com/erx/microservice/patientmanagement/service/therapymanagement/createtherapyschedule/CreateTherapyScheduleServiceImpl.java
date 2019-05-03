package com.erx.microservice.patientmanagement.service.therapymanagement.createtherapyschedule;


import com.erx.microservice.patientmanagement.domain.Clinic;
import com.erx.microservice.patientmanagement.domain.casemanagement.ClinicPreference;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyInstance;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyPlanning;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapySchedule;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.casemanagement.ClinicPreferenceRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyInstanceRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyPlanningRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/*
* created by Raushan on 27-11-2018
* */

@Service
public class CreateTherapyScheduleServiceImpl implements CreateTherapyScheduleService {

    private final Logger log = LoggerFactory.getLogger(CreateTherapyScheduleServiceImpl.class);

    @Autowired
    private TherapyScheduleRepository therapyScheduleRepository;

    @Autowired
    private ClinicPreferenceRepository clinicPreferenceRepository;

    @Autowired
    private TherapyPlanningRepository therapyPlanningRepository;

    @Autowired
    private TherapyInstanceRepository therapyInstanceRepository;


    @Override
    public CreateTherapyScheduleServiceResponse execute(CreateTherapyScheduleServiceRequest request) throws ServiceException {

        CreateTherapyScheduleServiceResponse response = null;


        log.debug("Request to create TherapySchedule");
        String status = "failed";
        LocalDateTime instanceScheduleDate = null;
        String clinicHoliday;
        String checkDay=null;
        String startDate = null;
        Long clinicId = null;
        Long caseId = null;
        List<Long> therapyScheduleIds = null;
        List<Object[]> therapyPlanningDetails = null;
        List<Long> therapyInstanceIds = null;
        try {
            clinicId = request.getClinicId();
            startDate = request.getStartDate();
            caseId = request.getCaseId();
            therapyScheduleIds = therapyScheduleRepository.findTherapyScheduleIdsByCaseId(caseId);
            if (therapyScheduleIds.isEmpty()) {
                clinicHoliday = clinicPreferenceRepository.getCommonHoliday(clinicId);
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyy");
                LocalDate localDate = LocalDate.parse(startDate, format);
                LocalDateTime scheduleStartDate = LocalDateTime.of(localDate, LocalDateTime.now().toLocalTime());
                therapyPlanningDetails = therapyPlanningRepository.getTherapyPlanningIdsAndPeriodicIntervalByCaseId(caseId);
                List<TherapySchedule> therapySchedules = new ArrayList<>();

                for (Object[] therapyPlanning : therapyPlanningDetails) {
                    Long therapyPlanningId = (Long) therapyPlanning[0];
                    long periodicInterval = (Long) therapyPlanning[1];

                    therapyInstanceIds = therapyInstanceRepository.
                            findTherapyInstanceIdsByTherapyPlanningId(therapyPlanningId);

                    int count = 0;
                    instanceScheduleDate = scheduleStartDate;
                    for (Long therapyInstanceId : therapyInstanceIds) {
                        TherapySchedule therapySchedule = new TherapySchedule();
                        TherapyInstance therapyInstance = new TherapyInstance();
                        therapyInstance.setId(therapyInstanceId);
                        therapySchedule.setTherapyInstance(therapyInstance);

                        checkDay = instanceScheduleDate.getDayOfWeek().name();
                        if (count == 0) {
                            if (checkDay.trim().equalsIgnoreCase(clinicHoliday)) {
                                instanceScheduleDate = instanceScheduleDate.plusDays(1);
                            }
                            therapySchedule.setScheduleDate(instanceScheduleDate);
                        } else {
                            instanceScheduleDate = instanceScheduleDate.plusDays((int) periodicInterval);
                            if (checkDay.trim().equalsIgnoreCase(clinicHoliday)) {
                                instanceScheduleDate = instanceScheduleDate.plusDays(1);
                            }
                            therapySchedule.setScheduleDate(instanceScheduleDate);
                        }
                        count++;
                        therapySchedules.add(therapySchedule);
                    }

                    therapyPlanningRepository.updateTherapyPlanningDetail
                            (therapyPlanningId, scheduleStartDate, scheduleStartDate, instanceScheduleDate);
                }
                if (!therapySchedules.isEmpty()) {
                    therapyScheduleRepository.save(therapySchedules);
                }
            }
            status="success";
            //create responsee response
            response = new CreateTherapyScheduleServiceResponse(status);
            response.SUCCESSFUL = true;
            response.setMessage("TherapySchedule created successfully");
            log.debug("TherapySchedule created successfully");

        } catch (Exception e) {
            //create response
            response = new CreateTherapyScheduleServiceResponse(status);
            response.SUCCESSFUL = false;
            response.setMessage("Failed to create TherapySchedule");
            log.error("CreateTherapyScheduleServiceImpl -----> execute " + e);
        }
        return response;
    }

}
