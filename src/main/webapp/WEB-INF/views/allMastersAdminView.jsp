<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script|Montserrat:400,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <title>All masters</title>
</head>
<body>
    <jsp:include page="_menu.jsp"></jsp:include>

    <section class="section top"></section>

    <section class="section">
        <div class="container">
            <div class="section__header">
                <h3 class="section__subtitle"><fmt:message key="masters.admin.page.subtitle"/></h3>
            </div>
            <ul class="collection">
                <c:forEach var="i" items="${masters}">
                    <li class="collection-item avatar">
                        <img src="${pageContext.request.contextPath}/masters/${i.imagePath}" alt="" class="circle">
                        <span class="title">${i.fullName}</span>
                        <p>${i.position}</p>
                        <a href="#" class="secondary-content"><i class="fas fa-user-edit"></i></a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </section>


    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
