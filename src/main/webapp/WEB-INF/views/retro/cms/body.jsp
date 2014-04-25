<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="featured"> <!-- <a href="burger.html"><img src="${pageContext.request.contextPath}/resources/retro/images/burger-specials.png" width="960" height="590" alt=""></a> -->
	<h2><spring:message code="welcome"/></h2>
	<p><spring:message code="welcome.text"/></p>
	<ul>
		<li>
			<a href="hotdog.html"><img src="${pageContext.request.contextPath}/resources/retro/images/hotdogs.jpg" width="284" height="214" alt=""></a>
		</li>
		<li>
			<a href="shake.html"><img src="${pageContext.request.contextPath}/resources/retro/images/shakes.jpg" alt="" width="284" height="214"></a>
		</li>
		<li>
			<a href="breakfast.html"><img src="${pageContext.request.contextPath}/resources/retro/images/breakfast.jpg" alt="" width="284" height="214"></a>
		</li>
	</ul>
</div>

<div>
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
	 
	<h2>Customer list:</h2>
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
</div>