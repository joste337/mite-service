package de.jos.service.mite.miteservice.model;

import java.util.Arrays;

public class MiteServiceReply {
    private boolean success;
    private ServiceResponse.Service[] services;
    private ProjectResponse.Project[] projects;

    public MiteServiceReply() {
    }

    public MiteServiceReply(boolean success) {
        this.success = success;
    }

    public MiteServiceReply(boolean success, ServiceResponse.Service[] services, ProjectResponse.Project[] projects) {
        this.success = success;
        this.services = services;
        this.projects = projects;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ServiceResponse.Service[] getServices() {
        return services;
    }

    public void setServices(ServiceResponse.Service[] services) {
        this.services = services;
    }

    public ProjectResponse.Project[] getProjects() {
        return projects;
    }

    public void setProjects(ProjectResponse.Project[] projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "success: " + success + "\nservices: " + Arrays.toString(services) + "\nprojects: " + Arrays.toString(projects);
    }
}
