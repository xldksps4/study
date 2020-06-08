<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<!-- include summernote css/js-->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/summernote/js/summernote.js"></script>

<!-- file preview -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/file/preview.js"></script>

<!-- file upload -->

</head>
<body>
<f:form method="post" action="writeRes.do"  enctype="multipart/form-data">
<f:hidden path="sdbseq"/>
<f:hidden path="sdbfirstfolder"/>
<f:hidden path="sdbregdate"/>
	
<div class="container">
<div class="row">
	<select>
		<!-- for문으로 돌려서 값이 있으면 해당 게시판 option태그들 추가 -->
	</select>
</div>

<div class="row">
<div class="col-md-12">
	제목 : <span><f:input path="sdbtitle" placeholder="제목"/></span><span>&nbsp;&nbsp;</span>
	작성자 : <span><f:input path="sduemail" readonly="true"/></span><br/>
	<span><f:errors path="sdbtitle" cssClass="error"/></span><br/>

	<textarea id="summernote" name="sdbcontent"></textarea><br/>


</div>
</div>
</div>
 파일명 <input type="text" name="fileName" />
파일첨부 <input type="file" />

<input type="submit" value="글작성 완료"/>
</f:form>


</body>
</html>