package com.erx.microservice.patientmanagement.service.casemanagement.savecmnextvisit;

/*
* created by Latha on 10-10-2018
* */

import com.erx.microservice.patientmanagement.domain.ClinicLocation;
import com.erx.microservice.patientmanagement.domain.Notification;
import com.erx.microservice.patientmanagement.domain.NotificationTypeMaster;
import com.erx.microservice.patientmanagement.domain.UserStaff;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmNextVisit;
import com.erx.microservice.patientmanagement.repository.ClinicLocationRepository;
import com.erx.microservice.patientmanagement.repository.NotificationRepository;
import com.erx.microservice.patientmanagement.repository.UserStaffRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmNextVisitRepository;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("saveCmNextVisitService")
public class SaveCmNextVisitServiceImpl implements SaveCmNextVisitService {

    private final Logger log = LoggerFactory.getLogger(SaveCmNextVisitServiceImpl.class);

    @Autowired
    private CmMasterRepository cmMasterRepository;

    @Autowired
    private CmNextVisitRepository cmNextVisitRepository;

    @Autowired
    private UserStaffRepository userStaffRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ClinicLocationRepository clinicLocationRepository;

    @Override
    public SaveCmNextVisitServiceResponse execute(SaveCmNextVisitServiceRequest request) throws ServiceException {
        SaveCmNextVisitServiceResponse response = null;
        CmMaster cmMaster = new CmMaster();
        CmNextVisit cmNextVisit = new CmNextVisit();
        UserStaff userStaff = new UserStaff();
        ClinicLocation clinicLocation = new ClinicLocation();
        Notification notification=new Notification();
        NotificationTypeMaster notificationTypeMaster = new NotificationTypeMaster();

        try {
            log.debug("Call to save next visit");
            //finding cm master
            cmMaster = cmMasterRepository.findOne(request.getCmNextVisitDTO().getCmMasterId());
            //finding user
            userStaff = userStaffRepository.findOne(request.getCmNextVisitDTO().getUserId());
            //finding clinic location
            clinicLocation = clinicLocationRepository.findOne(request.getCmNextVisitDTO().getClinicLocationId());
            if(cmMaster != null && userStaff != null && clinicLocation != null)
                //saving cm next visit
                cmNextVisit.setCmMaster(cmMaster);
            cmNextVisit.setCreatedDate(LocalDateTime.now());
            cmNextVisit.setNextVisitDate(request.getCmNextVisitDTO().getNextVisitDate());
            cmNextVisit.setNextVisitStatus("Planned");
            //save cm next visit
            CmNextVisit savedCmNextVisit = cmNextVisitRepository.save(cmNextVisit);

            //framing and saving notification
            notificationTypeMaster.setId((long) 1);
            notification.setNotificationTypeMaster(notificationTypeMaster);
            notification.setNotification_transaction_id(savedCmNextVisit.getId());
            notification.setUser(userStaff);
            notification.setNotification_date(savedCmNextVisit.getCreatedDate());
            notification.setNotification_status(savedCmNextVisit.getNextVisitStatus());
            notification.setCliniclocation(clinicLocation);
            //save notification
            Notification savedNotification = notificationRepository.save(notification);

            //create response
            response = new SaveCmNextVisitServiceResponse(savedCmNextVisit.getId(),savedNotification.getId());
            response.setMessage("Case sheet next visit saved successfully");
            log.debug("Case sheet next visit saved successfully");
        } catch (Exception e) {
            response = new SaveCmNextVisitServiceResponse();
            response.SUCCESSFUL = false;
            log.error(e.getMessage() + " so,Failed to save case sheet next visit details");
            response.setMessage(e.getMessage() + " so,Failed to save case sheet next visit details");
        }

        return response;
    }
}
