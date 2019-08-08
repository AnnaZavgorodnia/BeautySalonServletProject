<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Our Masters</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script|Montserrat:400,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
    <jsp:include page="_menu.jsp"></jsp:include>
    <section class="section top">
    </section>
    <section class="section">
        <div class="container">
            <div class="section__header">
                <h3 class="section__subtitle"><fmt:message key="masters.page.who.we.are"/></h3>
                <h2 class="section__title"><fmt:message key="masters.page.our.team"/></h2>
                <div class="section__text">
                    <p><fmt:message key="masters.page.team.text"/></p>
                </div>
            </div>
            <div class="about" id="masters">
                <c:forEach var="i" items="${masters}">
                    <div class="about__item">
                        <div class="about__img">
                            <img src="${pageContext.request.contextPath}/masters/${i.imagePath}" />
                        </div>
                        <div class="about__text">
                            <h4>${i.fullName}</h4>
                            <a href="https://www.instagram.com/${i.instagram}/">
                                <i class="fab fa-instagram"></i>
                            </a>
                            <a class="btn app-btn" href="${pageContext.request.contextPath}/app/create_app?masterId=${i.id}">
                                <fmt:message key="masters.page.make.appointment"/>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
</body>
</html>
