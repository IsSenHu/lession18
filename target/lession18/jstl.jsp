<%@ page import="java.util.*" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="com.husen.pojo.User" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: 11785
  Date: 2018/1/16
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>jstl</title>
    <%
        /*
        * response头
        * */
        Collection<String> responseHeadersNames = response.getHeaderNames();
        Map<String, String> responseHeaders = new HashMap<>();
        for (String responseHeadersName : responseHeadersNames){
            responseHeaders.put(responseHeadersName, response.getHeader(responseHeadersName));
        }
        request.setAttribute("responseHeaders", responseHeaders);
        /*
        * List遍历并格式化数字
        * */
        List<Double> numberList = new ArrayList<>();
        numberList.add(12312312312.2122);
        numberList.add(123123123222.1232);
        numberList.add(4523423452352.2223);
        request.setAttribute("numberList", numberList);
        request.setAttribute("date", new Date());
        /*
        * map遍历
        * */
        Map<String, User> map = new HashMap<>();
        User user = new User();
        User user2 = new User();
        User user3 = new User();
        user.setUid(1);
        user.setName("胡森");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("1995-05-28");
        user.setBirthday(date);
        user2.setUid(2);
        user2.setName("胡森2");
        user2.setBirthday(date);
        user3.setUid(3);
        user3.setName("胡森3");
        user3.setBirthday(date);
        map.put("user1", user);
        map.put("user2", user2);
        map.put("user3", user3);
        request.setAttribute("map", map);
    %>
</head>
<body>
    <div class="row">
        <div class="col-xs-4"></div>
        <div class="col-xs-4">
            <table class="table table-bordered">
                <caption>请求头</caption>
                <thead>
                    <tr>
                        <th style="width: 40%;">name</th>
                        <th>value</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${header}" var="head">
                        <tr>
                            <td>${head.key}</td>
                            <td>${head.value}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <table class="table table-bordered">
                <caption>响应头</caption>
                <thead>
                <tr>
                    <th style="width: 40%;">name</th>
                    <th>value</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${responseHeaders}" var="head">
                    <tr>
                        <td>${head.key}</td>
                        <td>${head.value}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <table class="table table-bordered">
                <caption>Map遍历</caption>
                <thead>
                <tr>
                    <th style="width: 40%;">key</th>
                    <th>用户ID</th>
                    <th>用户姓名</th>
                    <th>用户生日</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${map}" var="user">
                    <tr>
                        <td>${user.key}</td>
                        <td>${user.value.uid}</td>
                        <td>${user.value.name}</td>
                        <td><fmt:formatDate value="${user.value.birthday}" pattern="yyyy年MM月dd日"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <table class="table table-bordered">
                <caption>List遍历格数字并格式化输出</caption>
                <thead>
                <tr>
                    <th style="width: 40%;">原显示</th>
                    <th>格式化后显示</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${numberList}" var="number">
                    <tr>
                        <td>${number}</td>
                        <td><fmt:formatNumber pattern=".00">${number}</fmt:formatNumber></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <table class="table table-bordered">
                <caption>格式化日期</caption>
                <thead>
                <tr>
                    <th style="width: 40%;">原显示</th>
                    <th>格式化后显示</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${date}</td>
                        <td><fmt:formatDate value="${date}" pattern="yyyy年MM月dd日"/></td>
                    </tr>
                    <tr>
                        <td>${date}</td>
                        <td><fmt:formatDate value="${date}" pattern="E"/></td>
                    </tr>
                    <tr>
                        <td>${date}</td>
                        <td><fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                    <tr>
                        <td>${date}</td>
                        <td><fmt:formatDate value="${date}" pattern="HH:mm"/></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="col-xs-4"></div>
    </div>
</body>
</html>
