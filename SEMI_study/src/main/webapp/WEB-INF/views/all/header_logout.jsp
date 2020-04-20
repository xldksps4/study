<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- include bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/CSS/ALL/header_login.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/JS/ALL/header_login.js"></script>
<!-- google -->
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id" content="879634557485-v55qv49tpffgt0ujgmeq6glvtpa8lfmc.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
<!-- kakao -->
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

<title>Insert title here</title>
</head>
<body>
   <div id="headerwap">
         <div id="logo"><img alt="logo" src="${pageContext.request.contextPath}/resources/IMG/logo.png" onclick="location.href='MAIN_main.do'"></div>
            <div id="menu1">
               <span><a href="">공채캘린더</a></span> 
               <span><a href="JOB_jobSearch.do">채용검색</a> </span>
            </div>
            <div id="menu2">
               <span >|</span>
               <span><a href="USER_userMain.do">마이페이지</a></span>
               <span><a href="JOB_jobCenter.do">어디로?</a></span>
               <span><a href="BOARD_boardList.do">자유게시판</a></span>
               <span >|</span>
               <span><a onclick="logoutGo();">로그아웃</a></span>
            </div>    
   </div>

    
</body>
</html>