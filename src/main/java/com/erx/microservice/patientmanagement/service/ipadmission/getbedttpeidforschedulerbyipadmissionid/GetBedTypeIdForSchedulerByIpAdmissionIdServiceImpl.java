package com.erx.microservice.patientmanagement.service.ipadmission.getbedttpeidforschedulerbyipadmissionid;

/*
* created by Raushan on 05-10-2018.
* */

import com.erx.microservice.patientmanagement.config.Constants;
import com.erx.microservice.patientmanagement.domain.BedConfigurationMaster;
import com.erx.microservice.patientmanagement.domain.IpAdmissionBedTransfer;
import com.erx.microservice.patientmanagement.repository.BedTypeMasterRepository;
import com.erx.microservice.patientmanagement.repository.IpAdmissionBedTransferRepository;
import com.erx.microservice.patientmanagement.repository.IpAdmissionRepository;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class GetBedTypeIdForSchedulerByIpAdmissionIdServiceImpl implements GetBedTypeIdForSchedulerByIpAdmissionIdService {

    private final Logger log = LoggerFactory.getLogger(GetBedTypeIdForSchedulerByIpAdmissionIdServiceImpl.class);

    @Autowired
    private IpAdmissionBedTransferRepository ipAdmissionBedTransferRepository;

    @Autowired
    private IpAdmissionRepository ipAdmissionRepository;

    @Autowired
    private BedTypeMasterRepository bedTypeMasterRepository;

    @Override
    public GetBedTypeIdForSchedulerByIpAdmissionIdServiceResponse execute(GetBedTypeIdForSchedulerByIpAdmissionIdServiceRequest request) throws ServiceException {
        GetBedTypeIdForSchedulerByIpAdmissionIdServiceResponse response = null;
        String type = null;
        Long bedTypeId = null;
        Duration duration;
        LocalDateTime cutOffTime = null;
        LocalDateTime lastCutOffTime = null;
        Map<Long, Long> map = new HashMap();
        List<Long> arrayList = new ArrayList<>();
        List<IpAdmissionBedTransfer> ipAdmissionBedTransfers = new ArrayList<>();
        try {
            log.debug("Call to get  bed Type Id For Scheduler for given IpAdmissionId");
            // retrieve the bed transfer details
            String formatCutOffTime =(request.getCutOffTime().replace('T', ' '));
            type = request.getType();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            cutOffTime = LocalDateTime.parse(formatCutOffTime, formatter);
            lastCutOffTime = cutOffTime.minusDays(1);
            ipAdmissionBedTransfers = ipAdmissionBedTransferRepository.
                    getIpAdmissionBedTransfersByIpAdmissionIdAndByCreatedOn(request.getIpAdmissionID(), lastCutOffTime, cutOffTime);
            if (ipAdmissionBedTransfers.isEmpty()) {
                bedTypeId = ipAdmissionRepository.findOne(request.getIpAdmissionID()).getBedMaster().getBedTypeMaster().getId();
            } else {
                if (type.equalsIgnoreCase(Constants.TRANSFER_MAX_STAY_CONSTANT)) {

                    LocalDateTime startTime = ipAdmissionRepository.getAdmissionOnTimeById(request.getIpAdmissionID(), lastCutOffTime, cutOffTime);
                    LocalDateTime endTime;
                    for (IpAdmissionBedTransfer ipAdmissionBedTransfer : ipAdmissionBedTransfers) {
                        endTime = ipAdmissionBedTransfer.getCreatedOn();
                        if(startTime==null){
                            startTime = endTime;
                            continue;
                        }
                        bedTypeId = ipAdmissionBedTransfer.getBedTransferredFrom().getBedTypeMaster().getId();
                        //calculate the time difference b/w startTime and endTime
                        duration = Duration.between(startTime, endTime);
                        startTime = endTime;
                        long seconds = duration.getSeconds();
                        arrayList.add(seconds);
                        map.put(seconds, bedTypeId);
                    }
                    Long maxStaySeconds = Collections.max(arrayList);
                    bedTypeId = (Long) map.get(maxStaySeconds);
                } else {
                    for (IpAdmissionBedTransfer ipAdmissionBedTransfer : ipAdmissionBedTransfers) {
                        //bedTransferredFrom
                        BedConfigurationMaster bedTransferredFrom = ipAdmissionBedTransfer.getBedTransferredFrom();
                        Long sequenceBedTransferredFrom = bedTransferredFrom.getBedTypeMaster().getSequenceOrderNo();
                        bedTypeId = bedTransferredFrom.getBedTypeMaster().getId();
                        if (!map.containsKey(sequenceBedTransferredFrom)) {
                            map.put(sequenceBedTransferredFrom, bedTypeId);
                        }

                        //bedTransferredTo
                        BedConfigurationMaster bedTransferredTo = ipAdmissionBedTransfer.getBedTransferredTo();
                        Long sequenceBedTransferredTo = bedTransferredTo.getBedTypeMaster().getSequenceOrderNo();
                        bedTypeId = bedTransferredTo.getBedTypeMaster().getId();
                        if (!map.containsKey(sequenceBedTransferredTo)) {
                            map.put(sequenceBedTransferredTo, bedTypeId);
                        }
                        arrayList.add(sequenceBedTransferredTo);
                    }
                    if (type.equalsIgnoreCase(Constants.TRANSFER_LOWER_BED_CONSTANT)) {
                        Long minSequenceBedNo = Collections.min(arrayList);
                        bedTypeId = (Long) map.get(minSequenceBedNo);
                    }
                    if (type.equalsIgnoreCase(Constants.TRANSFER_HIGHER_BED_CONSTANT)) {
                        Long maxSequenceBedNo = Collections.max(arrayList);
                        bedTypeId = (Long) map.get(maxSequenceBedNo);
                    }
                }

            }

            response = new GetBedTypeIdForSchedulerByIpAdmissionIdServiceResponse(bedTypeId);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved bed Type Id For Scheduler for given IpAdmissionId Successfully");
            log.debug("Retrieved bed Type Id For Scheduler for given IpAdmissionId Successfully");
        } catch (Exception e) {
            response = new GetBedTypeIdForSchedulerByIpAdmissionIdServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve bed Type Id For Scheduler for given IpAdmissionId");
            log.error("Failed to retrieve bed Type Id For Scheduler for given IpAdmissionId");
        }
        return response;
    }
}
