<table>
<g:each in="${customers}" status="i" var="customerInstance">
    <tbody>
    <tr>
        <td>
            <g:remoteLink update="customerForm" action="edit" params="[customerId: customerInstance.customerId]">
                ${fieldValue(bean: customerInstance, field: "fullName")}
            </g:remoteLink>
        </td>
    </tr>
    </tbody>
</g:each>
</table>