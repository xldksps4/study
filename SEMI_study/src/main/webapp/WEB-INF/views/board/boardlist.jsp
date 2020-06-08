<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardList</title>


<!-- JQ -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- include bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->

<!-- include paging -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/board/boardList.js"></script>


<!-- include Summernote -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/summernote/js/summernote-lite.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/summernote/js/summernote-fileupload.js"></script>


<style type="text/css">
h1 {
	text-align: center;
}

table {
	width: 100%
}
</style>

</head>
<body>

	<!-- 작성된 글 리스트
	
	헤더 : 검색기능 
	
	바디 : 리스트 보여주기
	
	
	    
	 -->

	<div class="">

		<form action="BOARD_boardwrite.do" method="get">
			<table class="table-bordered">
				<col width="100" />
				<col width="300" />
				<col width="100" />
				<col width="100" />

				<thead>
					<!-- 검색창 영역 -->
					<!-- 		<tr> -->
					<!-- 			<td> -->
					<!-- 							<div class="search-label">검색</div> -->
					<!-- 						</td> -->
					<!-- 						<td colspan="3" class="search-form"> -->
					<!-- 							<div class="search-select"> -->
					<!-- 								<select class="form-control form-control-sm" name="search-category" -->
					<!-- 									id="search-category"> -->
					<!-- 									<option value="title">제목</option> -->
					<!-- 									<option value="content">본문</option> -->
					<!-- 									<option value="joinemail">작성자</option> -->
					<!-- 								</select> -->
					<!-- 							</div> -->
					<!-- 							<div class="search-input"> -->
					<!-- 								<input type="text" class="form-control form-control-sm" -->
					<!-- 									name="keyword" id="keyword" onkeydown="onKeyDown();"> -->
					<!-- 							</div> -->
					<!-- 							<div class="search-btn-group"> -->
					<!-- 								<button class="btn btn-sm btn-primary" -->
					<!-- 									id="search-btn" onclick="search();" >검 색</button> -->
					<!-- 							</div> -->
					<!-- 						</td> -->

					<!-- 		</tr> -->
					<!-- 검색창 영역 end -->

					<!-- 리스트 영역 -->
					<tr class="header-bar">
						<th>No.</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
				</thead>



				<!-- 함수 정리

for each : 반복문, subsetting및 다른 함수기능도 지원 			(org.apache.taglibs.standard.tag.rt.core.ForEachTag)
			   items : collection형태로만 값 받을 수 있음.	(Object)
		  begin, end : index 여기서부터~ 여기까지			(int)
				step : 특정 배수 형식으로 반복조건 부여 가능		(int)
				 var : 변수명, 형식은 컬렉션 개체에 따라 다름	(String)
		   varStatus : 반복 상태에 대해 내보낸 범위변수의 이름	(String)

if : 우리가 아는 그 if문. 조건이 참일 때..						(org.apache.taglibs.standard.tag.rt.core.IfTag)
//제공된 조건이 true 인 경우 본문을 제거하고 선택적으로이 조건의 평가를 나타내는 부울 스크립팅 변수를 노출하는 단순 조건부 태그?
				test : 본문 내용의 처리 여부를 결정하는 테스트 조건 (boolean)
				 var : 변수명, 변수 타입은 boolean			(String
			   scope : 스코프 변수명
			   
choose :  (org.apache.taglibs.standard.tag.common.core.ChooseTag)
//<when> 및 <otherwise>로 표시된 상호 배타적 인 조건부 작업에 대한 컨텍스트를 설정하는 간단한 조건부 태그?

	When : 모든 이전 조건이 'false'로 평가 된 경우에만 실행 		(org.apache.taglibs.standard.tag.common.core.OtherwiseTag)
//<choose>다음에 오는 태그
	otherwise : 

out : \꺽쇠%= 과 같은 역할, 출력
				  eq : ==
				  ne : !=
			   empty : null

-->


				<tbody>

					<c:choose>
						<c:when test="${empty boardDto }">
							<tr>
								<td colspan="4" id="boardlist-null">작성된 글이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${boardDto }" var="boardDto">
								<tr>
									<td class="board-seq"><fmt:formatNumber
											value="${boardDto.sdbseq }" pattern="000" /></td>
									<td class="board-title" onClick="boardDetail(${boardDto.sdbseq });">
									
									<!-- 답글 기능 필요 시  -->

									 ${boardDto.sdbtitle }</td>
									<td class="board-email">${boardDto.sduemail }</td>
									<td class="board-date">${boardDto.sdbregdate}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					<tr>
						<td colspan="4">
						<input type="submit" value="글쓰기" /> 
						<input type="button" value="버튼 글쓰기" onclick="location.href='BOARD_boardwrite.do'" />
						</td>
					</tr>
				</tbody>
			</table>
			<!-- 리스트 영역 end -->



			<!-- 페이징 영역 -->

			<!-- 	<div> -->
			<!-- 	[prev] -->
			<%-- 	<c:forEach var="i" begin="1" end="10" step="3">${i }<br/></c:forEach> --%>
			<!-- 	 [next] -->
			<!-- 	</div> -->
		</form>
	</div>
</body>
</html>