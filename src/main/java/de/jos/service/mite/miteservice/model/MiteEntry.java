package de.jos.service.mite.miteservice.model;


public class MiteEntry {
    private TimeEntry time_entry;

    public MiteEntry(String minutes, String note, String project_id, String service_id) {
        this.time_entry = new TimeEntry(minutes, note, project_id, service_id);
    }

    public TimeEntry getTime_entry() {
        return time_entry;
    }

    public void setTime_entry(TimeEntry timeEntry) {
        this.time_entry = timeEntry;
    }

    public class TimeEntry {
        private String minutes;
        private String note;
        private String project_id;
        private String service_id;

        public TimeEntry (String minutes, String note, String project_id, String service_id) {
            this.minutes = minutes;
            this.note = note;
            this.project_id = project_id;
            this.service_id = service_id;
        }

        public String getMinutes() {
            return minutes;
        }

        public void setMinutes(String minutes) {
            this.minutes = minutes;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getProject_id() {
            return project_id;
        }

        public void setProject_id(String project_id) {
            this.project_id = project_id;
        }

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }
    }
}
