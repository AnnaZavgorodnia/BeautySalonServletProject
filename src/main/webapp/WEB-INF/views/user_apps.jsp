<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script|Montserrat:400,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <title>My appointments</title>
</head>
<body>

    <jsp:include page="_menu.jsp"></jsp:include>

    <section class="section top">
    </section>
    <section class="section">
        <div class="container">
            <div class="section__header">
                <h3 class="section__subtitle"><fmt:message key="users.apps.title"/></h3>
            </div>
            <div class="user__app__content">
                <c:forEach var="i" items="${appointments}">
                    <div class="card my-card" id="${i.id}">
                        <div class="card-header">
                            <h5>${i.appDate} ${i.appTime}</h5>
                        </div>
                        <div class="card-body">
                            <p class="card-text"><fmt:message key="master.title"/>: ${i.master.fullName}</p>
                            <p class="card-text"><fmt:message key="service.title"/>: ${i.service.name}</p>
                            <p class="card-text"><fmt:message key="price.title"/>: ${i.service.price}</p>
                            <a href="${pageContext.request.contextPath}/app/me/appointments/delete?appId=${i.id}"
                               class="btn btn-danger">
                               <fmt:message key="button.cancel"/>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
</body>
</html>
