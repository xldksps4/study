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
<meta charset="UTF-8">
<title>회원가입</title>


<script type="text/javascript" src="resources/js/jquery-3.4.1.min.js"></script>

<script type="text/javascript">

/*
 이메일 인증 
 - 해당 태그의 값이 없으면 이메일 입력요구
 else 이메일 인증메일 발송 알림 + 새창띄우기(window.open)
 
 
 */
 	// 이메일 인증, 
 	// ↓
 	// 비동기 : 아이디 있음없음 유효성 / 전송되었습니다, '확인 후 입력해주세요'
	function emailChkConfirm() {
 		
 		//window.open 파라미터 이해안감
 		//window.open(url, name, specs, replace);  
 		//name default : _blank(새 창 띄우기) / specs : 창 크기
		window.open("mailSend.jsp?email=" + join_id, "","width=600px, height=400px");	// 해당 jsp 오픈, 
	
 		//이 전에 이메일 인증 팝업부터
 //팝업띄워서 parents id 넣으면 값이주입됨. 	
		var email = document.getElementById("join_id").value;	
	//넣고 reload		

		if (email == "") {
			alert("이메일을 입력해주세요");

		}

		else {
			alert(email + "로 인증메일이 전송 되었습니다");
			//팝업열기
			window.open("mailSend.jsp?email=" + email, "",
					"width=600px, height=400px");	// 해당 jsp 오픈, 

		}
	}
	//아이디 중복확인
	function idChk() {
		var doc = document.getElementsByName("userid")[0];
		if (doc.value.trim() == "" || doc.value == null
				|| doc.value == "indefined") {
			alert("아이디를 먼저 입력해주세요");

		} else {
			window.open("TbRegist.do?command=idChk&userId=" + doc.value, "",
					"width=500,height=300");
		}

	}

	$(function() {
		$("#mailsend").click(function() {
			if ($("#email").val().length == 0) {
				$("#mailsend").show();
			} else {
				$("#mailsend").hide();
			}
		});
	});
	
	
//
//
//아래는 내가 한것(바디 포함)
//
//

// 	var chk1 = false;	// 아이디 / 아이디 유효성>아이디 영어+숫자가 아닐 경우, 아이디 길이
// 	var chk2 = false;	// 이메일 
// 	var chk3 = false;	// 비밀번호
// 	var chk4 = false;	// 멘토멘티 ->> 성별or생년월일로 바꾸면 되려나
	var chk5 = false;	// 약관 동의
	
	
	
//아이디 유효성

	// 아이디 유효성 검사
		$(".input_text").eq(0).keyup(function() {	//아이디
			var idChecked = /^[0-9a-zA-Z]/;
			var id = document.getElementsByClassName("input_text")[0];
			var res = document.getElementsByClassName("check_res")[0];

			if (id.value) { // id가 작성되어 있다면 
				if (!(idChecked.test(id.value))) { // 정규표현식으로 영어 숫자 검사
					chk1 = false;
					res.style.display = "block";
					res.style.color = "red";
					res.textContent = "아이디는 영어+숫자를 사용해 주세요";
					return false;

				} else if ((id.value.length < 5) || (id.value.length > 15)) { // 길이 검사 
					res.style.display = "block";
					res.style.color = "red";
					res.textContent = "아이디는 5 ~ 15자리 사이로 만들어주세요";
					chk1 = false;
					return false;

				} else { // DB에서 아이디 중복검사 
					$.ajax({
						url : "join.do",
						type : "post",
						async : true,
						data : {
							command : "idCheck",
							id : id.value
						},
						dataType : "text",
						success : function(value) {
							if (value === "fail") {
								chk1 = false;
								res.style.display = "block";
								res.style.color = "red";
								res.textContent = "이미 존재하는 아이디 입니다.";
							} else if (value === "ok") {
								chk1 = true; // id 최종적으로 사용가능하면 true
								res.style.display = "block";
								res.style.color = "blue"
								res.textContent = "사용가능한 아이디 입니다.";
							}
						},
						error : function() {
							alert("통신 실패");
						}
					});
				}
			} else {
				//alert("아이디를 입력 하세요.");
				res.textContent = "아이디는 필수입니다.";
				res.style.display = "block";
				chk1 = false;
				return false;
			}
		});
	
	
	
	
