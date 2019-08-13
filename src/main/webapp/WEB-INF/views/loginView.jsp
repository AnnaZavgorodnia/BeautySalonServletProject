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
    <title>Login</title>
</head>
<body>

    <jsp:include page="_menu.jsp"></jsp:include>

    <div class="form__intro"></div>
    <div class="form__wrapper">
        <h2><fmt:message key="login.page.form.name"/></h2>
        <div class="form__container">
            <c:if test="${not empty errorMessage}">
                <div>
                    <fmt:message key="${errorMessage}"/>
                </div>
            </c:if>
            <c:if test="${not empty logout}">
                <div>
                    <fmt:message key="login.page.logout"/>
                </div>
            </c:if>
            <form method="POST" action="${pageContext.request.contextPath}/app/login" id="loginForm" autocomplete="off">
                <input type="hidden" name="redirectId" value="${param.redirectId}" />
                <label for="username"><fmt:message key="login.page.username"/></label>
                <input class="form__input"
                       type="text"
                       placeholder="Username"
                       required
                       id="username"
                       name="username">
                <br>
                <label for="password"><fmt:message key="login.page.password"/></label>
                <input class="form__input"
                       type="password"
                       placeholder="Password"
                       required
                       name="password"
                       id="password">
                <br><br>
                <button class="form__button"
                        type="submit"><fmt:message key="login.page.button"/></button>
            </form>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/language.js"></script>
</body>
</html>