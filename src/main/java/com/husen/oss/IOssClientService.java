package com.husen.oss;

import java.io.InputStream;
import java.util.Date;

public interface IOssClientService {
    //void saveDocument(InputStream inputStream, String key, String contentType, Map<String, String> metadata) throws IOException;

    String genUrlFromKey(String key);

    String genUrlFromKey(String key, Date date);

    InputStream getDocument(String key);
}
