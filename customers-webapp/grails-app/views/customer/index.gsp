<%@ page import="ee.ttu.Customer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'customer.label', default: 'Customer')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-customer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><a href="${message(code: 'contract.webapp.url')}"><g:message code="default.contracts.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
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
		<div id="list-customer" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>

            <g:formRemote name="searchCustomers" url="[controller: 'customer', action: 'search']" method="GET" update="customerList">
                <div class="fieldcontain">
                    <label for="query-name"><g:message code="default.list.query-name" default="Customer name"/></label>
                    <g:textField name="query-name" value="${params.queryName}"/>

                    <label for="query-identityCode"><g:message code="default.list.query-identityCode" default="Identity code"/></label>
                    <g:textField name="query-identityCode" value="${params.queryIdentityCode}"/>

                    <g:submitButton name="${message(code: 'default.button.search.label', default: 'Search')}" id="customerSearchButton" />
                </div>
            </g:formRemote>

            <div id="customerList"></div>
            <div id="customerForm"></div>

		</div>
	</body>
</html>
