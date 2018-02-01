package com.husen.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class IOssClientServiceImpl implements IOssClientService {
    private OSSClient ossClient;
    public IOssClientServiceImpl() throws IOException {
        OssConfigure ossConfigure = new OssConfigure("aliyunoss.properties");
        ossClient = new OSSClient(ossConfigure.getEndpoint(), ossConfigure.getAccessKeyId(), ossConfigure.getAccessKeySecret());
    }

    public void saveDocument(InputStream inputStream, String bucketName, String fileName) throws IOException {
        PutObjectResult putObjectResult = ossClient.putObject(bucketName, fileName, inputStream);
        ossClient.shutdown();
    }

    public String genUrlFromKey(String key) {
        return null;
    }

    public String genUrlFromKey(String key, Date date) {
        return null;
    }

    public InputStream getDocument(String key) {
        return null;
    }
}
