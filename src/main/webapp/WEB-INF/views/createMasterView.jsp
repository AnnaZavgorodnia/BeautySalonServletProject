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
    <title>Create Master</title>
</head>
<body>
    <jsp:include page="_menu.jsp"></jsp:include>

    <section class="section top"></section>

    <section class="section">
        <div class="container">
            <div class="section__header">
                <h3 class="section__subtitle">
                    <fmt:message key="create.master.title"/>
                </h3>
            </div>
            <div class="row">
                <form class="col s10 offset-s1"
                      method="post"
                      action="${pageContext.request.contextPath}/app/add_master"
                      autocomplete="off"
                      enctype="multipart/form-data">
                    <div class="row">
                        <div class="input-field col s6">
                            <input name="firstName"
                                   id="firstName"
                                   type="text"
                                   class="validate"
                                    <c:if test="${not empty firstName}">
                                           value="${firstName}"
                                    </c:if>
                                   required>
                            <label for="firstName"><fmt:message key="registration.page.first.name.en"/></label>
                            <c:if test="${not empty firstNameError}">
                                <span class="helper-text" style="color: darkred"><fmt:message key="registration.page.first.name.en.master.error"/></span>
                            </c:if>
                        </div>
                        <div class="input-field col s6">
                            <input name="lastName"
                                   id="lastName"
                                   type="text"
                                   class="validate"
                                    <c:if test="${not empty lastName}">
                                           value="${lastName}"
                                    </c:if>
                                   required>
                            <label for="lastName"><fmt:message key="registration.page.last.name.en"/></label>
                            <c:if test="${not empty lastNameError}">
                                <span class="helper-text" style="color: darkred"><fmt:message key="registration.page.last.name.en.master.error"/></span>
                            </c:if>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6">
                            <input name="firstNameUa"
                                   id="firstNameUa"
                                   type="text"
                                   class="validate"
                            <c:if test="${not empty firstNameUa}">
                                   value="${firstNameUa}"
                            </c:if>
                                   required>
                            <label for="firstNameUa"><fmt:message key="registration.page.first.name.ua"/></label>
                            <c:if test="${not empty firstNameUaError}">
                                <span class="helper-text" style="color: darkred"><fmt:message key="registration.page.first.name.ua.master.error"/></span>
                            </c:if>
                        </div>
                        <div class="input-field col s6">
                            <input name="lastNameUa"
                                   id="lastNameUa"
                                   type="text"
                                   class="validate"
                            <c:if test="${not empty lastNameUa}">
                                   value="${lastNameUa}"
                            </c:if>
                                   required>
                            <label for="lastNameUa"><fmt:message key="registration.page.last.name.ua"/></label>
                            <c:if test="${not empty lastNameUaError}">
                                <span class="helper-text" style="color: darkred"><fmt:message key="registration.page.last.name.ua.master.error"/></span>
                            </c:if>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6">
                            <input name="username"
                                   id="username"
                                   type="text"
                                   class="validate"
                                    <c:if test="${not empty username}">
                                           value="${username}"
                                    </c:if>
                                   required>
                            <label for="username"><fmt:message key="registration.page.username"/></label>
                            <c:if test="${not empty usernameError}">
                                <span class="helper-text" style="color: darkred"><fmt:message key="registration.page.username.error"/></span>
                            </c:if>
                            <c:if test="${not empty userExistsError}">
                                <span class="helper-text" style="color: darkred"><fmt:message key="registration.page.user.exists.error"/></span>
                            </c:if>
                        </div>
                        <div class="input-field col s6">
                            <input name="password"
                                   id="password"
                                   type="password"
                                   class="validate"
                                    <c:if test="${not empty password}">
                                           value="${password}"
                                    </c:if>
                                   required>
                            <label for="password"><fmt:message key="registration.page.password"/></label>
                            <c:if test="${not empty passwordError}">
                                <span class="helper-text" style="color: darkred"><fmt:message key="registration.page.password.error"/></span>
                            </c:if>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6">
                            <input name="email"
                                   id="email"
                                   type="email"
                                   class="validate"
                                    <c:if test="${not empty email}">
                                           value="${email}"
                                    </c:if>
                                   required>
                            <label for="email"><fmt:message key="registration.page.email"/></label>
                            <c:if test="${not empty emailError}">
                                <span class="helper-text" style="color: darkred"><fmt:message key="registration.page.email.error"/></span>
                            </c:if>
                        </div>
                        <div class="input-field col s6">
                            <input name="instagram"
                                   id="instagram"
                                   type="text"
                                   class="validate"
                                    <c:if test="${not empty instagram}">
                                           value="${instagram}"
                                    </c:if>
                                   required>
                            <label for="instagram"><fmt:message key="create.master.instagram.label"/></label>
                            <c:if test="${not empty instagramError}">
                                <span class="helper-text" style="color: darkred"><fmt:message key="registration.page.instagram.error"/></span>
                            </c:if>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6">
                            <select id="position-select" name="position" class="validate" required>
                                <c:forEach items="${positions}" var="i">
                                    <option value="${i}">${i}</option>
                                </c:forEach>
                            </select>
                            <label><fmt:message key="create.master.position.label"/></label>
                        </div>
                        <div class="input-field col s6">
                            <select id="service-select" name="services" multiple class="validate" required>
                                <c:forEach items="${services}" var="i">
                                    <c:if test="${lang eq 'en'}">
                                        <option value="${i.id}" selected>${i.name}</option>
                                    </c:if>
                                    <c:if test="${lang eq 'ua'}">
                                        <option value="${i.id}" selected>${i.nameUa}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            <label><fmt:message key="create.master.service.label"/></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="file-field input-field">
                            <div class="row">
                                <div class="col s2 btn-black btn-active" style="height: 35px;">
                                    <span><fmt:message key="create.master.photo.label"/></span>
                                    <input type="file" name="file" class="validate" required>
                                </div>
                                <div class="col s10 file-path-wrapper">
                                    <input class="file-path validate" type="text">
                                </div>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn-black btn-active"><fmt:message key="create.master.button.submit"/></button>
                </form>
            </div>
        </div>
    </section>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/create_master.js"></script>
    <script src="${pageContext.request.contextPath}/js/language.js"></script>
</body>
</html>
