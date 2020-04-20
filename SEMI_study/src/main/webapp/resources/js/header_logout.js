
//구글 계정 로드
// 1. 구글은 로그인과 로그아웃을 한페이지에서 처리하지 않으면 로그인된 정보를 다시한번 불러와야 한다 그때 사용되는 함수(gapi.load())이다.
// 2. 해당 함수를 html의 해더에 구글 js를 받아오는 정보에 쿼리스트링으로 추가해야 한다
// >>> <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
function onLoad() {
    gapi.load('auth2', function() {
      gapi.auth2.init();
    });
}

//구글 로그아웃
// 1. 로그인과 다른 페이지에서 gapi.auth2 객체를 사용하려면 위의 gapi.load()함수로 호출해야 사용가능하다.
// 2. 해당 gapi.auth2 객체를 사용해서 로그아웃을 하지 않을시 기존 계정이 남아 크롬에서 해당페이지 자동 로그인이 된다.(로그인 페이지에 가면 바로 로그인 실행)
function signOut() {
   var auth2 = gapi.auth2.getAuthInstance(); 
   auth2.disconnect();             // 구글 강제 로그아웃   #1  >> 먼저사용할 경우 다시 로그인클릭시 계정입력창이 뜬다
    auth2.signOut().then(function () { // 구글 로그아웃 실행   #2  >> 먼저 사용할 경우 다시 로그인클릭시 이전 계정으로 자동 로그인된다.
      console.log('User signed out.');
    });         
}

//카카오 로그아웃 >> 카카오는 로그인시 브라우져에 계정을 적용하는데, 로그아웃은 해당 사이트만 시킨다. 연결된 소셜계정들에 영향을 주지 않기 위해서라고함.
function kakaoLogout(){
   Kakao.init('9f7a7d3a273350b18a01ba15bdae8c67');
   Kakao.Auth.logout(function() {   
      console.log(Kakao.Auth.getAccessToken());
   }); 
}

//카카오 계정 만료 >> 사이트와의 연결 끊기, 이것을 사용하면 매번 로그인시 카카오 계정 사용 동의 페이지가 로드됨
function kakaoConnectionExpire(){
   Kakao.init('9f7a7d3a273350b18a01ba15bdae8c67');      // #1
   Kakao.API.request({                           // #2
      url : '/v1/user/unlink',                  // 계정 연동 끊는 명령 url
      success : function(response) {                // #3 결과 
         console.log(response);
      },
      fail : function(error) {
         console.log(error);
      },
   });
}

function logoutGo() {
   // 카카오 계정 연결 끊기 >> 사이트와의 연결 끊기, 이것을 사용하면 매번 로그인시 카카오 계정 사용 동의 페이지가 로드됨
   //kakaoConnectionExpire()
   // 카카오 로그아웃
   kakaoLogout()
   onLoad()  // 로그인된 계정호출
   signOut() // 구글계정 로그아웃
   location.href = "USER_logout.do"
}



