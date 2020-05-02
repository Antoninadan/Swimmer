package ua.i.mail100.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


// DONT USE
@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}