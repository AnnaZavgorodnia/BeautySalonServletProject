<!DOCTYPE html>
<%@ page language="java" isErrorPage="true"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Error</title>
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script|Montserrat:200,400,700&display=swap&subset=cyrillic" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/styles/error.css" />
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>

<div id="notfound">
    <div class="notfound">
        <div class="notfound-404">
            <h1><fmt:message key="error.page.oops"/></h1>
            <h2><fmt:message key="error.page.message"/></h2>
        </div>
        <a href="${pageContext.request.contextPath}/">
            <fmt:message key="error.page.button"/>
        </a>
    </div>
</div>
</body>
</html>
