package com.erx.microservice.patientmanagement.service.taskmanagement.taskcategory;

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.taskmanagementdto.TaskCategoryDTO;
import org.springframework.data.domain.Page;

public class TaskCategorySearchServiceResponse   extends CommonServiceResponse {

    private Page<TaskCategoryDTO> taskCategoryDTOs;

    public Page<TaskCategoryDTO> getTaskCategoryDTOs() {
        return taskCategoryDTOs;
    }

    public void setTaskCategoryDTOs(Page<TaskCategoryDTO> taskCategoryDTOs) {
        this.taskCategoryDTOs = taskCategoryDTOs;
    }
}
