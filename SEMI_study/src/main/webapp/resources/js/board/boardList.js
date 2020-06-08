/*---------- 
 * curPage = 
 * 페이징 시 필요한 현재 머물러있는 페이지
 * 이걸 계속 넘겨줘야 뒤로가기 눌렀을 때 해당 페이지로 고정된다.
 * 예를 들어 현재 글이 3페이지에 존재하는 글이라면, 그 정보를 계속 넘겨줘야 댓글작성 등 작업 후에 뒤로가기했을때
 * 3페이지에 머물러 있음
 * ----------*/
curPage = $('.paging-focus').text();


/*---------- 페이징 : paging func ----------*/
//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa 미사용, 수정필요
function paging(curPage) {
	location.href = "BOARD_boardList.do?curPage=" + curPage;
}

/*---------- 상세글보기 : boardDetail func ----------*/
function boardDetail(sdbseq) {
//	var curPage = document.getElementsByClassName('paging-focus').text;  // 쿼리 : $('.paging-focus').text();
	location.href='BOARD_boarddetail.do?sdbseq=' + sdbseq; //+ '&curPage=' + curPage;
}



/*---------- 검 색 : search func ----------*/
//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa 미사용, 수정필요
function search() {
	
	// category : 제목(title), 본문(content), 작성자(joinemail)
	var category = $('#search-category').val();
	// keyword : input keyword
	var keyword = $('#keyword').val();
	
	// controller
	location.href='BOARD_boardList.do?category=' + category + '&keyword=' + keyword;
}

/*---------- 검색(엔터치면 입력) : onKeyDown func ----------*/
//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa 미사용, 수정필요
function onKeyDown() {
    if(event.keyCode == 13) {
         search();
    }
}