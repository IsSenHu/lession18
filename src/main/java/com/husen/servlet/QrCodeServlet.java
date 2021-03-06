package com.husen.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 11785
 * 扫描二维码进入下载页面，ios的ios的下载页面，安卓的进安卓的下载页面
 */
@WebServlet("/qrcode.do")
public class QrCodeServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(QrCodeServlet.class);
    private static final String IPHONE = "iPhone";
    private static final String ANDROID = "Android";
    private static final String IPHONE_DOWNLOAD_URL = "http://weixin.qq.com/cgi-bin/download302?check=false&uin=&stype=&promote=&fr=&lang=zh_CN&ADTAG=&url=ios";
    private static final String ANDROID_DOWNLOAD_URL = "http://weixin.qq.com/cgi-bin/readtemplate?uin=&stype=&promote=&fr=&lang=zh_CN&ADTAG=&check=false&t=weixin_download_method&sys=android&loc=weixin,android,web,0";
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userAgent = request.getHeader("User-Agent");
        /*
        * 如果是iphone手机
        * */
        if(userAgent.contains(IPHONE)){
            response.sendRedirect(IPHONE_DOWNLOAD_URL);
        }else if(userAgent.contains(ANDROID)){
            response.sendRedirect(ANDROID_DOWNLOAD_URL);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
