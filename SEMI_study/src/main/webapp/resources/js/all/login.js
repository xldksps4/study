//로그인창 팝업
function openLogin(){
	window.open('/semi/SDUSER_login.do','_blank','width=356px,height=405px,location=no,status=no,scrollbars=no');
}

$(function() {
	$("#error").hide();

});
// 비밀번호 확인
function idpwcheck() {
	alert("확인")
	if ($("#newpw").val() != $("#pwcheck").val()) {
		alert("비밀번호를 확인해주세요.")
		return true;
	} else {
		return true;
	}
}

// 이메일 인증 팝업
function checkid() {
//	window
//			.open("USER_emailcheckpopup_login.do", "",
//					"width=500px,height=500px");
//	chk01 = true
}

//post 방식으로 전송하는 메서드
function postSend(path, params, method) {
	method = method || "post";
	var form = document.createElement("form");
	form.setAttribute("method", method);
	form.setAttribute("action", path);
	// 히든으로 값을 넣는다.
	for ( var key in params) { // {'name1':'var1','name2':'var2','name3':'var3'}
		var input_tag = document.createElement("input");
		input_tag.setAttribute("type", "hidden");
		input_tag.setAttribute("name", key) // name1, name2, name3
		input_tag.setAttribute("value", params[key]) // var1, var2, var3
		console.log("name : " + key);
		console.log("value : " + params[key]);
		form.appendChild(input_tag);
	}
	document.body.appendChild(form);
	form.submit();
}


// 일반 로그인
function login() {
	var joinemail = document.getElementById('join_email').value;
	var joinpw = document.getElementById('join_pw').value;
	 console.log("joinemail : " + joinemail + " / joinpw : " + joinpw);

	var loginVal = {
		"sduemail" : joinemail,
		"sdupw" : joinpw
	};

	console.log(loginVal)

	// 유저아이디나 유저 비밀번호가 없다면
	if (joinemail == null || joinemail == "" || joinpw == null || joinpw == "") {
		alert("아이디나 비밀번호를 입력해주세요 !");
	} else {
		console.log("ajax:::::"+loginVal);
		$.ajax({
			type : "post",
			url : "SDUSER_loginAjax.do",
			data : JSON.stringify(loginVal),
			contentType : "application/json",
			dataType : "json",
			success : function(msg) {
				console.log(msg);
				
				var paramsjoin = {
						"sduemail" : joinemail,
						"sdupw" : joinpw
					};
				
				if (msg.check == true) {
					location.href = "MAIN_main.do"
					postSend("MAIN_main.do", paramsjoin, "post");
				} else {
					$("#error").show();
					$("#error").html("ID 혹은 PW가 잘못되었습니다.");
				}
			},
			error : function() {
				alert("통신 실패");
			}
		});
	}
}
function modal() {
	$('#modal').modal('show');
	$("#M_error").hide();
}

// [modal]================================================

// 아이디,비밀번호 수정
window.onload = function() {

	// 비밀번호 일치 여부
	$('#pwcheck').focusout(function() {
		var pw = $('#newpw').val();
		var pwChk = $('#pwcheck').val();
		var res = $('#errormessge');

		if (pw != "" || pwChk != "") {
			if (pw == pwChk) {

				res.html("비밀번호가 일치합니다.");
			} else {

				res.html("비밀번호가 일치하지 않습니다");
			}
		}
	});

}






