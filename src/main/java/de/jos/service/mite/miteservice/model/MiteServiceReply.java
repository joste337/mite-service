package de.jos.service.mite.miteservice.model;

public class MiteServiceReply {
    private boolean success;
    private String message;
    private ServiceResponse.Service[] services;
    private ProjectResponse.Project[] projects;

    public MiteServiceReply() {
    }

    public MiteServiceReply(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}
