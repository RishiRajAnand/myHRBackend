package com.erx.microservice.patientmanagement.service.mapper.casemanagement;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmMasterComplaint;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmMasterComplaintDTO;

import java.util.List;


public interface CmMasterComplaintsMapper {

    CmMasterComplaintDTO cmMasterComplaintsToCmMasterComplaintDTO(CmMasterComplaint cmMasterComplaints);

    CmMasterComplaint cmMasterComplaintDTOToCmMasterComplaints(CmMasterComplaintDTO cmMasterComplaintDTO);

    List<CmMasterComplaintDTO> cmMasterComplaintsToCmMasterComplaintDTOs(List<CmMasterComplaint> cmMasterComplaints);

    List<CmMasterComplaint> cmMasterComplaintDTOsToCmMasterComplaints(List<CmMasterComplaintDTO> cmMasterComplaintDTOs);
}
