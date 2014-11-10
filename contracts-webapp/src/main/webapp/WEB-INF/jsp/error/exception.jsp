<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
            <a href="<spring:message code='navigation.customers.url' />">
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
            <span><a>Welcome, <sec:authentication property="principal.username" /></a></span>
        </li>
    </ul>
</div>

<div id="errorDiv" class="content scaffold-list" role="main">
    <h1><spring:message code="form.contracts.error.webServiceException" /></h1>

    <div class="fieldcontain" style="margin-left: 30px">
        <spring:message code="form.contracts.error.cause">
            <spring:argument value="${message}" />
        </spring:message>
    </div>
</div>

</body>
</html>
