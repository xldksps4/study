<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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

<!-- include JQ -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<!-- include bootStrap -->
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<title>게시글확인</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board/boardDetail.js"></script>
</head>

<body>
   <input type="hidden" name="sdbseq" value="${boardDto.sdbseq }" id="sdbseq">
   <input type="hidden" name="sduemail" value="${boardDto.sduemail }"/>
   <div class="row" >
      <div class="col-md-12" id="headTitle">
         <hr>
         <br>
         <h2>${boardDto.sdbseq}번 글 상세</h2>
         <!-- onclick="location.href='writeCancel.do'" -->
         <input type="button" class="button" value="목록으로" onclick="location.href='BOARD_writeCancel.do'"/>
         <input type="button" class="button" value="수정하기" onclick="authorityChk('${sduserDto.sduemail }', '${boardDto.sduemail }', ${boardDto.sdbseq })"/>
         <br>
         <hr>
      </div>
   </div>
   <div id="container">
      <div id="board">
         <div id="content">
            <div id="cdetail">
                 <input type="text" id="title" name="sdbtitle" value="${boardDto.sdbtitle }" readonly="readonly"/>
               <br>
               <div id="cdetail"> <!-- onerror="this.src='/semi/resources/img/board/sample01.jpg';" -->
                  <span><img src="${login.sduimgpath }"></span><span>${boardDto.sdbfilename }</span>
                  <span id="fileDown">
                     <c:if test="${empty boardDto.sdbfilepath }">
                        첨부파일 없음
                     </c:if>
                     <c:if test="${not empty boardDto.sdbfilepath }">
                        ${boardDto.sdbfilepath }
                     </c:if>
                     <input type="button" value="첨부파일 확인" class="button" onclick="fileDetail('${boardDto.sdbseq }');"/>
                  </span>   
               </div>
               <br>
               <br>
               <hr>
               <div class="cdetail2">
                  <div class="blank"></div>
                  <div id="ctext">
                     ${boardDto.sdbcontent }
                  </div>
                  <div id="btn2">
                  </div>
               </div>
            </div>   
         </div>
      </div>
   </div>
   
   <!-- 모달 영역 -->
   <div class="modal fade" id="boardModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog" role="document">
         <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal"
                  aria-label="Close">
                  <span aria-hidden="true">&times;</span>
               </button>
               <h4 class="modal-title" id="myModalLabel">첨부 파일 리스트</h4>
            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">
               <button type="button" class="btn btn-primary" id="yes-btn"
                  onclick="fileDownChk();">선택 다운로드</button>
               <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
            </div>
         </div>
      </div>
   </div>
   
</body>
</html>