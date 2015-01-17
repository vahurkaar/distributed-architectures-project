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

<%@ include file="include/navigation.jspf" %>

<div id="contractsSearchForm" class="content scaffold-list" role="main">
    <h1><spring:message code="form.contracts.search.header" /></h1>

    <c:url var="contractsSearchUrl" value="/searchContracts" />
    <form:form modelAttribute="contractsSearchForm" method="POST" action="${contractsSearchUrl}">

        <div class="fieldcontain">
            <form:label path="customerName"><spring:message code="form.contracts.search.customerName" /></form:label>
            <form:input path="customerName" />
        </div>

        <div class="fieldcontain">
            <form:label path="contractName"><spring:message code="form.contracts.search.conractName" /></form:label>
            <form:input path="contractName" />
        </div>

        <div class="fieldcontain">
            <form:label path="contractNumber"><spring:message code="form.contracts.search.contractNumber" /></form:label>
            <form:input path="contractNumber" />
        </div>

        <div class="fieldcontain">
            <button type="submit"><spring:message code="form.contracts.search.submit" /></button>
        </div>
    </form:form>

    <c:choose>
        <c:when test="${customers eq null}"></c:when>
        <c:when test="${not empty customers}">
            <table id="customerResults">
                <thead>
                    <tr>
                        <th><spring:message code="form.contracts.search.results.customerName" /></th>
                        <th><spring:message code="form.contracts.search.results.customerCode" /></th>
                        <th><spring:message code="form.contracts.search.results.customerStatus" /></th>
                        <th><spring:message code="form.contracts.search.results.numberOfContracts" /></th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${customers}" var="customer">
                    <c:url var="customerUrl" value="/customer/view">
                        <c:param name="id" value="${customer.id}" />
                    </c:url>
                    <tr>
                        <td>
                            <c:set var="customerFullname" value="${customer.firstname} ${customer.lastname}" />
                            <a href="${customerUrl}">
                                <c:out value="${fn:trim(customerFullname)}" />
                            </a>
                        </td>
                        <td>
                            <a href="${customerUrl}">
                                <c:out value="${customer.identityCode}" />
                            </a>
                        </td>
                        <td>
                            <c:forEach items="${customerTypes}" var="customerType">
                                <c:if test="${customerType.id eq customer.customerType}">
                                    <c:out value="${customerType.name}" />
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <c:out value="${fn:length(customer.contracts.contract)}" />
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <spring:message code="form.contracts.search.noResults" />
        </c:otherwise>
    </c:choose>

</div>

</body>
</html>
