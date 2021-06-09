<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="writeMod" method="post">
    <input type=""hidden name="iboard" value="0">
    <%--value에 0을 적는 이유: iboard값이 넘어갈때 int이기 때문에 아무것도 안적어주면 오류남 --%>
    <div><input type="text" name="title" placeholder="제목"></div>
    <div><textarea type="ctnt" placeholder="내용"></textarea></div>
    <div>
        <input type="submit" value="등록">
        <input type="reset" value="새로작성">
    </div>
</form>