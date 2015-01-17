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

<%@ include file="../include/navigation.jspf" %>

<div id="errorDiv" class="content scaffold-list" role="main">
    <h1><spring:message code="form.contracts.error.exception" /></h1>

    <div class="fieldcontain" style="margin-left: 30px">
        <spring:message code="form.contracts.error.cause">
            <spring:argument value="${message}" />
        </spring:message>
    </div>
</div>

</body>
</html>
