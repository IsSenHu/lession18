package com.husen.oss;

import java.io.IOException;
import java.util.Properties;

public class OssConfigure {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String accessUrl;

    public OssConfigure() {

    }

    public OssConfigure(String storageConfName) throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream(storageConfName));
        endpoint = properties.getProperty("Endpoint");
        accessKeyId = properties.getProperty("AccessKeyId");
        accessKeySecret = properties.getProperty("AccessKeySecret");
        bucketName = properties.getProperty("BucketName");
        accessUrl = properties.getProperty("accessUrl");
    }

    public OssConfigure(String endpoint, String accessKeyId,
                        String accessKeySecret, String bucketName, String accessUrl) {

        this.endpoint = endpoint;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.bucketName = bucketName;
        this.accessUrl = accessUrl;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }
}
