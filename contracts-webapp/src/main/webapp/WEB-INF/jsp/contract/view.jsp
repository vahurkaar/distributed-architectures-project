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
    <h1><spring:message code="form.contracts.contract.header" /></h1>

    <ul class="errors" role="status" style="display: none">
        <li id="errorArea"></li>
    </ul>

    <c:url var="viewCustomerUrl" value="/customer/view">
        <c:param name="id" value="${contractForm.customerId}" />
    </c:url>
    <a id="viewCustomerUrl" href="${viewCustomerUrl}" style="display: none"></a>

    <div id="contractFormArea">
        <c:url var="saveContractUrl" value="/contract/save" />
        <form:form id="saveContractForm" modelAttribute="contractForm" action="${saveContractUrl}">
            <fieldset class="form">
                <input type="hidden" name="customerId" value="${contractForm.customerId}" />
                <input type="hidden" name="id" value="${contractForm.id}" />

                <div class="fieldcontain">
                    <form:label path="contractNumber"><spring:message code="form.contracts.contract.contractNumber" /></form:label>
                    <custom-form:input path="contractNumber" writeAsLabel="${readOnly}" />
                </div>

                <div class="fieldcontain">
                    <form:label path="name"><spring:message code="form.contracts.contract.name" /></form:label>
                    <custom-form:input path="name" writeAsLabel="${readOnly}" />
                </div>

                <div class="fieldcontain">
                    <form:label path="description"><spring:message code="form.contracts.contract.description" /></form:label>
                    <custom-form:input path="description" writeAsLabel="${readOnly}" />
                </div>

                <div class="fieldcontain">
                    <form:label path="conditions"><spring:message code="form.contracts.contract.conditions" /></form:label>
                    <custom-form:input path="conditions" writeAsLabel="${readOnly}" />
                </div>

                <div class="fieldcontain">
                    <form:label path="valueAmount"><spring:message code="form.contracts.contract.valueAmount" /></form:label>
                    <custom-form:input path="valueAmount" writeAsLabel="${readOnly}" />
                </div>

                <div class="fieldcontain">
                    <form:label path="validFrom"><spring:message code="form.contracts.contract.validFrom" /></form:label>
                    <custom-form:input path="validFrom" writeAsLabel="${readOnly}" cssClass="datepicker"/>
                </div>

                <div class="fieldcontain">
                    <form:label path="validTo"><spring:message code="form.contracts.contract.validTo" /></form:label>
                    <custom-form:input path="validTo" writeAsLabel="${readOnly}" cssClass="datepicker"/>
                </div>

                <div class="fieldcontain">
                    <form:label path="contractStatusType"><spring:message code="form.contracts.contract.contractStatusType" /></form:label>
                    <c:choose>
                        <c:when test="${readOnly}">
                            <c:forEach items="${contractStatusTypeTypeList}" var="type">
                                <c:if test="${type.id eq contractForm.contractStatusType}">
                                    <c:out value="${type.name}" />
                                </c:if>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <form:select path="contractStatusType" writeAsLabel="${readOnly}">
                                <form:option value="" />
                                <form:options items="${contractStatusTypeTypeList}" itemValue="id" itemLabel="name" />
                            </form:select>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="fieldcontain">
                    <form:label path="contractType"><spring:message code="form.contracts.contract.contractType" /></form:label>
                    <c:choose>
                        <c:when test="${readOnly}">
                            <c:forEach items="${contractTypeTypeList}" var="type">
                                <c:if test="${type.id eq contractForm.contractType}">
                                    <c:out value="${type.name}" />
                                </c:if>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <form:select path="contractType" writeAsLabel="${readOnly}">
                                <form:option value="" />
                                <form:options items="${contractTypeTypeList}" itemValue="id" itemLabel="name" />
                            </form:select>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="fieldcontain">
                    <form:label path="note"><spring:message code="form.contracts.contract.note" /></form:label>
                    <custom-form:input path="note" writeAsLabel="${readOnly}" />
                </div>
            </fieldset>
            <fieldset class="buttons">
                <c:choose>
                    <c:when test="${not readOnly}">
                        <input id="saveContractButton" type="submit" class="save" value='<spring:message code="form.contracts.contract.buttons.save" />' />
                    </c:when>
                    <c:otherwise>
                        <c:url var="editContractLink" value="/contract/edit">
                            <c:param name="id" value="${contractForm.id}" />
                        </c:url>
                        <c:url var="deleteContractLink" value="/contract/delete">
                            <c:param name="id" value="${contractForm.id}" />
                            <c:param name="customerId" value="${contractForm.customerId}" />
                        </c:url>
                        <a class="edit" href="${editContractLink}">
                            <spring:message code="form.contracts.contract.buttons.edit" />
                        </a>
                        <c:if test="${contractForm.contractStatusType eq 5 or contractForm.contractStatusType eq 6}">
                            <a class="delete" href="${deleteContractLink}">
                                <spring:message code="form.contracts.contract.buttons.delete" />
                            </a>
                        </c:if>
                    </c:otherwise>
                </c:choose>
                <c:url var="customerLink" value="/customer/view">
                    <c:param name="id" value="${contractForm.customerId}" />
                </c:url>
                <a href="${customerLink}">
                    <spring:message code="form.contracts.contract.buttons.customer" />
                </a>
            </fieldset>

        </form:form>
    </div>

</div>

<script>
    $(document).ready(function() {
        $('#saveContractButton').click(function() {
            $.ajax({
                url: $('#saveContractForm').attr('action'),
                type: "POST",
                data: $('#saveContractForm').serializeArray(),
                success: function(data) {
                    if (data == 'OK') {
                        $('#contractFormArea').html('Success!');
                        setTimeout("location.href = $('#viewCustomerUrl').attr('href');", 1500);
                        $('.errors').hide();
                    } else {
                        $('.errors').show();
                        $('#errorArea').html(data);
                    }
                }
            });

            return false;
        });
    })
</script>

</body>

</html>