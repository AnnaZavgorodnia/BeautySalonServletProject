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
    <title>Registration</title>
</head>
<body>

    <jsp:include page="_menu.jsp"></jsp:include>

    <div class="form__intro"></div>
    <div class="form__wrapper">
        <h2><fmt:message key="registration.page.form.name"/></h2>
        <div class="form__container">
            <form method="POST" action="${pageContext.request.contextPath}/app/registration"  autocomplete="off" >
                <label id="usernameLabel"
                       for="username"><fmt:message key="registration.page.username"/></label>
                <input class="form__input"
                       type="text"
                       id="username"
                        <c:if test="${not empty username}">
                               value="${username}"
                        </c:if>
                       name="username"
                       placeholder="<fmt:message key="registration.page.username.placeholder"/>"
                       required>
                <c:if test="${not empty usernameError}">
                    <label style="color: cornflowerblue"><fmt:message key="registration.page.username.error"/></label>
                    <br>
                </c:if>
                <c:if test="${not empty userExistsError}">
                    <label style="color: cornflowerblue"><fmt:message key="registration.page.user.exists.error"/></label>
                    <br>
                </c:if>
                <label id="fistNameLabel"
                       for="firstName"><fmt:message key="registration.page.first.name"/></label>
                <input type="text"
                       class="form__input"
                       id="firstName"
                       name="firstName"
                        <c:if test="${not empty firstName}">
                                value="${firstName}"
                        </c:if>
                       placeholder="<fmt:message key="registration.page.first.name.placeholder"/>"
                       required>
                <c:if test="${not empty firstNameError}">
                    <label style="color: cornflowerblue"><fmt:message key="registration.page.first.name.user.error"/></label>
                    <br>
                </c:if>
                <label id="lastNameLabel"
                       for="lastName"><fmt:message key="registration.page.last.name"/></label>
                <input type="text"
                       class="form__input"
                       id="lastName"
                        <c:if test="${not empty lastName}">
                               value="${lastName}"
                        </c:if>
                       name="lastName"
                       placeholder="<fmt:message key="registration.page.last.name.placeholder"/>"
                       required>
                <c:if test="${not empty lastNameError}">
                    <label style="color: cornflowerblue"><fmt:message key="registration.page.last.name.user.error"/></label>
                    <br>
                </c:if>
                <label id="emailLabel"
                       for="email"><fmt:message key="registration.page.email"/></label>
                <input type="email"
                       class="form__input"
                       id="email"
                        <c:if test="${not empty email}">
                               value="${email}"
                        </c:if>
                       name="email"
                       placeholder="<fmt:message key="registration.page.email.placeholder"/>"
                       required>
                <c:if test="${not empty emailError}">
                    <label style="color: cornflowerblue"><fmt:message key="registration.page.email.error"/></label>
                    <br>
                </c:if>
                <label id="passwordLabel"
                       for="password"><fmt:message key="registration.page.password"/></label>
                <input type="password"
                       class="form__input"
                       id="password"
                        <c:if test="${not empty password}">
                               value="${password}"
                        </c:if>
                       name="password"
                       placeholder="<fmt:message key="registration.page.password.placeholder"/>"
                       required>
                <c:if test="${not empty passwordError}">
                    <label style="color: cornflowerblue"><fmt:message key="registration.page.password.error"/></label>
                    <br>
                </c:if>
                <button type="submit"
                        class="form__button">
                    <fmt:message key="registration.page.button"/>
                </button>
            </form>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/language.js"></script>

</body>
</html>
