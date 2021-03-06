<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script|Montserrat:400,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">
    <title>Make an appointment</title>
</head>
<body>

    <jsp:include page="_menu.jsp"></jsp:include>

    <section class="section top">
    </section>
    <section class="section">
        <div class="container">
            <div class="section__header">
                <h3 class="section__subtitle">
                    <fmt:message key="create.appointment.title"/>
                </h3>
            </div>
            <div class="create__app__content">
                <input type="hidden" value="${master.id}" id="master_id"/>
                <div class="create__app__master__image" id="masterImg">
                    <img src="${pageContext.request.contextPath}/masters/${master.imagePath}">
                </div>
                <div class="input-field">
                    <select id="select-service">
                        <option value="" disabled selected><fmt:message key="create.appointment.choose.option.title"/></option>
                        <c:forEach items="${master.services}" var="i">
                            <c:if test="${lang eq 'en'}">
                                <option value="${i.id}"
                                        id="${i.name}"
                                        data-price="${i.price}">${i.name}</option>
                            </c:if>
                            <c:if test="${lang eq 'ua'}">
                                <option value="${i.id}"
                                        id="${i.nameUa}"
                                        data-price="${i.price}">${i.nameUa}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <label for="select-service" id="select_label">
                        <fmt:message key="create.appointment.choose.option.title"/>...
                    </label>
                </div>
                <div class="create__app__time__date">
                    <label for="date">
                        <fmt:message key="create.appointment.date"/>
                    </label>
                    <input type="text" id="date" class="datepicker">
                    <div class="create__app__time" id="time-table" data-book="<fmt:message key="create.appointment.button.master"/>">
                    </div>
                </div>
            </div>
        </div>
    </section>

    <div id="modal" class="modal" style="width: 40%">
        <div class="modal-content">
            <div class="row">
                <div class="col s6" style="font-size: 20px">
                    <p id="master-modal">
                        <fmt:message key="master.title"/>:
                        <c:if test="${lang eq 'en'}">${master.fullName}</c:if>
                        <c:if test="${lang eq 'ua'}">${master.fullNameUa}</c:if>
                    </p>
                    <p><fmt:message key="create.appointment.service.title"/>: <span id="service-modal"></span></p>
                    <p><fmt:message key="create.appointment.price.title"/>: <span id="price-modal"></span></p>
                    <p><fmt:message key="create.appointment.date.title"/>: <span id="date-modal"></span></p>
                    <p><fmt:message key="create.appointment.time.title"/>: <span id="time-modal"></span></p>
                </div>
                <div class="col s6 center-align">
                    <img style="width: 60%" src="${pageContext.request.contextPath}/masters/${master.imagePath}">
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="modal-close waves-effect waves-red btn-flat">
                <fmt:message key="button.cancel"/>
            </button>
            <button type="button" id="button-book" class="modal-close waves-effect waves-green btn-flat">
                <fmt:message key="create.appointment.button.master"/>
            </button>
        </div>
    </div>

    <div id="modal-exists" class="modal" style="width: 40%">
        <div class="modal-content">
            <h3><fmt:message key="create.appointment.exists"/></h3>
        </div>
        <div class="modal-footer">
            <button type="button" class="modal-close waves-effect waves-red btn-flat">
                <fmt:message key="button.ok"/>
            </button>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/book_app.js"></script>
    <script src="${pageContext.request.contextPath}/js/language.js"></script>
</body>
</html>
