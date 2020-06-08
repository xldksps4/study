//최종 유효성검사 용도 전역변수 선언
	var idChk = false;
	var pwChk = false;
	var nameChk = false;
	//닉네임은 선택입력처리해볼게요
	var dayOfBirthChk = false;
	var sexChk = false;
	//약관동의
	var acceptChk = false;


// 이메일 인증코드 변수
var emailCode = "";

// 아이디 유효성 검사(중복 체크)
function idCheck() {
	console.log('아이디 중복체크');
	// 이메일 주소 형식 체크 정규식
	var idChecked = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

	var joinId = document.getElementById('join_id').value; // 변수 id와 동일
	// var id = document.getElementsByClassName("input_text")[0].value; //id입력창의
	// 값
	var res = document.getElementsByClassName("check_res")[0]; // id비동기 결과창

	// data: 에 보낼 값을 변수로
	var sendData = {
		joinId : joinId
	}

	if (joinId) { // id가 작성되어 있다면
		if (!(idChecked.test(joinId))) { // 정규표현식으로 영어 숫자 검사
			idChk = false;
			res.textContent = "";
			res.style.display = "block";
			res.style.color = "red";
			res.textContent = "아이디는 영어+숫자를 사용해 주세요";
			return false;

		} else if ((joinId.length < 8) || (joinId.length > 50)) { // 길이 검사 ,
																	// 차후 수정 필요
			res.textContent = "";
			res.style.display = "block";
			res.style.color = "red";
			res.textContent = "아이디는 이메일 형식으로 만들어주세요";
			idChk = false;
			return false;

		} else { // DB에서 아이디 중복검사
			$.ajax({
				url : 'idCheck.do',
				type : 'post',
				async : true,
				data : sendData,
				dataType : "text",

				success : function(result) {
					if (result === "idAlreadyHave") { // 컨트롤러에서 넘어온 return
						idChk = false;
						res.textContent = "";
						res.style.display = "block";
						res.style.color = "red";
						res.textContent = "이미 존재하는 아이디 입니다.";
					} else if (result === "idDoNotHave") {
						idChk = true; // id 최종적으로 사용가능하면 true
						res.textContent = "";
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
		chk1 = false;
		// alert("아이디를 입력 하세요.");
		res.textContent = "아이디는 필수입니다.";
		res.style.display = "block";

		return false;
	}

}

// 이메일 인증 smtp
function verifyEmailConfirm() {

	// 사용자가 입력한 이메일 이 담긴 태그 id
	var joinId = document.getElementById('join_id').value;
	// 보낼 때 변수명
	var to = {
		to : joinId
	}

	$.ajax({
		url : 'SDUSER_sendMail.do',
		type : 'post',
		async : true,
		data : to,
		dataType : "text",
		success : function(data) {
			alert('인증코드가 발급되었습니다. 메일을 확인하세요.');
			emailCode = data;
			console.log(emailCode);
		},
		error : function(err) {
			alert('통신 실패 에러.');
			console.log('err는 : ' + err);
		}
	});
}

// 이메일 인증코드 확인
function emailChk() {
	// 텍스트바 id = verify_num / 확인버튼 id= verify_Check
	var verifyNum = document.getElementById('verify_num').value;
	var res = document.getElementsByClassName("check_res")[0]; // id비동기 결과창

	if (verifyNum == "") {
		res.textContent = "";
		res.style.display = "block";
		res.style.color = "red";
		res.textContent = "인증코드를 써주세요.";
	} else if (verifyNum != emailCode) {
		res.textContent = "";
		res.style.display = "block";
		res.style.color = "red";
		res.textContent = "인증코드가 일치하지 않습니다.";
	} else {
		res.textContent = "";
		res.style.display = "block";
		res.style.color = "green";
		res.textContent = "인증코드가 일치합니다.";
	}
}

// 비동기 비밀번호 확인
function pwCheck() {

	// var pwChecked = /^[0-9a-zA-Z]/;
	// 숫자, 특문 각 1회 이상. 영문은 2개 이상 사용하여 8자리 이상 입력
	var pwChecked = /(?=.*\d{1,50})(?=.*[~`!@#$%\^&*()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,50}$/;
	var joinPw = document.getElementById("join_password").value; // 비밀번호 태그 값
	var res1 = document.getElementsByClassName("check_res")[1];
	// var joinPwSpan = document.getElementById("pw_same"); //위와 동일

	if (joinPw) { // pw 값이 존재한다면
		if (!pwChecked.test(joinPw)
				&& (joinPw.length < 5 || joinPw.length > 18)) {

			chkPw = false;
			res1.textContent = "";
			res1.style.display = "block";
			res1.style.color = "red";
			res1.textContent = "아이디는 영어와 숫자 혼합, 5~17자리로 사용해 주세요";
			return false;

		} else {
			res1.textContent = "";
			res1.style.display = "block";
			res1.style.color = "green";
			res1.textContent = "사용 가능한 비밀번호입니다.";
			return true;
		}
	} else {
		// alert("비밀번호를 입력 하세요.");
		res1.textContent = "";
		res1.style.display = "block";
		res1.style.color = "red";
		res1.textContent = "비밀번호는 필수입니다.";
		chkPw = false;
		return false;

	}
}

// 비밀번호 확인
function pwCheckRes() {

	var joinPw = document.getElementById("join_password").value; // 비밀번호 태그 값
	var joinPwRe = document.getElementById("join_password_re").value; // 확인값
	var res1 = document.getElementsByClassName("check_res")[1];
	var res2 = document.getElementsByClassName("check_res")[2];

	if ((joinPw != "") && (joinPwRe != "")) {
		if (joinPw === joinPwRe) {
			chkPw = true;
			res1.textContent = "";
			res2.textContent = "";
			res2.style.display = "block";
			res2.style.color = "green";
			res2.textContent = "비밀번호가 일치합니다.";
			// return true;

		} else {

			chkPw = false;
			res1.textContent = "";
			res2.textContent = "";
			res1.style.display = "block";
			res1.style.color = "red";
			res1.textContent = "비밀번호가 일치하지 않습니다.";
			// return false;

		}
	} else {
		// alert("비밀번호를 입력 하세요.");
		res1.textContent = "비밀번호확인은 필수입니다.";
		res1.style.display = "block";
		chkPw = false;
		// return false;
	}
}

// 생년월일 dayOfBirth 유효성 검사
function dobCheck() {
	var sdudob = document.getElementById("sdudob").value;
	var year = Number(document.getElementById("sdudob").value.substring(0, 4));
	var month = Number(document.getElementById("sdudob").value.substring(4, 6));
	var date = Number(document.getElementById("sdudob").value.substring(6, 8));
	var today = new Date();
	var yearNow = today.getFullYear(); // getYear() (x) <- 현재 년도에서 1900 뺀 숫자로
										// 나옴.

	var res3 = document.getElementsByClassName("check_res")[3];

	if (sdudob.length <= 8) {
		// 연도의 경우 -120년보다 작거나 yearNow보다 크다면 false를 반환합니다.
		if (((yearNow - 120) > year) || (year > yearNow)) {

			dayOfBirthChk = false;
			res3.textContent = "";
			res3.style.display = "block";
			res3.style.color = "red";
			res3.textContent = "생년월일을 확인해주세요.";

		} else if (month < 1 || month > 12) {

			dayOfBirthChk = false;
			res3.textContent = "";
			res3.style.display = "block";
			res3.style.color = "red";
			res3.textContent = "태어난 달을 확인해주세요.";

		} else if (date < 1 || date > 31) {

			dayOfBirthChk = false;
			res3.textContent = "";
			res3.style.display = "block";
			res3.style.color = "red";
			res3.textContent = "태어난 날을 확인해주세요.";

			// 다중 if문 만들지 않기 위해...
		} else if ((month == 4 || month == 6 || month == 9 || month == 11)
				&& date == 31) {

			dayOfBirthChk = false;
			res3.textContent = "";
			res3.style.display = "block";
			res3.style.color = "red";
			res3.textContent = "해당 달에는 31일이 없습니다..";

		} else if (month == 2) {

			// 윤년계산
			var isLeap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));

			if (date > 29 || (date == 29 && !isLeap)) {

				dayOfBirthChk = false;
				res3.textContent = "";
				res3.style.display = "block";
				res3.style.color = "red";
				res3.textContent = "해당년도 2월은 해당일자가 없습니다.";

			} else {

				dayOfBirthChk = true;
				res3.textContent = "";
				res3.style.display = "block";
				res3.style.color = "green";
				res3.textContent = "생년월일을 성공적으로 입력하셨습니다.";

			}// end of if(day>29 || (day==29 && !isLeap))

		} else {

			dayOfBirthChk = true;
			res3.textContent = "";
			res3.style.display = "block";
			res3.style.color = "green";
			res3.textContent = "생년월일을 성공적으로 입력하셨습니다.";

		}
		
	} else { //8글자 초과 시
		
		//maxlength : 8 처리해둔 상태
		
	}

}

// 성별 라디오박스에서 뽑아 오기
function innerVal() {
	var sdusexVal = document.getElementsByName("sdusex");

	for (var i = 0; i < sdusexVal.length; i++) {
		if (sdusexVal[i].checked) {
			var sdusex = sdusexVal[i].value;
			alert(sdusex);
			return sdusex;
		}
	}

}

//약관
function accept(obj) {
	if (obj) {
		acceptChk = true;
	} else {
		acceptChk = false;
	}
}

// submit 유효성 처리
// 빈칸이 있었을 경우 정보가 넘어가지 않도록 함
function validate() {
	
	//DB저장 위한 값들
	var sdusex = innerVal();
	var sduemail = document.getElementById("join_id").value;
	var sdupw = document.getElementById("join_password").value;
	var sduname = document.getElementById("join_name").value;
	var sdunick = document.getElementById("no_sdunick").value;
	var sdudob = document.getElementById("sdudob").value;
	
//	console.log(sduemail);// 출력됨 ㅇㅇ 근데 sdusex안나옴
	
	if(idChk == false){
		alert("아이디를 입력해주세요");
		return false;
	}else if(pwChk == false){
		alert("비밀번호를 입력해주세요");
		return false;
	}else if(nameChk == false){
		alert("이름을 입력해주세요");
		return false;
	}else if(dayOfBirthChk == false){
		alert("생년월일을 입력해주세요");
		return false;
	}else if(acceptChk == false){
		alert("약관동의를 체크해주세요");
		return false;
	}else{
	
		return true;
	}

}