<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
</head>
<body>
    <div>
        <h1>로그인</h1>
    </div>
    <div id="wrap">
        <div class="title">Hello</div>
        <div class="errMsg">
            <p>${errMsg}</p>
        </div>
        <form action="login" method="post">
            <div>
                ID : <input type="text" name="uid" placeholder="아이디">
            </div>
            <div>
                PW : <input type="password" name="upw" placeholder="비밀번호">
            </div>
            <div>
                <span><input type="submit" value="Login"></span>
            </div>
        </form>
        <span><a href="/user/join"><button>Join</button></a></span>
    </div>
</body>
</html>