// sns로그인===================================================
function kakao() {
//	alert("[카카오]init 전");
	//지원 : 3ffc02510f976b23accd140b3077863a
	//재욱 : 허용안됨 : 42b4ddd5cd6868a72edf971ad95d9f15
	Kakao.init('3ffc02510f976b23accd140b3077863a'); // #1 api 인증번호 
//	alert("[카카오]init 후, loginForm함수 실행 전");
	Kakao.Auth.loginForm({
		success : function(authObj) { // #2 카카오 측에 접속 성공했으면
			Kakao.API.request({ // #3 요청
				url : '/v2/user/me',
				success : function(res) { // #4 사용자가 로그인에 성공했으면
					
//		찍힘			alert("카카오 성공");
					console.log(res); // res >>> 토큰
					var kakaoemail = res.kakao_account.email; // 기본으로 가져올 수 있음
					var sduname = res.kakao_account.profile.nickname;
					var sdupw = "kakao123";
					var sdudob = res.kakao_account.birthday; // 생일 : 가져오기 설정
																// 및 >> 동의 받아야함
					var sdusex = res.kakao_account.gender; // 성별 : 가져오기 설정 및 >>
															// 동의 받아야함
//					let date = new Date().getFullYear().slice(-2);
//					alert('new Date().getFullYear() 두자리가 20이길 : '+date );
					
					var params = {
						"sduemail" : kakaoemail
					};

					var paramslogin = {
						"sduemail" : kakaoemail,
						"sdupw" : sdupw
					};
					
// 실패.. 0000월일 들어가도 에러남					[java.lang.String] to type [java.util.Date] for value '00001019'; nested exception is java.lang.IllegalArgumentException]
//					if (sdudob == '' || sdudob == 'undefined'){
//						sdudob = '0000-12-25';
//					} else if(sdudob[0]=='1') { 
//						sdudob8 = '0000'+sdudob;
//					}
					
					
					if (sdusex === "f") {
						sdusex = "f"
					} else if (sdusex === "m") {
						sdusex = "m"
					} else{
						sdusex = "g"
					}

					var paramsjoin = {
						"sduemail" : kakaoemail,
						"sduname" : sduname,
						"sdupw" : sdupw,
						"sdudob" : sdudob,
						"sdusex" : sdusex
					};
					
					alert('kakaoemail : '+kakaoemail +'  sdudob : '+sdudob+'  sdusex : '+sdusex +" sdudob: "+sdudob);
					console.log("값들이 잘 담겼나~"+paramsjoin);

					console.log(res.id);// <---- 콘솔 로그에 id 정보 출력(id는 res안에 있기
										// 때문에 res.id 로 불러온다)
					console.log(res.kakao_account.email);
					console.log(res.kakao_account.birthday);
					console.log(res.kakao_account.gender);
					console.log(res.properties['nickname']);// <---- 콘솔 로그에 닉네임
															// 출력(properties에 있는
															// nickname 접근
					console.log(authObj.access_token);// <---- 콘솔 로그에 토큰값 출력
					alert('post 전송 바로전, : paramsjoin : '+ paramsjoin)
					// post전송
					postSend("SDUSER_snslogin.do", paramsjoin, "post");
				}
			})
		},
		fail : function(error) {
			alert("카카오 실패");
			alert(JSON.stringify(error));
		}
	});
}

// 구글 로그인
function onSignIn(googleUser) { // #1
	var profile = googleUser.getBasicProfile(); // #2

	var paramsjoin = {
		"joinemail" : profile.getEmail(),
		"joinname" : profile.getName(),
		"joinpw" : profile.getId(),
		"joinbirth" : '',
		"joinsex" : ''
	};

	// 구글계정를 로그인 하자마자 정보만 사용하고 계정 만료시킬때 사용
	/*
	 * var auth2 = gapi.auth2.getAuthInstance(); // 현재 로그인된 정보 받아오기
	 * auth2.signOut().then(function() { // 로그아웃 실행 console.log('Usersigned
	 * out.'); }); auth2.disconnect(); // 구글 강제 로그아웃
	 */

	// 서버에 정보 post 전송
	postSend("USER_snslogin.do", paramsjoin, "post");

	// 구글이 지원하는 토큰을 직접 서버로 보내는 로직 >>> awt에 주로 사용된다.
	/*
	 * // 성공적으로 로그인후 내 사이트의 서버로 보낼 토큰을 얻는다 var id_token =
	 * googleUser.getAuthResponse().id_token;
	 *  // 서버로 토큰을 보낸다 var xhr = new XMLHttpRequest(); xhr.open('POST',
	 * 'https://yourbackend.example.com/tokensignin'); // 내 서버로 url 입력
	 * xhr.setRequestHeader('Content-Type',
	 * 'application/x-www-form-urlencoded'); xhr.onload = function() {
	 * console.log('Signed in as: ' + xhr.responseText); }; xhr.send('idtoken=' +
	 * id_token);
	 */
}
