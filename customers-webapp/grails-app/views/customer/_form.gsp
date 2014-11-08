<%@ page import="ee.ttu.Customer" %>

<g:hasErrors bean="${customer}">
    <ul class="errors" role="alert">
        <g:eachError bean="${customer}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
        </g:eachError>
    </ul>
</g:hasErrors>

<g:hiddenField name="customerId" value="${customer?.customerId}" />

<div class="fieldcontain ${hasErrors(bean: customer, field: 'identityCode', 'error')} required">
    <label for="identityCode">
        <g:message code="customer.identityCode.label" default="Identity Code" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField readonly="${customer?.identityCode != null && !formType.equals('create')}" name="identityCode" required="" value="${customer?.identityCode}"/>

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
	<g:datePicker name="birthDate" precision="day" years="${1900..2100}" value="${customer?.birthDate}"  />

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
	<g:select id="customerType" name="customerType.customerTypeId" from="${customerTypes}" optionKey="customerTypeId" value="${customer?.customerType?.customerTypeId}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: customer, field: 'note', 'error')}">
	<label for="note">
		<g:message code="customer.note.label" default="Note" />
	</label>
	<g:textArea name="note" value="${customer?.note}"/>

</div>


<div id="customerAddressesSubForm">
    <h4><g:message code="customer.addresses.header" default="Addresses" /></h4>
    <table>
        <thead>
        <tr>
            <th style="width: 10px;">&nbsp;</th>
            <th>Aadress</th>
            <th style="width: 10px;"><a><img src="${assetPath(src: 'skin/database_add.png')}" /></a></th>
        </tr>
        </thead>
        <tbody style="background: #eaeaea">
        <tr>
            <td><a><img src="${assetPath(src: 'skin/database_edit.png')}" /></a></td>
            <td>Jejejeee</td>
            <td><a><img src="${assetPath(src: 'skin/database_delete.png')}" /></a></td>
        </tr>
        </tbody>
    </table>
</div>

<div id="addressFormModal" style="display: none">
    <h4>Aadress</h4>
</div>