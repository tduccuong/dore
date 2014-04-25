<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div>
	<ul>
		<li class="first">
			<h2><spring:message code="footer.hotline.caption"/></h2>
			<h3><spring:message code="footer.hotline.number"/></h3>
			<ul>
				<li>
					<a href="http://www.freewebsitetemplates.com/go/facebook" class="facebook"></a>
				</li>
				<li>
					<a href="http://www.freewebsitetemplates.com/go/twitter" class="twitter"></a>
				</li>
				<li>
					<a href="http://www.freewebsitetemplates.com/go/googleplus" class="googleplus"></a>
				</li>
			</ul>
		</li>
		<li>
			<a href="index.html"><img class="logo" src="${pageContext.request.contextPath}/resources/retro/images/logo-footer.png" alt=""></a>
			<ul class="navigation">
				<li>
					<a href="index.html"><spring:message code="home"/></a>
				</li>
				<li>
					<a href="menu.html"><spring:message code="menu"/></a>
				</li>
				<li>
					<a href="contact.html"><spring:message code="contact"/></a>
				</li>
			</ul>
			<span><spring:message code="copyright"/></span>
		</li>
		<li class="last">
			<h2><spring:message code="footer.follow.caption"/></h2>
			<form action="login.html">
				<input type="text" name="subscribe" value="<spring:message code="footer.follow.email"/>">
				<input type="submit" value="">
			</form>
		</li>
	</ul>
</div>