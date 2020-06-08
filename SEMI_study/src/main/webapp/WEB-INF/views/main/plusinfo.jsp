<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<!-- file upload -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/file/preview.js"></script>

</head>
<body>
<c:choose>
		<c:when test="${empty login }">
			<%@ include file="../all/header_login.jsp" %>
		</c:when>
		<c:otherwise>
			<%@ include file="../all/header_minibar.jsp" %>
			
		</c:otherwise>
	</c:choose>
	<!-- (+)닉네임 -->
	<div class="container">

		<!-- MAIN_plusinfo.do -->
		<f:form method="post" action="SDUSER_infoUpdate.do" modelAttribute="sduserDto" enctype="multipart/form-data">
			<f:hidden path="sduseq"/>
			<table class="table">
				<tr>
					<td>프로필</td>
					<td id="img-insertimg-notorder-null"><img alt="이미지를 입력하세요" src="${login.sduimgpath }" /></td>
					<td><f:input type="file" path="sdufileupload" id="img-preview-notorder-haveimg" onclick="imgUpload()" /></td>
					
				</tr>
				<tr>
					<td>아이디</td>
					<td><f:input path="sduemail" readonly="true"/></td>
				</tr>

				<tr>
					<td>이름</td>
					<!-- <td><input type="text" id="sduname" /></td> -->
					<td><f:input path="sduname" /></td>
					<td><f:errors path="sduname" cssClass="error" /></td>
				</tr>
				<tr>
					<td>닉네임</td>
					<td><f:input path="sdunick" /></td>
					<!-- dto값 쓰는게 맞지? -->
					<td><input type="button" value="닉네임을 입력하세요"
						onclick="check_nickname()"> <f:errors path="sdunick"
							cssClass="error"></f:errors></td>
				</tr>
				<tr>
					<td>생년월일</td>
					<!-- <td><input id="sdudob" /></td> -->
					<td><f:input path="sdudob" readonly="true" /></td>
					<td></td>
				</tr>
			</table>
			
			 <input type="submit" value="회원정보 수정"/>
	        <input type="button" value="취소" onclick="location.href='/semi/MAIN_main.do'">
   
		</f:form>

	</div>
</body>
</html>