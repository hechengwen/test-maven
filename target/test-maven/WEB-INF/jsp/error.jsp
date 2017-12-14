<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/31
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>error</title>
</head>
<body>

<h1>
    ${desc}
</h1>

<table>
    <div>
        ${exception}
    </div>

    <div>
        绝对路劲:${url}
    </div>

    <div>
        相对路劲:${contextUrl}
    </div>
</table>


</body>
</html>
