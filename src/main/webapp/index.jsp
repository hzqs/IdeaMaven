<%--
  Created by IntelliJ IDEA.
  User: hzq
  Date: 2017/11/3
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>上传文件</h2>
<form method="post" action="${pageContext.request.contextPath}/userAction/uploadFile" enctype="multipart/form-data">
   UserName:<input name="uname"/><br/>
    UserImage:<input type="file" name="ufile"><br/>
    <input type="submit" value="上传文件">
</form>



</body>
</html>
