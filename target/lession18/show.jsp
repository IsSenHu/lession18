<%--
  Created by IntelliJ IDEA.
  User: 11785
  Date: 2017/12/22
  Time: 11:36
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
    <title>作业二结果</title>
</head>
<style type="text/css">
    caption{
        font-size: 20px;
        color: blue;
    }
</style>
<body style="text-align: center;">
    <div class="center-block">
        <a style="font-size: 26px;" href="${pageContext.request.contextPath}/work.do">再次访问</a>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-mid-12 col-lg-12" style="border: 3px double #E1E1E1;">
                <table class="table table-hover">
                    <caption>你的Ip地址</caption>
                    <tbody>
                        <tr>
                            <td>${Ip}</td>
                        </tr>
                    </tbody>
                </table>
                <table class="table table-hover">
                    <caption>请求头</caption>
                    <thead>
                    <tr>
                        <td style="width: 30%;">名字</td>
                        <td>值</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${header}" var="head">
                    <tr>
                        <td style="width: 30%;">${head.key}</td>
                        <td>${head.value}</td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <table class="table table-hover">
                    <caption>响应头</caption>
                    <thead>
                    <tr>
                        <td style="width: 30%;">名字</td>
                        <td>值</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${resHeads}" var="head">
                    <tr>
                        <td style="width: 30%;">${head.key}</td>
                        <td>${head.value}</td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <table class="table table-hover">
                    <caption>是否第一次访问</caption>
                    <tbody>
                    <tr>
                        <c:if test="${cookies == null}">
                        <td>是</td><td></td>
                        </c:if>
                        <c:if test="${cookies != null}">
                        <td>否</td>
                        </c:if>
                    </tr>
                    <c:if test="${cookies == null}">
                        <tr>
                            <td style="width: 30%;">服务器设置cookie名为</td>
                            <td>${cookiee.name}</td>
                        </tr>
                        <tr>
                            <td style="width: 30%;">设置的maxAge为</td>
                            <td>${cookiee.maxAge}</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
                <c:if test="${cookies != null}">
                <table class="table table-hover">
                    <caption>设置的cookie</caption>
                    <thead>
                    <tr>
                        <td>名字</td>
                        <td>值</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cookies}" var="c">
                    <tr>
                        <td style="width: 30%;">${c.key}</td>
                        <td>${c.value}</td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
