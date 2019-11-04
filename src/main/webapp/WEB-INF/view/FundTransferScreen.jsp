<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Fund Transfer </h1>
<c:if test="${not empty msg}">
    <h4 style="color: red">${msg}</h4>
</c:if>
<form:form method="post" action="fund-transfer">
    <form:errors/>
    <table>
        <tr>
            <td>Destination Account :</td>
            <td>
                <input name="accountDestination"
                       type="text"
                       required
                       onkeypress='return event.charCode >= 48 && event.charCode <= 57'
                       maxlength="6"/>
            </td>
        </tr>
        <tr>
            <td>Transfer Amount :</td>
            <td>
                <input name="amount"
                       required
                       onkeypress='return event.charCode >= 48 && event.charCode <= 57'
                       maxlength="6"/>
            </td>
        </tr>
        <tr>
            <td>Ref :</td>
            <td>
                <input name="ref"
                       required
                       onkeypress='return event.charCode >= 48 && event.charCode <= 57'
                       maxlength="6"/>
            </td>
        </tr>
        <tr>
            <td><input type="submit" name="action" value="Confirm Trx"/></td>
            <td><input type="button" onclick="window.location.replace('account-screen');" value="Cancel Trx"/></td>
        </tr>
    </table>
</form:form>

<br/>