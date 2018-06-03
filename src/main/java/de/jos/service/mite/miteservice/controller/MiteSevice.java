package de.jos.service.mite.miteservice.controller;


import de.jos.service.mite.miteservice.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MiteSevice {
    private static final Logger LOGGER = LoggerFactory.getLogger(MiteSevice.class);

    @Autowired
    private MiteClient miteClient;

    // private String mtr3ID = "2351287";
    // private String developmentID = "253445";

    @GetMapping("/newEntry")
    public MiteServiceReply createNewEntry(@Validated @ModelAttribute MiteRequestAttributes miteRequestAttributes) {
        MiteRequest miteRequest = getMiteRequest("time_entries.json", miteRequestAttributes);

        try {
            miteClient.createNewEntry(miteRequest,
                    new MiteEntry(miteRequestAttributes.getDuration(),
                            miteRequestAttributes.getComment(),
                            miteRequestAttributes.getProjectId(),
                            miteRequestAttributes.getServiceId()));
            return new MiteServiceReply(true);
        } catch (Exception e) {
            LOGGER.error("Failed to execute new-Command: {}", e.getMessage());
            return new MiteServiceReply(false);
        }
    }

    @GetMapping("/projects")
    public MiteServiceReply getAvailableProjectsByName(@Validated @ModelAttribute MiteRequestAttributes miteRequestAttributes) {
        LOGGER.info("projectResponse request for name: {}", miteRequestAttributes.getSearchParam());
        String url = miteBaseUrl + "projects.json?api_key=" + miteRequestAttributes.getApiKey() + "&name=" + miteRequestAttributes.getSearchParam();
        ProjectResponse[] projects;

        try {
            projects = restTemplate.getForObject(url, ProjectResponse[].class);
            System.out.println("projects: " + projects);
            MiteServiceReply miteServiceReply = new MiteServiceReply(true);
            List<ProjectResponse.Project> projectList = new ArrayList<>();
            Arrays.asList(projects).forEach(project -> {
                projectList.add(project.getProject());
                System.out.println("project: " + project.getProject().getName());
            });
            miteServiceReply.setProjects(projectList.toArray(new ProjectResponse.Project[projectList.size()]));
            return miteServiceReply;
        } catch (Exception e) {
            LOGGER.error("Failed to execute project-Command: {}", e.getMessage());
            return new MiteServiceReply(false);
        }
    }

    @GetMapping("/services")
    public MiteServiceReply getAvailableServicesByName(@Validated @ModelAttribute MiteRequestAttributes miteRequestAttributes) {
        String url = miteBaseUrl + "services.json?api_key=" + miteRequestAttributes.getApiKey() + "&name=" + miteRequestAttributes.getSearchParam();
        ServiceResponse[] services;

        try {
            services = restTemplate.getForObject(url, ServiceResponse[].class);
            MiteServiceReply miteServiceReply = new MiteServiceReply(true);
            List<ServiceResponse.Service> serviceList = new ArrayList<>();
            Arrays.asList(services).forEach(service -> serviceList.add(service.getService()));
            miteServiceReply.setServices(serviceList.toArray(new ServiceResponse.Service[serviceList.size()]));
            return miteServiceReply;
        } catch (Exception e) {
            LOGGER.error("Failed to execute service-Command: {}", e.getMessage());
            return new MiteServiceReply(false);
        }
    }

    @GetMapping("/verifyApiKey")
    public MiteServiceReply verifyApiKey(@Validated @ModelAttribute MiteRequestAttributes miteRequestAttributes) {
        String url = miteBaseUrl + "myself.json?api_key=" + miteRequestAttributes.getApiKey();
        try {
            restTemplate.getForObject(url, Object.class);
            return new MiteServiceReply(true);
        } catch (Exception e) {
            return new MiteServiceReply(false);
        }
    }

    private MiteRequest getMiteRequest(String path, MiteRequestAttributes miteRequestAttributes) {
        MiteRequest miteRequest = new MiteRequest();
        miteRequest.buildRequest(path, miteRequestAttributes);
        return miteRequest;
    }
}
