package de.jos.service.mite.miteservice.manager;

import de.jos.service.mite.miteservice.controller.MiteClient;
import de.jos.service.mite.miteservice.model.MiteRequest;
import de.jos.service.mite.miteservice.model.MiteServiceReply;
import de.jos.service.mite.miteservice.model.ProjectResponse;
import de.jos.service.mite.miteservice.model.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class ProjectManager {
    @Autowired
    private MiteClient miteClient;

    public MiteServiceReply getProjects(MiteRequest miteRequest) {
        ProjectResponse[] projects = miteClient.callMiteWithRequestAndResponseType(miteRequest, ProjectResponse[].class);

        MiteServiceReply miteServiceReply = new MiteServiceReply(true);
        miteServiceReply.setServices(Arrays.stream(projects).map(project -> project.getProject()).collect(Collectors.toList()).toArray(new ServiceResponse.Service[projects.length]));
        return miteServiceReply;
    }
}
