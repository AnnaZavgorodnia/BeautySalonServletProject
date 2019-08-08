<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script|Montserrat:400,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <title>Home Page</title>
</head>
<body>

<jsp:include page="_menu.jsp"></jsp:include>

<div class="video__intro">
    <div class="video">
        <video class="video__media" src="${pageContext.request.contextPath}/static/images/fashion.mp4" autoplay muted loop></video>
    </div>

    <div class="intro__content">
        <div class="container">
            <div class="intro__inner">
                <h2 class="intro__subtitle"><fmt:message key="landing.greeting"/></h2>
                <h1 class="intro__title"><fmt:message key="landing.name"/></h1>
                <a class="btn" href="#"><fmt:message key="landing.button.learn.more"/></a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
