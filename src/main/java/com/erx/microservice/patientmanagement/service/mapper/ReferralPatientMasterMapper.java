package com.erx.microservice.patientmanagement.service.mapper;


import com.erx.microservice.patientmanagement.domain.ReferralPatientMaster;
import com.erx.microservice.patientmanagement.service.dto.patientreferraldto.CreateReferralPatientMasterDTO;
import com.erx.microservice.patientmanagement.service.dto.patientreferraldto.ReferralPatientMasterDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface ReferralPatientMasterMapper {

    ReferralPatientMasterDTO referralPatientMasterTOReferralPatientMasterDTO(ReferralPatientMaster referralPatientMaster);

    ReferralPatientMaster referralPatientMasterDTOTOReferralPatientMaster(ReferralPatientMasterDTO referralPatientMasterDTO);

    List<ReferralPatientMasterDTO> referralPatientMasterTOReferralPatientMasterDTOs(List<ReferralPatientMaster> referralPatientMasters);

    List<ReferralPatientMaster> referralPatientMasterDTOTOReferralPatientMasters(List<ReferralPatientMasterDTO> referralPatientMasterDTOs);

    ReferralPatientMaster createReferralPatientMasterDTOTOReferralPatientMaster(CreateReferralPatientMasterDTO createReferralPatientMasterDTO);


}
