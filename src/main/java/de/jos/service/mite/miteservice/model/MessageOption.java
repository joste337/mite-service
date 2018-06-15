package de.jos.service.mite.miteservice.model;

public class MessageOption {
    private String message;
    private String url;

    public MessageOption(String message, String url) {
        this.message = message;
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MessageOption{" +
                "message='" + message + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
