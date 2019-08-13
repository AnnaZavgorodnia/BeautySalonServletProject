<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>

<header class="header">
    <div class="container">
        <div class="header__inner">
            <div class="header__logo"><fmt:message key="header.logo" /></div>
            <nav class="header__nav">
                <ul>
                    <li class="${module eq 'index' ? 'nav__link active': 'nav__link'}">
                        <a class="nav__sublink"
                           href="${pageContext.request.contextPath}/"><fmt:message key="header.menu.home"/></a>
                    </li>
                    <c:if test="${not empty loginedUser && loginedUser.role == 'CLIENT'}">
                        <li class="${module eq 'masters' ? 'nav__link active': 'nav__link'}">
                            <a class="nav__sublink"
                               href="${pageContext.request.contextPath}/app/masters"><fmt:message key="header.menu.masters"/></a>
                        </li>
                    </c:if>
                    <c:if test="${not empty loginedUser && (loginedUser.role == 'ADMIN' || loginedUser.role == 'MASTER')}">
                        <li class="${module eq 'all_appointments' ? 'nav__link active': 'nav__link'}">
                            <a class="nav__sublink"
                               href="${pageContext.request.contextPath}/app/all_appointments"><fmt:message key="header.menu.all_appoinments"/></a>
                        </li>
                    </c:if>
                    <c:if test="${not empty loginedUser && loginedUser.role == 'ADMIN'}">
                        <li class="${module eq 'add_master' ? 'nav__link active': 'nav__link'}">
                            <a class="nav__sublink"
                               href="${pageContext.request.contextPath}/app/add_master"><fmt:message key="header.menu.create_master"/></a>
                        </li>
                    </c:if>
                    <c:if test="${not empty loginedUser && loginedUser.role == 'ADMIN'}">
                        <li class="${module eq 'all_masters' ? 'nav__link active': 'nav__link'}">
                            <a class="nav__sublink"
                               href="${pageContext.request.contextPath}/app/all_masters"><fmt:message key="header.menu.admin.all.masters"/></a>
                        </li>
                    </c:if>
                    <c:if test="${empty loginedUser}">
                        <li class="${module eq 'login' ? 'nav__link active': 'nav__link'}">
                            <a class="nav__sublink"
                               href="${pageContext.request.contextPath}/app/login"><fmt:message key="header.menu.login"/></a>
                        </li>
                    </c:if>
                    <c:if test="${empty loginedUser}">
                        <li class="${module eq 'registration' ? 'nav__link active': 'nav__link'}">
                            <a class="nav__sublink"
                               href="${pageContext.request.contextPath}/app/registration"><fmt:message key="header.menu.register"/></a>
                        </li>
                    </c:if>
                    <c:if test="${not empty loginedUser}">
                        <li class="${module eq 'my_appointments' ? 'nav__link active': 'nav__link'}">
                            <span class="nav__sublink"><fmt:message key="header.menu.account"/></span>
                            <ul class="nav__drop__menu">
                                <c:if test="${not empty loginedUser && loginedUser.role == 'CLIENT'}">
                                    <li>
                                        <a class="nav__menu__content"
                                           href="${pageContext.request.contextPath}/app/me/appointments"><fmt:message key="header.menu.appointments"/></a>
                                    </li>
                                </c:if>
                                <li>
                                    <a class="nav__menu__content"
                                       href="${pageContext.request.contextPath}/app/logout">
                                        <fmt:message key="header.menu.logout"/>
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </c:if>
                    <li class="nav__link">
                                <span class="nav__sublink"><fmt:message key="header.menu.lang"/></span>
                        <ul class="nav__drop__menu">
                            <li>
                                <span id="lang_ua" class="nav__menu__content"
                                   ><fmt:message key="header.menu.language.ua"/></span>
                            </li>
                            <li>
                                <span id="lang_en" class="nav__menu__content"
                                   ><fmt:message key="header.menu.language.en"/></span>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>