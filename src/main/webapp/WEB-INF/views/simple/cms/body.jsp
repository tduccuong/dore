<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>DORE - Deeo Online Restaurant Engine</title>
</head>

<body>

<form:form method="post" action="cms/customer/add.html" commandName="newCustomer">
	<table>
		<tr>
	    <td><form:label path="name"><spring:message code="newCustomer.label.caption"/></form:label></td>
	    <td><form:input path="name" /></td>
  	</tr>
	  <tr>
	    <td colspan="2">
	       <input type="submit" value="<spring:message code="newCustomer.button.caption"/>"/>
	    </td>
	  </tr>
	</table> 
</form:form>
 
<h3>Customer list:</h3>
<c:if  test="${!empty customerList}">
<table class="data">
<tr>
    <th>Name</th>
    <th>&nbsp;</th>
</tr>
<c:forEach items="${customerList}" var="customer">
    <tr>
        <td>${customer.name} </td>
        <td><a href="cms/customer/del/${customer.id}">delete</a></td>
    </tr>
</c:forEach>
</table>
</c:if>
 
</body>
</html>