package com.husen.oss;

import java.io.InputStream;
import java.util.Date;

/**
 * @author 11785
 */
public interface IOssClientService {

    /**
     * 获取FromKey，通过参数key来获取，这是一个抽象方法
     * @param key
     * @return
     * 获取FromKey，通过参数key来获取，这是一个抽象方法
     */
     public String genUrlFromKey(String key);

    /**
     * 获取FromKey，通过参数key和date来获取，这是一个抽象方法
     * @param key
     * @param date
     * @return
     * 获取FromKey，通过参数key来获取，这是一个抽象方法
     */
     public String genUrlFromKey(String key, Date date);

    /**
     * 获取输入流，通过传入的key,也就是路径来获得，这是一个抽象方法
     * @param key
     * @return
     * 获取FromKey，通过参数key来获取，这是一个抽象方法
     */
     public InputStream getDocument(String key);
}
