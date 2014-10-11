<div id="edit-customer" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="['Customer']" /></h1>
    <g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:formRemote name="customerForm" url="[action: 'update']" method="POST" update="customerForm">
        <fieldset class="form">
            <g:render template="form"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitToRemote class="save" update="edit-customer" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" onSuccess="performSearch()" />
            <g:submitToRemote class="delete" update="edit-customer" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onSuccess="performSearch()" />
        </fieldset>
    </g:formRemote>
</div>
