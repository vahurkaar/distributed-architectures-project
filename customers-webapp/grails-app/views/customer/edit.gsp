<div id="edit-customer" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="['Customer']" /></h1>
    <g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${customer}">
    <ul class="errors" role="alert">
        <g:eachError bean="${customer}" var="error">
        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
        </g:eachError>
    </ul>
    </g:hasErrors>
    <g:formRemote name="customerForm" url="[action: 'update']" method="POST" update="customerForm">
        <fieldset class="form">
            <g:render template="form"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitToRemote class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
            <g:submitToRemote class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onSuccess="" />
        </fieldset>
    </g:formRemote>
</div>
