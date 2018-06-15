package de.jos.service.mite.miteservice.responseModel;

public class ProjectResponse {
    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public class Project {
        private String name;
        private String id;

        public Project() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String  getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String toString() {
            return "Project-name: " + name + "; Project-ID: " + id;
        }
    }
}
