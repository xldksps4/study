<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board/boarddetail_a4.css">

</head>
<body>
	<div class="container" id="container" style="float:left; width:21cm; height: 29.7cm;">
	<div class="">

		<!-- A4형식 글보기 폼 -->
		<div class="book">
		<span>작성자 : ${boardDto.sduemail }</span>
		<span> 작성일자 : ${boardDto.sdbregdate }</span><br/>
		<span>제목 : ${boardDto.sdbtitle }</span>
		<div class="page">
			<div class="subpage" id="content">${boardDto.sdbcontent }</div>
		</div>
		<input type="button" value="삭제" style="float: right;"
			onclick="location.href='BOARD_detailDelete.do?sdbseq=${boardDto.sdbseq}'" />
		<input type="button" value="목록"
			onclick="location.href='user_list.do?sdbseq=${boardDto.sdbseq }'" />
			<!-- history.back()을 사용할 경우, 버퍼문제, 보안이슈가 발생할 수 있음. -->
		</div>
	</div>
	</div>


</body>
</html>