package com.erx.microservice.patientmanagement.web.rest;

import com.erx.microservice.patientmanagement.config.DefaultProfileUtil;
import com.erx.microservice.patientmanagement.config.ErxProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Resource to return information about the currently running Spring profiles.
 */
@RestController
@RequestMapping("/api")
public class ProfileInfoResourceController {

    private final Environment env;

    private final ErxProperties erxProperties;

    public ProfileInfoResourceController(Environment env, ErxProperties erxProperties) {
        this.env = env;
        this.erxProperties = erxProperties;
    }

    @GetMapping("/profile-info")
    public ProfileInfoVM getActiveProfiles() {
        String[] activeProfiles = DefaultProfileUtil.getActiveProfiles(env);
        return new ProfileInfoVM(activeProfiles);
    }


    class ProfileInfoVM {

        private String[] activeProfiles;


        ProfileInfoVM(String[] activeProfiles) {
            this.activeProfiles = activeProfiles;

        }

        public String[] getActiveProfiles() {
            return activeProfiles;
        }

    }
}
