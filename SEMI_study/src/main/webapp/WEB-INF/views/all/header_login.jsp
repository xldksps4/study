<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- include bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- css -->
<%-- <link href="${pageContext.request.contextPath}/resources/css/all/header.css" rel="stylesheet" type="text/css"> --%>


<!-- login package -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/all/header_login.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/all/login.js"></script>

<!-- google -->
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
	content="879634557485-v55qv49tpffgt0ujgmeq6glvtpa8lfmc.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async
	defer></script>


<!-- kakao -->
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>


<title>헤더, 로그인 가능</title>

</head>
<body>

	<!-- 로그인 시 -->
	<div class="container">
		<!-- id 쓴다면 : id="header_wrap"  -->
		<div class="row">
			<div id="menu1"></div>
		</div>
		<div class="row">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<div id="logo">
					<img class="center-block" alt="logo"
						src="${pageContext.request.contextPath}/resources/img/all/logo.png"
						onclick="location.href='MAIN_main.do'">
				</div>
			</div>
			<div class="col-lg-4">
				<div id="menu2">
					<span>|</span> <span><a href="/semi/SDUSER_registform.do">회원가입</a></span>
					<span>|</span> <span><a href="/semi/SDUSER_login.do">로그인</a></span>
				</div>
			</div>
		</div>


		<div class="row">
			<!-- 메뉴바 -->
			<div class="col-lg-4">
				<div class="nav">
					<div id="menu">
					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="nav">
					<div id="menu">
						<p>게시판</p>
						<span><a href="/semi/BOARD_goboardlist.do">일반 게시판</a></span><br/>
						<span><a href="https://velog.io/@xldksps4">velog</a></span><br/>
					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="nav">
					<div>
						<p>기타</p>
						<span>소개</span><br/>
					</div>
				</div>
			</div>

			<div class="row">
				<img class="center-block" alt="배너 사진이 없습니다."
					src="${pageContext.request.contextPath}/resources/img/all/banner_1.jpg">
			</div>
		</div>
	</div>
</body>
</html>