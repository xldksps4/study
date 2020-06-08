<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv='Content-type' content='text/html; charset=utf-8'>
<meta http-equiv="cache-control" content="no-cache, must-revalidate">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Expires" content="Mon, 06 Jan 1990 00:00:01 GMT">


<title>게시글작성</title>

<!-- include JQ -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<!-- summernotr 부트스트랩4 연동 버전/ 3버전과 사용하면 css 충돌이 발생한다. -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
	
<!-- <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>

<!-- summernote 부트스트랩 없이 단독사용 라이트 버전 -->
<!-- <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script> -->
<!-- <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css" rel="stylesheet"> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script> -->

<!-- include summernote ready AND fileupload -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/summernote/js/summernote-fileupload.js"></script>
<!-- include summernote-ko-KR -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/summernote/lang/summernote-ko-KR.js"></script>

</head>

<body>
	<form action="BOARD_boardwriteres.do" method="post" id="form"
		enctype="multipart/form-data">
		<input type="hidden" name="sdbseq" value="0" id="sdbseq"> 
		<input type="hidden" name="sduemail" value="${loginDto.sduemail }" /> 
		<input type="hidden" name="sduname" value="${loginDto.sduname }" />
		<input type="hidden" name="sdbcontent" value="" />	<!-- js에서 보내줄거에요 -->
		<div class="row">
			<div class="col-md-12" id="headTitle">
				<hr>
				<br>
				<h2>자유게시판</h2>
				<!-- onclick="location.href='writeCancel.do'" -->
				<input type="button" class="button" value="작성취소"
					onclick="location.href='BOARD_writeCancel.do'" /> 
				<input type="button" class="button" value="저장하기" 
					onclick="AjaxFileUpload();" /> 
				<input type="submit" id="submit" 
					style="display: none" value="submit" /> <br>
				<hr>
			</div>

		</div>
		<div id="container">
			<div id="board">
				<div id="content">
					<div id="cdetail">
						<span><img src="${login.sduimgpath}"></span><span> | </span>
						<span>이름</span> | <span>${loginDto.sdunick }</span> <br>
						<span>제목</span> | <input type="text" id="title" name="sdbtitle" placeholder="제목을 입력해주세요" /> <br>
						<div id="cdetail">
							<span>파일</span> | <input type="file" id="file" multiple="multiple" name="file" />
						</div>
						<br> <br>
						<hr>
						<div class="cdetail2">
							<div class="blank"></div>
							<div id="ctext">
								<p>내용을 입력해주세요</p>
								<textarea rows="100" cols="100" id="summernote"></textarea>
		<!--                      <input type="text" id="context" name = "content" placeholder="내용을 입력해주세요"/> -->
							</div>
							<div id="btn2"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>