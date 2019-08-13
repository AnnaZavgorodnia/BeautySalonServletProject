<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script|Montserrat:400,700&display=swap" rel="stylesheet">
    <meta charset="UTF-8">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/core/main.min.css" integrity="sha256-Lfe6+s5LEek8iiZ31nXhcSez0nmOxP+3ssquHMR3Alo=" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/daygrid/main.min.css" integrity="sha256-AVsv7CEpB2Y1F7ZjQf0WI8SaEDCycSk4vnDRt0L2MNQ=" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/timegrid/main.min.css" integrity="sha256-DOWdbe6a1VwJwFpkimY6z5tW9mmrBNre2jZsAige5PE=" crossorigin="anonymous" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <title>All appointments</title>
</head>
<body>

    <jsp:include page="_menu.jsp"></jsp:include>

    <section class="section top"></section>

    <div class="container">
        <div id='calendar'></div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/core/main.min.js" integrity="sha256-GBryZPfVv8G3K1Lu2QwcqQXAO4Szv4xlY4B/ftvyoMI=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/daygrid/main.min.js" integrity="sha256-FT1eN+60LmWX0J8P25UuTjEEE0ZYvpC07nnU6oFKFuI=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/timegrid/main.min.js" integrity="sha256-L9T+qE3Ms6Rsuxl+KwLST6a3R/2o6m33zB5mR2KyPjU=" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/js/all_apps.js"></script>
    <script src="${pageContext.request.contextPath}/js/language.js"></script>
</body>
</html>

