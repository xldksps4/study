<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>

</head>



<body>
	
	<c:choose>
		<c:when test="${empty login }">
			<%@ include file="WEB-INF/views/all/header_login.jsp" %>
		</c:when>
		<c:otherwise>
			<%@ include file="WEB-INF/views/all/header_logout.jsp" %>
			
		</c:otherwise>
	</c:choose>
	
	<div class="container" style="background-color: gray;">
		<div id="div_main_top">
			<h1 class="title animated  bounce delay-1s duration-3s">싱글 프로젝트</h1>
		</div>
		
		<div class="body">
			
		</div>
	</div>
	
</body>
</html>