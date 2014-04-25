<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div>
	<a href="index.html"><img class="logo" src="${pageContext.request.contextPath}/resources/retro/images/logo.png" width="513" height="84" alt="" title=""></a>
	<a href="index.html"><img  src="${pageContext.request.contextPath}/resources/retro/images/waitress.png" width="332" height="205" alt="" title=""></a>
	<ul class="navigation">
		<li><a class="active" href="index.html"><spring:message code="home"/></a></li>
		<li><a href="burger.html"><spring:message code="menu"/></a></li>
		<li><a href="contact.html"><spring:message code="contact"/></a></li>
	</ul>
	<span style="float: right">
		<a href="?lang=en">en</a> 
		| 
		<a href="?lang=vi">vi</a>
	</span>
</div>