<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>
<%
 response.setHeader("Cache-Control","no-cache");
 response.setHeader("Pragma","no-cache");
 response.setDateHeader("Expires",0);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv='Content-type' content='text/html; charset=utf-8'>
<meta http-equiv="cache-control" content="no-cache, must-revalidate">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Expires" content="Mon, 06 Jan 1990 00:00:01 GMT">


<title>게시글수정</title>

<!-- summernotr 부트스트랩4 연동 버전/ 3버전과 사용하면 css 충돌이 발생한다. -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board/boardUpdate.js"></script>

<!-- <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>


<script type="text/javascript" src="${pageContext.request.contextPath}/resources/summernote/lang/summernote-ko-KR.js"></script>

<!-- without bootstrap -->
<!-- <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css" rel="stylesheet"> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script> -->

</head>

<body>
<form action="updateRes.do" method="post" id="form" enctype="multipart/form-data">
   <input type="hidden" name="sdbseq" value="${boardDto.sdbseq}" id="sdbseq">
   <input type="hidden" name="sduemail" value="${sduserDto.sduemail }"/>
   <input type="hidden" name="sduname" value="${sduserDto.sduname }"/>
   <input type="hidden" name="sdbcontent"/>
   <div class="row" >
      <div class="col-md-12" id="headTitle">
         <hr>
         <br>
         <h2>자유게시판</h2>
         <!-- onclick="location.href='writeCancel.do'" -->
         <input type="button" class="button" value="수정취소" onclick="location.href='BOARD_boarddetail.do?sdbseq=${boardDto.sdbseq}'"/>
         <input type="button" class="button" value="수정완료" onclick="AjaxFileUpdate();"/>
         <input type="submit" style="display: none" id="submit"/>
          
         <br>
         <hr>
      </div>
   
   </div>
   <div id="container">
      <div id="board">
         <div id="content">
            <div id="cdetail">
          
                 <input type="text" id="title" name="sdbtitle"  value="${boardDto.sdbtitle }"/>
               <br>
               <div id="cdetail">
                  <span>${sduserDto.sduname }</span>
                  <input type="file" id="file" multiple="multiple" name="file" onclick="return warning();"/>
               </div>
               <br>
               <br>
               <hr>
               <div>
                  <div></div>
                  <div id="ctext">
                     <textarea rows="100" cols="100" id="summernote">${boardDto.sdbcontent }</textarea>
                     
                  </div>
                  <div id="btn2">
                  </div>
               </div>
            </div>   
         </div>
      </div>
   </div>
</form>
</body>
</html>