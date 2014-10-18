<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'customer.label', default: 'Customer')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-customer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li style="float: right">
                    <form name="logoutForm" method="POST" action="${createLink(controller: 'logout')}">
                        <a href="javascript:document.logoutForm.submit()">Logout</a>
                    </form>
                </li>
                <li style="float: right">
                    <span><a>Welcome, ${user.username}</a></span>
                </li>
			</ul>
		</div>
		<div id="create-customer" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:form name="customerForm" url="[action: 'save']">
				<fieldset class="form">
					<g:render template="form" model="[formType: 'create']"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
