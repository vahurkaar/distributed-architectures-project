<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<div class="nav" role="navigation">
    <ul>
        <li>
            <c:url value="/" var="homeUrl" />
            <a class="home" href="${homeUrl}">
                <spring:message code="navigation.home" />
            </a>
        </li>
        <li>
            <a class="home" href="<spring:message code='navigation.customers.url' />">
                <spring:message code="navigation.customers" />
            </a>
        </li>
        <li style="float: right">
            <c:url value="/logout" var="logoutUrl" />
            <form name="logoutForm" method="POST" action="${logoutUrl}">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <a href="javascript:document.logoutForm.submit()"><spring:message code="navigation.logout" /></a>
            </form>
        </li>
        <li style="float: right">
            <span><a>Welcome, ${user.username}</a></span>
        </li>
    </ul>
</div>
<h1>Hello world!</h1>
</body>
</html>
