<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <title>登陆页面</title>
</head>
<body>
<!--action的值是提交到哪个页面，method指定提交数据的方式，常用的有get和post-->
<form action="/login/index" method="post">
    <!--type的值可以为text、password、checkbox、radio、image、hidden、submit、reset-->
    用户名：<input type="text" name="loginName"/><br/>
    密&nbsp&nbsp码：<input type="password" name="password"/><br/>
    <input type="submit" value="登录系统"/>
    <input type="reset" value="重置"/>
</form>
</body>
</html>