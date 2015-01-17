<div id="show-customer" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="['Customer']" /></h1>
    <g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list customer">

        <g:if test="${customer?.birthDate}">
        <li class="fieldcontain">
            <span id="birthDate-label" class="property-label"><g:message code="customer.birthDate.label" default="Birth Date" /></span>

                <span class="property-value" aria-labelledby="birthDate-label"><g:formatDate date="${customer?.birthDate}" /></span>

        </li>
        </g:if>

        <g:if test="${customer?.customerStatusType}">
        <li class="fieldcontain">
            <span id="customerStatusType-label" class="property-label"><g:message code="customer.customerStatusType.label" default="Customer Status Type" /></span>

                <span class="property-value" aria-labelledby="customerStatusType-label"><g:link controller="customerStatusType" action="show" id="${customer?.customerStatusType?.customerStatusTypeId}">${customer?.customerStatusType?.encodeAsHTML()}</g:link></span>

        </li>
        </g:if>

        <g:if test="${customer?.customerType}">
        <li class="fieldcontain">
            <span id="customerType-label" class="property-label"><g:message code="customer.customerType.label" default="Customer Type" /></span>

                <span class="property-value" aria-labelledby="customerType-label"><g:link controller="customerType" action="show" id="${customer?.customerType?.id}">${customer?.customerType?.encodeAsHTML()}</g:link></span>

        </li>
        </g:if>

        <g:if test="${customer?.firstname}">
        <li class="fieldcontain">
            <span id="firstname-label" class="property-label"><g:message code="customer.firstname.label" default="Firstname" /></span>

                <span class="property-value" aria-labelledby="firstname-label"><g:fieldValue bean="${customer}" field="firstname"/></span>

        </li>
        </g:if>

        <g:if test="${customer?.identityCode}">
        <li class="fieldcontain">
            <span id="identityCode-label" class="property-label"><g:message code="customer.identityCode.label" default="Identity Code" /></span>

                <span class="property-value" aria-labelledby="identityCode-label"><g:fieldValue bean="${customer}" field="identityCode"/></span>

        </li>
        </g:if>

        <g:if test="${customer?.lastname}">
        <li class="fieldcontain">
            <span id="lastname-label" class="property-label"><g:message code="customer.lastname.label" default="Lastname" /></span>

                <span class="property-value" aria-labelledby="lastname-label"><g:fieldValue bean="${customer}" field="lastname"/></span>

        </li>
        </g:if>

        <g:if test="${customer?.modifier}">
        <li class="fieldcontain">
            <span id="modifier-label" class="property-label"><g:message code="customer.modifier.label" default="Modifier" /></span>

                <span class="property-value" aria-labelledby="modifier-label"><g:fieldValue bean="${customer}" field="modifier"/></span>

        </li>
        </g:if>

        <g:if test="${customer?.note}">
        <li class="fieldcontain">
            <span id="note-label" class="property-label"><g:message code="customer.note.label" default="Note" /></span>

                <span class="property-value" aria-labelledby="note-label"><g:fieldValue bean="${customer}" field="note"/></span>

        </li>
        </g:if>

    </ol>
    <g:form url="[resource:customer, action:'delete']" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${customer}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
            <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
        </fieldset>
    </g:form>
</div>