//비동기 비밀번호 확인 	
	function pwChk() {

		var joinPw = document.getElementById("join_password");		//비밀번호
		var joinPwRe = document.getElementById("join_password_re");	//비밀번호 확인
		var joinPwSpan = document.getElementById("pw_same");		//비밀번호 유효성


		if (joinPw.value.length< 5 || joinPw.value.length >18) {
			
//			joinPw.focus();	// alert등 후에 해당 텍스트바로 커서 오게 만드는 함수
			document.getElementById("join_pw").value 
			= document.getElementById("join_pw_re").value = "";
			
			joinPwSpan.innerHTML = "비밀번호는 5~17자리로 입력하세요.";
			joinPwSpan.style.color = "red";

		}

		if ((joinPw.value != "") && (joinPwRe.value != "")) {
			if (joinPw.value == joinPwRe.value) {
				joinPwSpan.innerHTML = "비밀번호가 일치합니다.";
				joinPwSpan.style.color = "blue";

			} else {
				joinPwSpan.innerHTML = "비밀번호가 일치하지 않습니다.";
				joinPwSpan.style.color = "red";
			}
		}
	}
	
	
//생년월일
	//화면위치에 따라 아래 또는 위로 보여주려는거?
	window.onload = function() {
		appendYear();
		appendMonth();
		appendDate();
	}
	function appendYear() {
		var date = new Date();
		var year = date.getFullYear();	//getYear() (x) <- 현재 년도에서 1900 뺀 숫자로 나옴.
		var selectValue = document.getElementById("year");
		var optionIndex = 0;
		for (var i = year - 100; i <= year; i++) {
			selectValue.add(new Option(i, i), optionIndex++); //올해부터 -100년도까지 option표기
		}				//add()를 사용해 select>options 값 추가
	}
	function appendMonth() {

		var selectValue = document.getElementById("month");

		var optionIndex = 0;

		for (var i = 1; i <= 12; i++) {
			if (i < 10) {
				i = "0" + i;
			}
			selectValue.add(new Option(i, i), optionIndex++);
		}

	}
	function appendDate() {

		var selectValue = document.getElementById("date");

		var optionIndex = 0;

		for (var i = 1; i <= 31; i++) {
			if (i < 10) {
				i = "0" + i;
			}
			selectValue.add(new Option(i, i), optionIndex++);

		}

	}

	// 약관동의
	function consent(obj) {
		if (obj) {
			chk5 = true;
		} else {
			chk5 = false;
		}
	}

	
	//submit 유효성 처리	
	function validate() {
		var id = document.getElementById("join_id"); 	//id이자 email
		var pw = document.getElementById("join_password");		// 비밀번호
		var pwch = document.getElementById("join_password_re");		//비밀번호 확인
		var pwSpan = document.getElementById("pw_same");		// 비밀번호 확인 스판
// 		var pw = document.getElementsByName("user_pw")[0];	//비밀번호 input의 이름, 이거 왜
		var name = document.getElementById("join_name");		//이름
		var year = document.getElementById("year");		//년
		var month = document.getElementById("month");	//월
		var date = document.getElementById("date");		//일
		
		// 질문 : regist.id명.value로 작성한 이유가 뭐지? form태그 name이던데
		if (pw.value != pwch.value) {
			alert("비밀번호가 틀립니다. 다시 한번 확인해 주세요");

			pwch.value = "";
			pwch.focus();
			return false;

		} else if (id.value == "") {
			alert("이메일을 입력해 주세요");

			id.focus();
			return false;

		} else if (name.value == "") {
			alert("이름을 입력해 주세요");

			name.focus();
			return false;

		} else if (year.value = "") {
			alert("연도을 선택해 주세요");

			year.focus();
			return false;

		} else if (month.value = "") {
			alert("월을 선택해 주세요")

			month.focus();
			return false;

		} else if (date.value = "") {
			alert("일을 선택해 주세요");

			date.focus();
			return false; //이벤트 확산 안되는거고
		}
	}	// 질문 : body>form>onsubmit="return validate()"의 return?,
			// form태그에서 return true는 그냥 submint되는건가?
	
		
	
	
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
.button:hover{
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
	<div> </div>
	<div> </div>
	<div> </div>
</div>
<div id="wrap_register_form">
	<div id="wrap_register_header"> 
	</div>
	<div>
	</div>
	<div><h1>회원가입</h1><br/><br/></div>
	<div id="wrap_regiter_joinform">
	
<!-- 회원가입 form --> 		
		<form action="TbRegist.do" method="post" name="regist"
		onsubmit="return validate()">
		<input type="hidden" name="command" value="insert">

			

<!-- 
id / class / onclick / 기타
아이디 : x / x / idChk() / button(버튼 클래스) 
이메일 : mailsend / button / emailsend() / title="n"
<input type="hidden" name="to" value="hoyhi123@naver.com">
<input type="hidden" name="from" value="hoyhi123@naver.com">
<input type="button" id="mailsend" title="n" value="인증코드전송" onclick="emailsend()" class="button">
비밀번호 : pw
비밀번호확인 : pwch same(유효성 id)
이름 : username / 
성별 : x / button
-->

<!-- 아이디&이메일 -->

	<div>
		<p>아이디</p>
		<input type="email" 
		id="join_id" class="input_text" name="user_id" placeholder="ID는 이메일 형식입니다."
		required="required" onclick="emailChkConfirm()"/>	
		<span class="check_res" style="display: none"></span>
<!-- 		<input type="button" onclick="emailsend()"> -->
	</div>
	<br/>	

<!-- 비밀번호 -->
	
	<div>
		<p>비밀번호</p>
		<input type="password" 
		id="join_password" class="input_text" name="user_pw" placeholder="비밀번호를 입력하세요"
		required="required" maxlength="19" onkeyup="idChk()"/>
		
		<p>비밀번호 확인</p>
		<input type="password"
		id="join_password_re" class="input_text" name="user_pw_re" placeholder="비밀번호를 다시 입력하세요"
		required="required" maxlength="19" onkeyup="idChk()"/>
		
		<span id ="pw_same" class="check_res" style="display: none"></span>
	</div>
	<br/>		
		
	
<!-- 이름 -->	
	<div>
		<p>이름</p>
		<input type="text" id="join_name" name="user_name" 
		required="required" maxlength="4"/>
	</div>
	<br/>	
	
<!-- 성별 -->
	<div>
		<p>성별</p>
		남성<input type="radio" name="user_gender" checked="checked"
		value="남성" class="button" /> 
		여성<input type="radio" name="user_gender" 
		value="여성" class="button" />
	</div>	
	<br/>
	
	<div>
		<select name="year"  id="year">
			<option value=""></option></select> 년
		<select name="month" id="month">
			<option value=""></option></select> 월 
		<select name="date" id="date">
			<option value=""></option></select>일
	</div>
	
	<div><br/><br/><br/><br/></div>
	
	<div class="span_text">
		<input type="checkbox" name="check" required="required" onclick="consent(this);"/>
		<span>이용약관 및 개인정보 취급방침에 동의하시겠습니까?</span>
	</div>
	<div>
	<input type="button" class="join_back" value="뒤로" onclick="location.href='index.html'"
	style="border-radius: 5px;"/>
	<input type="submit" class="join" value="회원가입"
	style="border-radius: 5px;"/>
	</div>
	
	<div><br/><br/><br/><br/></div>
	
	
	</form>
		
		
		
		
		
	</div>
</div>










	

<%-- 	<%@ include file="./form/footer.jsp"%> --%>



</body>
</html>