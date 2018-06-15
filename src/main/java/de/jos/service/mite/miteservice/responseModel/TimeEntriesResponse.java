package de.jos.service.mite.miteservice.responseModel;

public class TimeEntriesResponse {
    private ResponseTimeEntry time_entry;

    public ResponseTimeEntry getTime_entry() {
        return time_entry;
    }

    public void setTime_entry(ResponseTimeEntry time_entry) {
        this.time_entry = time_entry;
    }

    public class ResponseTimeEntry {
        private String id;
        private String minutes;
        private String date_at;
        private String note;
        private String project_name;
        private String service_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMinutes() {
            return minutes;
        }

        public void setMinutes(String minutes) {
            this.minutes = minutes;
        }

        public String getDate_at() {
            return date_at;
        }

        public void setDate_at(String date_at) {
            this.date_at = date_at;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getProject_name() {
            return project_name;
        }

        public void setProject_name(String project_name) {
            this.project_name = project_name;
        }

        public String getService_name() {
            return service_name;
        }

        public void setService_name(String service_name) {
            this.service_name = service_name;
        }

        public String toReplyString() {
            return "Date: " + date_at + "; " + project_name + " - " + service_name + " - " + minutes + " - " + note;
        }
    }

}
