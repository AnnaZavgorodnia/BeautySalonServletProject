<!DOCTYPE html>
<%@ page language="java" isErrorPage="true"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head lang="${lang}">
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script|Montserrat:400,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/account.css">
    <title>Login</title>
</head>
<body>

    <jsp:include page="_menu.jsp"></jsp:include>

    <div class="center">
        <div class="avatar">
            <c:if test="${not empty image}">
                <img src="${pageContext.request.contextPath}/masters/${image}" alt="avatar">
            </c:if>
            <c:if test="${empty image}">
                <img src="${pageContext.request.contextPath}/images/masters/default.jpg" alt="avatar">
            </c:if>
        </div>
        <div class="content">
            <h1><fmt:message key="registration.page.full.name"/>: ${loginedUser.fullName}</h1>
            <h2><fmt:message key="role.title"/>: ${loginedUser.role}</h2>
            <h2><fmt:message key="registration.page.email"/>: ${loginedUser.email}</h2>
            <div class="buttons-holder">
                <c:if test="${not empty loginedUser && loginedUser.role == 'CLIENT'}">
                    <a class="btn" href="${pageContext.request.contextPath}/app/me/appointments">
                        <fmt:message key="header.menu.appointments"/>
                    </a>
                    <a class="btn" href="${pageContext.request.contextPath}/app/masters">
                        <fmt:message key="header.menu.masters"/>
                    </a>
                </c:if>
                <c:if test="${not empty loginedUser && loginedUser.role == 'ADMIN'}">
                    <a class="btn" href="${pageContext.request.contextPath}/app/all_masters">
                        <fmt:message key="header.menu.admin.all.masters"/>
                    </a>
                    <a class="btn" href="${pageContext.request.contextPath}/app/all_appointments">
                        <fmt:message key="header.menu.all_appoinments"/>
                    </a>
                </c:if>
                <c:if test="${not empty loginedUser && loginedUser.role == 'MASTER'}">
                    <a class="btn" href="${pageContext.request.contextPath}/app/all_appointments">
                        <fmt:message key="header.menu.all_appoinments"/>
                    </a>
                </c:if>
            </div>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/language.js"></script>
</body>
</html>
