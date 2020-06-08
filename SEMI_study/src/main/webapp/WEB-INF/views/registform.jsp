<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 한글이 깨지는 이유
   * 브라우저 -> 서버 -> DBMS 과정에서 매번 다른 문자코드를 사용하여 재표현되기 때문. 왜?
   * GET과 POST에 따라 처리 방식이 다름.
 -->
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html;charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<!-- include bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- regist.js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/regist.js"></script>

<meta charset="UTF-8">
<title>회원가입</title>


<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<script type="text/javascript">

//텍스트 롤링 jquery
$(function() {
    var count = $('#rank li').length;
    var height = $('#rank li').height();

    function step(index) {
        $('#rank ol').delay(2000).animate({
            top: -height * index,
        }, 500, function() {
            step((index + 1) % count);
        });
    }

    step(1);
});
	
</script>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);

* {
	font-family: 'Jeju Gothic', cursive;
}

div {
	width: 50%;
	height: auto;
	margin: 50px 25%;
	align-self: center;
}

.button:hover {
	cursor: pointer;
}
</style>
</head>
<body>

	<%-- 	<%@ include file="./form/mainPage.jsp"%> --%>

	<%
		String useremail = request.getParameter("useremail");
	%>

	<div>
		<div></div>
		<div></div>
		<div></div>
	</div>
	<div id="wrap_register_form">
		<div id="wrap_register_header"></div>
		<div></div>
		<div>
			<h1>회원가입</h1>
			<br /> <br />
		</div>
		<div id="wrap_regiter_joinform" class="container">

			<!-- 회원가입 form -->
			<form action="SDUSER_userRegist.do" method="post" name="regist"
				onsubmit="return validate()"> <!-- 만약 모든 값이 true일때 return해주세요 -->

				<!-- 아이디&이메일 -->

				<div class="row">
					<div class="col-lg-12">	
					<p>아이디</p>
					<input type="email" id="join_id" class="input_text" name="sduemail"
						placeholder="ID는 이메일 형식입니다." onkeyup="javascript:idCheck()" /> 
					<input id="email_check"
						type="button" value="이메일 인증" onclick="verifyEmailConfirm()" /> 
					<br/>
					<input type="text" value="" id="verify_num" placeholder="인증번호 입력">
					<input type="button" value="확인" onclick="emailChk();" id="verify_check">
					<br/>
					<span class="check_res" style="display: none"></span>	
					<!-- 		<input type="button" onclick="emailsend()"> -->
					</div>
				</div>
				<br />


				<!-- 비밀번호 -->
				<div class="row">
					<div class="col-lg-12">	
					<p>비밀번호</p>
					
					<input type="password" id="join_password" class="input_text"
						name="sdupw" placeholder="비밀번호를 입력하세요" required="required"
						maxlength="19" onkeyup="javascript:pwCheck()" /> <span
						id="pw_same1" class="check_res" style="display: none"></span> <br/>

					<p>비밀번호 확인</p>
					<input type="password" id="join_password_re" class="input_text"
						name="user_pw_re" placeholder="비밀번호를 다시 입력하세요" required="required"
						maxlength="19" onkeyup="pwCheckRes()" /> <span id="pw_same2"
						class="check_res" style="display: none"></span>
					</div>
				</div>
				<br />


				<!-- 이름 -->
				<div class="row">
					<div class="col-lg-12">
					<p>이름</p>
					<input type="text" id="join_name" name="sduname"
						required="required" maxlength="8" />
					</div>	
				</div>
				<br />

				<div class="row">
					<div class="col-lg-12">
					<p>생년월일</p>
					<input type="text" id="sdudob" name="sdudob" maxlength="8" 
					placeholder="YYYYMMDD" onkeyup="javascript:dobCheck()"/><br/>
					<!-- 생년월일 유효성 출력 -->
					<span class="check_res" style="display: none"></span>
					</div>
				</div>
				<br/>

				<!-- 성별 작성 안내 -->
				<div id="content" class="row">
				<div id="rank" class="col-lg-12">
					<p>성별 작성 시 다음과 같은 이니셜을 작성해주세요.</p>
			        <ul>
<!-- 			        <ol> -->
			            <li><a href="#">남성은 M</a></li>
			            <li><a href="#">여성은 W</a></li>
			            <li><a href="#">양성은 Y</a></li>
			            <li><a href="#">무성은 N</a></li>
<!-- 			        </ol> -->
			        </ul>
			    </div>
				</div>

				<!-- 성별 -->
				<div class="row">
					<div class="col-lg-12">
					<p>성별</p>
			            <input type="text" id="sdusex" name="sdusex" onkeyup="genderCheck()" placeholder="유효한 이니셜을 입력하세요"/>
			            <span class="check_res" style="display: none"></span>
					</div>
				</div>
				<br />				


				<div>
					<br /> <br /> <br /> <br />
				</div>

				
				<div class="row">
					<div class="col-lg-12">
					<input type="checkbox" name="check" required="required"
						onclick="accept(this);" /> <span>이용약관 및 개인정보 취급방침에
						동의하시겠습니까?</span>
					</div>
				</div>
				
				
				<div class="row">
				<div class="col-lg-12">
					<input type="button" class="join_back" value="뒤로"
						onclick="location.href='index.html'" style="border-radius: 5px;" />
					<input type="submit" class="join" value="회원가입"
						style="border-radius: 5px;" />
				</div>
				</div>

				<div>
					<br /> <br /> <br /> <br />
				</div>


			</form>





		</div> <!-- container 끝 -->
	</div>




</body>
</html>