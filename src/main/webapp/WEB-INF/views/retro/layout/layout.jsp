<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
											"http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<spring:theme var="cssResource" code="styleSheet" />
		<c:url value="${cssResource}" var="cssUrl" /> 
		<link rel="stylesheet" type="text/css" href="${cssUrl}"/>
		
		<title><spring:message code="welcome"/></title>
	</head>
	
	<body>
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		
		<div id="body">
			<tiles:insertAttribute name="body" />
		</div>
		
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</body>
	
</html>
