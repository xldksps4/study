
//써머노트 웹 에디터 로딩
$(document).ready(function() {
     $('#summernote').summernote({
             height: 300,                 // set editor_ height
             minHeight: null,             // set minimum_ height of editor
             maxHeight: null,             // set maximum_ height of editor
             focus: true,                  // 에디터 로딩 후 focus를 맞출지 여부
             lang: "ko-KR",
             placeholder: '필기하세요'
     });
});

//서머노트에 text 쓰기
$('#summernote').summernote('insertText', '써머노트에 쓸 텍스트');


// 서머노트 쓰기 비활성화
$('#summernote').summernote('disable');

// 서머노트 쓰기 활성화
$('#summernote').summernote('enable');


// 서머노트 리셋
$('#summernote').summernote('reset');


// 마지막으로 한 행동 취소 ( 뒤로가기 )
$('#summernote').summernote('undo');
// 앞으로가기
$('#summernote').summernote('redo');



/*
 써머노트 특징

HTML5 를 기반으로 사용하기 때문에 플래시가 없습니다.
- 웹접근성을 고려할 수 있습니다.

오픈소스이기 때문에 많은 개발자 버전이 존재 합니다.
- github에서 검색해 봅니다. (세상에는 똑똑한 사람들이 참 많습니다.)
 
 무료

 기본 이미지 저장방식 : base64 <<< 리소스 관리 측면에서 무리가 있기에 커스텀 필요
 동영상 업로드 : 불가능, 링크 게시 방식. <<< YouTube, Vimeo, Instagram 등의 동영상을 불러오려면 커스텀 필요

 
 */