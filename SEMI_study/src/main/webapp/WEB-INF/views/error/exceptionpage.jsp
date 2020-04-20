<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>버그 발생! 관리자에게 문의 하세요.</h1>
   <h2>에러 메세지 : </h2>
   <h3>${exception.getMessage()}</h3>
   
   <ul>
      <c:forEach items="${exception.getStackTrace()}" var="stack">
         <li>${stack.toString()}</li>
      </c:forEach>
   </ul>
   
</body>
</html>