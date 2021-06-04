<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>${requestScope.data.title}</title>
    <link rel="stylesheet" href="/res/css/boardList.css">
    <script defer src="/res/js/boardList.js"></script>
</head>
<body>
  <div><a href="#" onclick="goBack();">돌아가기</a></div>
  <h1>${requestScope.data.title}</h1>
  <div>글번호 : ${requestScope.data.iboard}</div>
  <div>작성자 : <c:out value="${requestScope.data.writerNm}"/> | 작성일 : ${requestScope.data.regdt}</div>
  <div><c:out value="${requestScope.data.ctnt}"/></div> <%-- c:out을 사용하는 이유/xss공격: 자바스크립트 공격을 하지 못하게 한다. --%>

    <c:if test="${not empty sessionScope.loginUser}"><%--테스트 안에있는 게 만약 true라면 실행하게끔 하는 식--%>
        <div>
            <form id="cmtFrm" onsubmit="return false;"> // onsumit대신 action="#"도 가능
                <input type="text" id="cmt">
                <input type="button" value="댓글달기" onclick="regCmt();">
            </form>
        </div>
    </c:if>
<div id="cmtList" data-login_user_pk="${sessionScope.loginUer.iuser}" data_iboard="${param.iboard}"> </div>

</body>
</html>
