package de.jos.service.mite.miteservice.controller;


import de.jos.service.mite.miteservice.manager.EntryManager;
import de.jos.service.mite.miteservice.manager.ProjectManager;
import de.jos.service.mite.miteservice.manager.ServiceManager;
import de.jos.service.mite.miteservice.manager.VerifyManager;
import de.jos.service.mite.miteservice.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class MiteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MiteService.class);

    @Autowired
    private ServiceManager serviceManager;
    @Autowired
    private ProjectManager projectManager;
    @Autowired
    private VerifyManager verifyManager;
    @Autowired
    private EntryManager entryManager;

    @GetMapping("/newEntry")
    public MiteServiceReply createNewEntry(@Validated @ModelAttribute MiteRequestAttributes miteRequestAttributes) {
        MiteRequest miteRequest = createMiteRequestFromAttributes("time_entries.json", miteRequestAttributes);
        LOGGER.info("New request");

        return entryManager.createNewEntry(miteRequest, entryManager.parseMiteRequestAttributesToMiteEntry(miteRequestAttributes));
    }

    @GetMapping("/project")
    public MiteServiceReply getAvailableProjectsByName(@Validated @ModelAttribute MiteRequestAttributes miteRequestAttributes) {
        MiteRequest miteRequest = createMiteRequestFromAttributes("projects.json", miteRequestAttributes);
        LOGGER.info("Project request for name: {}", miteRequestAttributes.getSearchParam());

        return projectManager.getProjects(miteRequest);
    }

    @GetMapping("/service")
    public MiteServiceReply getAvailableServicesByName(@Validated @ModelAttribute MiteRequestAttributes miteRequestAttributes) {
        MiteRequest miteRequest = createMiteRequestFromAttributes("services.json", miteRequestAttributes);
        LOGGER.info("Service request for name: {}", miteRequestAttributes.getSearchParam());

        return serviceManager.getServices(miteRequest);
    }

    @GetMapping("/verifyApiKey")
    public MiteServiceReply verifyApiKey(@Validated @ModelAttribute MiteRequestAttributes miteRequestAttributes) {
        MiteRequest miteRequest = createMiteRequestFromAttributes("myself.json", miteRequestAttributes);
        LOGGER.info("Verify request");

        return verifyManager.verify(miteRequest);
    }

    private MiteRequest createMiteRequestFromAttributes(String path, MiteRequestAttributes miteRequestAttributes) {
        MiteRequest miteRequest = new MiteRequest();
        miteRequest.buildRequest(path, miteRequestAttributes);
        return miteRequest;
    }
}
