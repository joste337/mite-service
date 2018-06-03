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

    @GetMapping("/newEntry")
    public MiteServiceReply createNewEntry(@Validated @ModelAttribute MiteRequestAttributes miteRequestAttributes) {
        MiteRequest miteRequest = getMiteRequest("time_entries.json", miteRequestAttributes);

        return miteClient.createNewEntry(miteRequest,
                new MiteEntry(miteRequestAttributes.getDuration(),
                        miteRequestAttributes.getComment(),
                        miteRequestAttributes.getProjectId(),
                        miteRequestAttributes.getServiceId()));
    }

    @GetMapping("/projects")
    public MiteServiceReply getAvailableProjectsByName(@Validated @ModelAttribute MiteRequestAttributes miteRequestAttributes) {
        MiteRequest miteRequest = getMiteRequest("projects.json", miteRequestAttributes);
        LOGGER.info("Project request for name: {}", miteRequestAttributes.getSearchParam());

        return miteClient.getProjects(miteRequest);
    }

    @GetMapping("/services")
    public MiteServiceReply getAvailableServicesByName(@Validated @ModelAttribute MiteRequestAttributes miteRequestAttributes) {
        MiteRequest miteRequest = getMiteRequest("services.json", miteRequestAttributes);
        LOGGER.info("Service request for name: {}", miteRequestAttributes.getSearchParam());

        return miteClient.getServices(miteRequest);
    }

    @GetMapping("/verifyApiKey")
    public MiteServiceReply verifyApiKey(@Validated @ModelAttribute MiteRequestAttributes miteRequestAttributes) {
        MiteRequest miteRequest = getMiteRequest("myself.json", miteRequestAttributes);
        LOGGER.info("Verify request", miteRequestAttributes.getSearchParam());

        return miteClient.verify(miteRequest);
    }

    private MiteRequest getMiteRequest(String path, MiteRequestAttributes miteRequestAttributes) {
        MiteRequest miteRequest = new MiteRequest();
        miteRequest.buildRequest(path, miteRequestAttributes);
        return miteRequest;
    }
}
