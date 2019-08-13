<!DOCTYPE html>
<%@ page language="java" isErrorPage="true"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>

    <jsp:include page="_menu.jsp"></jsp:include>

    <h3>Hello: ${loginedUser.username}</h3>

    User Name: <b>${loginedUser.username}</b>
    <br />

    <script src="${pageContext.request.contextPath}/js/language.js"></script>
</body>
</html>
