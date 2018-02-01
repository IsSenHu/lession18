<%--
  Created by IntelliJ IDEA.
  User: 11785
  Date: 2018/1/17
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
    <link type="text/css" media="all" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
    <link type="text/css" media="all" href="${pageContext.request.contextPath}/css/daterangepicker.css" rel="stylesheet"/>
    <link type="text/css" media="all" href="${pageContext.request.contextPath}/css/daterangepicker-bs3.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
    <!-- 引入支持中文 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/moment.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/daterangepicker.js"></script>
    <title>下载微信</title>
</head>
<style>
    img{
        border: 3px double green;
    }
</style>
<body>
    <div class="row">
        <div class="col-xs-4"></div>
        <div class="col-xs-4" style="text-align: center;">
            <img src="${pageContext.request.contextPath}/image/new.png"/>
            <h2>扫描二维码下载微信app</h2>
        </div>
        <div class="col-xs-4"></div>
    </div>
</body>
</html>
