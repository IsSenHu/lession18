package com.husen.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author 11785
 */
@WebServlet("/work.do")
public class WorkServlet extends HttpServlet {
    private final static int MAXAGE = 60 * 60 * 24;
    private final static int CACHE_TIME = 1000 * 30;
    private final static long BIAN = 1000L;
    private final static String UNKNOWN = "unknow";
    private final static String X_FORWARDED_FOR = "x-forwarded-for";
    private final static String PROXY_CLIENT_IP = "Proxy-Client-IP";
    private final static String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    private final static String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    private final static String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";
    private final static String COOKIENAME = "cn.com.biz-united.husen.work2";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long ifModified = request.getDateHeader("If-Modified-Since");
        if(ifModified != -1L){
            long lastModified = getLastModified(request);
            if((ifModified + CACHE_TIME) > lastModified / BIAN * BIAN){
                response.setStatus(304);
                return;
            }
        }
        //设置缓存时间30s
        response.setDateHeader("Expires", System.currentTimeMillis() + 30 * 1000);
        //Cache-Control来控制页面的缓存与否,public:浏览器和缓存服务器都可以缓存页面信息；
        response.setHeader("Cache-Control", "public");
        //Pragma:设置页面是否缓存，为Pragma则缓存，no-cache则不缓存
        response.setHeader("Pragma", "Pragma");
        //设置响应类型和编码为utf-8
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //得到所有的响应头名字
        List<String> responseHeaderNames = (List<String>) response.getHeaderNames();
        //遍历响应头的名字获取其值存入map集合中
        Map<String, String> responseHeaders = new HashMap<>(20);
        for(String headName : responseHeaderNames){
            responseHeaders.put(headName, response.getHeader(headName));
        }
        //得到所有的请求头名字
        Enumeration<String> requestHeaderNames = request.getHeaderNames();
        //遍历请求头名字获取其值存入map集合中
        Map<String, String> requestHeaders  = new HashMap<>(20);
        while(requestHeaderNames.hasMoreElements()){
            String headName = requestHeaderNames.nextElement();
            requestHeaders.put(headName, request.getHeader(headName));
        }
        //获取用户真实Ip地址
        String iP = getIpAddress(request);
        //保存以上信息进request域中
        request.setAttribute("resHeads", responseHeaders);
        request.setAttribute("reqHeads", requestHeaders);
        request.setAttribute("Ip", iP);
        /*
        * 设置cookie
        * */
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            Map<String, Object> cookieMap = new HashMap<>(20);
            for (Cookie c : cookies) {
                //如果有这个cookie，就说明不是第一次访问了。
                if (c.getName().equals(COOKIENAME)) {
                    //然后将值存入一个map中
                    String name = c.getName();
                    if (name != null) {
                        cookieMap.put("name", name);
                    } else {
                        cookieMap.put("name", "没有设置该值");
                    }
                    String value = c.getValue();
                    if (value != null) {
                        cookieMap.put("value", value);
                    } else {
                        cookieMap.put("value", "没有设置该值");
                    }
                    String secure = c.getSecure() + "";
                    cookieMap.put("secure", secure);
                    String path = c.getPath();
                    if (path != null) {
                        cookieMap.put("path", path);
                    } else {
                        cookieMap.put("path", "没有设置该值");
                    }
                    cookieMap.put("maxAge", MAXAGE);
                    String domain = c.getDomain();
                    if (domain != null) {
                        cookieMap.put("domain", domain);
                    } else {
                        cookieMap.put("domain", "没有设置该值");
                    }
                    String comment = c.getComment();
                    if (comment != null) {
                        cookieMap.put("comment", comment);
                    } else {
                        cookieMap.put("comment", "没有设置该值");
                    }
                    Integer version = c.getVersion();
                    cookieMap.put("version", version);
                    request.setAttribute("cookies", cookieMap);
                    request.getRequestDispatcher("/show.jsp").forward(request, response);
                    return;//然后直接退出
                }
            }
        }
        //否则循环结束走这一步
        Cookie cookie = new Cookie("cn.com.biz-united.husen.work2", UUID.randomUUID().toString());
        cookie.setMaxAge(MAXAGE);
        response.addCookie(cookie);
        request.setAttribute("cookiee", cookie);
        request.getRequestDispatcher("/show.jsp").forward(request, response);
    }
    private static String getIpAddress(HttpServletRequest request) {
                 String ip = request.getHeader(X_FORWARDED_FOR);
                 if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                         ip = request.getHeader(PROXY_CLIENT_IP);
                    }
                 if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                         ip = request.getHeader(WL_PROXY_CLIENT_IP);
                     }
                if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                       ip = request.getHeader(HTTP_CLIENT_IP);
                    }
                 if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                         ip = request.getHeader(HTTP_X_FORWARDED_FOR);
                     }
                 if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                         ip = request.getRemoteAddr();
                     }
                 return ip;
            }

    @Override
    protected long getLastModified(HttpServletRequest req) {
        return System.currentTimeMillis();
    }
}
