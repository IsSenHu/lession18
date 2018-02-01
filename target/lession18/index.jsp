<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    <title>index</title>
</head>
<style>
    img{
        width: 500px;
        height: 500px;
        border: 3px double blue;
        padding: 5px;
    }
</style>
<body>
    <center><h2 style="color: red;">${msg}</h2></center>
    <div class="row">
        <div class="col-xs-3"></div>
        <div class="col-xs-6">
            <form action="${pageContext.request.contextPath}/upload.do" method="post" enctype="multipart/form-data">
                <table class="table table-bordered">
                    <tr>
                        <td>选择文件</td>
                        <td><input type="file" name="pic" class="form-control"/></td>
                    </tr>
                    <tr>
                        <td>选择文件</td>
                        <td><input type="file" name="pic2" class="form-control"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="上传"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="col-lg-3"></div>
    </div>
    <c:forEach items="${img}" var="img">
        <div class="row">
            <div class="col-xs-4"></div>
            <div class="col-xs-4">
                <img src="${img}"/>
            </div>
            <div class="col-xs-4"></div>
        </div>
    </c:forEach>
</body>
</html>
