<%@ page import="ee.ttu.Customer" %>
<g:hiddenField name="customerId" value="${customer?.customerId}" />

<div class="fieldcontain ${hasErrors(bean: customer, field: 'identityCode', 'error')} required">
    <label for="identityCode">
        <g:message code="customer.identityCode.label" default="Identity Code" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField readonly="${customer?.identityCode}" name="identityCode" required="" value="${customer?.identityCode}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: customer, field: 'firstname', 'error')} required">
    <label for="firstname">
        <g:message code="customer.firstname.label" default="Firstname" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="firstname" required="" value="${customer?.firstname}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: customer, field: 'lastname', 'error')} required">
    <label for="lastname">
        <g:message code="customer.lastname.label" default="Lastname" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="lastname" required="" value="${customer?.lastname}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: customer, field: 'birthDate', 'error')} required">
	<label for="birthDate">
		<g:message code="customer.birthDate.label" default="Birth Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker default="none" noSelection="['':'']" name="birthDate" precision="day"  value="${customer?.birthDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: customer, field: 'customerStatusType', 'error')}">
	<label for="customerStatusType">
		<g:message code="customer.customerStatusType.label" default="Customer Status Type" />
	</label>
	<g:select id="customerStatusType" noSelection="['':'']" name="customerStatusType.customerStatusTypeId" from="${customerStatusTypes}" optionKey="customerStatusTypeId" value="${customer?.customerStatusType?.customerStatusTypeId}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: customer, field: 'customerType', 'error')}">
	<label for="customerType">
		<g:message code="customer.customerType.label" default="Customer Type" />
	</label>
	<g:select id="customerType" noSelection="['':'']" name="customerType.customerTypeId" from="${customerTypes}" optionKey="customerTypeId" value="${customer?.customerType?.customerTypeId}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: customer, field: 'note', 'error')}">
	<label for="note">
		<g:message code="customer.note.label" default="Note" />
	</label>
	<g:textArea name="note" value="${customer?.note}"/>

</div>


