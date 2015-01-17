<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="custom-form" uri="http://www.ttu.ee/jsp/jstl/custom-form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Customer view</title>
</head>
<body>

<%@ include file="../include/navigation.jspf" %>

<div id="contractView" class="content scaffold-list" role="main">
    <h1><spring:message code="form.contracts.customer.header" /></h1>
    <c:if test="${suggestStatusUpgrade}">
        <div id="upgradeCustomerDiv" style="background: pink; margin: 10px; padding: 10px;">
            <p><spring:message code="form.contracts.customer.special" /></p>
            <p><spring:message code="form.contracts.customer.upgradeQuestion" /></p>
            <button id="upgradeCustomerButotn">
                <spring:message code="form.contracts.customer.upgrade" />
            </button>
        </div>

        <c:url var="upgradeUrl" value="/customer/upgrade" />
        <form id="upgradeCustomerForm" action="${upgradeUrl}" method="POST" style="display: none">
            <input type="hidden" name="id" value="${customerForm.id}" />
            <input type="submit" id="upgradeCustomerButton" />
        </form>
    </c:if>

    <form:form modelAttribute="customerForm">
        <div class="fieldcontain">
            <form:label path="fullname"><spring:message code="form.contracts.customer.fullname" /></form:label>
            <custom-form:input path="fullname" writeAsLabel="true" />
        </div>

        <div class="fieldcontain">
            <form:label path="identityCode"><spring:message code="form.contracts.customer.identityCode" /></form:label>
            <custom-form:input path="identityCode" writeAsLabel="true" />
        </div>

        <div class="fieldcontain">
            <form:label path="customerStatusTypeName"><spring:message code="form.contracts.customer.customerStatusTypeName" /></form:label>
            <custom-form:input path="customerStatusTypeName" writeAsLabel="true" />
        </div>

        <div class="fieldcontain">
            <form:label path="customerTypeName"><spring:message code="form.contracts.customer.customerTypeName" /></form:label>
            <custom-form:input path="customerTypeName" writeAsLabel="true" />
        </div>

        <div class="fieldcontain">
            <form:label path="note"><spring:message code="form.contracts.customer.note" /></form:label>
            <custom-form:input path="note" writeAsLabel="true" />
        </div>

        <div id="contractsSubForm" style="margin: 20px;" class="fieldcontain">
            <table>
                <tr>
                    <td>
                        <h4 style="margin-bottom: 10px;"><spring:message code="form.contracts.customer.contracts.header" /></h4>
                    </td>
                    <td>
                        <c:url var="newContractUrl" value="/contract/new">
                            <c:param name="customerId" value="${customerForm.id}" />
                        </c:url>
                        <a href="${newContractUrl}">
                            <spring:message code="form.contracts.contract.buttons.add" />
                        </a>
                    </td>
                </tr>
            </table>
            <c:choose>
                <c:when test="${not empty customerForm.contracts.contract}">
                    <table>
                        <thead>
                        <tr>
                            <th>
                                <spring:message code="form.contracts.customer.contracts.contractNumber" />
                            </th>
                            <th>
                                <spring:message code="form.contracts.customer.contracts.contractName" />
                            </th>
                            <th>
                                <spring:message code="form.contracts.customer.contracts.contractAmount" />
                            </th>
                        </tr>
                        </thead>
                        <tbody style="background: #eaeaea">
                        <c:forEach items="${customerForm.contracts.contract}" var="contract">
                            <tr>
                                <td>
                                    <c:url var="contractViewUrl" value="/contract/view">
                                        <c:param name="id" value="${contract.id}" />
                                    </c:url>
                                    <a href="${contractViewUrl}">
                                        <c:out value="${contract.contractNumber}" />
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${contract.name}" />
                                </td>
                                <td>
                                    <c:out value="${contract.valueAmount}" />
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <spring:message code="form.contracts.customer.noContracts" />
                </c:otherwise>
            </c:choose>
        </div>
    </form:form>

</div>

<script>
    $(document).ready(function() {
        $('#upgradeCustomerButotn').click(function() {
            $.ajax({
                url: $('#upgradeCustomerForm').attr('action'),
                type: "POST",
                data: $('#upgradeCustomerForm').serializeArray()
            }).done(function(data) {
                $('#upgradeCustomerDiv').html('Success!');
                setTimeout("location.reload();", 1500);
            });

            return false;
        });
    })
</script>

</body>

</html>
