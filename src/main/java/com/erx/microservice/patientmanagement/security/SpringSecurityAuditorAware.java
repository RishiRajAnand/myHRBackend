package com.erx.microservice.patientmanagement.security;

import com.erx.microservice.patientmanagement.config.Constants;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * Implementation of AuditorAware based on Spring Security.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        // String userName = SecurityUtils.getCurrentUserLogin();
        // @TODO integrate Erx security here
        String userName = null;
        return userName != null ? userName : Constants.SYSTEM_ACCOUNT;
    }
}
