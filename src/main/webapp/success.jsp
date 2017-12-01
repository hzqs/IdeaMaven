<%--
  Created by IntelliJ IDEA.
  User: hzq
  Date: 2017/11/3
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>文件上传成功</h2>
成功上传文件${filename}
<input type="image" src="${pageContext.request.contextPath}/images/${filename}" width="200" height="30">
<a href="${pageContext.request.contextPath}/userAction/downloadFile?fileName=${filename}">下载</a>
</body>
</html>

