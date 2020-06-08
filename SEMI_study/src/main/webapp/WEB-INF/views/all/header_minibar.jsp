<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!-- include bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- css -->
<%-- <link	href="${pageContext.request.contextPath}/resource/css/all/header.css"	rel="stylesheet" type="text/css"> --%>

<!-- login package -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/all/header_logout.js"></script>

<!-- img preview -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/file/preview.js"></script>

<!-- google -->
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
	content="879634557485-v55qv49tpffgt0ujgmeq6glvtpa8lfmc.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async
	defer></script>
<!-- kakao -->
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>


<body>
	<div class="container" style="background-color: yellow;">
		<div class="row">
			<div id="menu1"></div>
		</div>
		<div class="row">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<div id="logo">
					<img class="center-block" alt="logo가 없습니다."
						src="${pageContext.request.contextPath}/resources/img/all/logo.png"
						onclick="location.href='MAIN_main.do'">
				</div>
			</div>
			<div class="col-lg-4">
				<div id="menu2">
					<span>|</span> <span><a href="/semi/MAIN_plusinfo.do">마이페이지</a></span> <span>|</span>
					<span><a href="/semi/SDUSER_logout.do">로그아웃</a></span>
				</div>
			</div>
		</div>


	</div>



</body>
</html>