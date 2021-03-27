<%--
  Created by IntelliJ IDEA.
  User: 昌
  Date: 2021/1/20
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <base href="<%=basePath%>>"> <%--加了base标签之后，页面中的所有相对路径全部失效,所以全部要写成绝对路径--%>
    <title>Title</title>
</head>
<body>

</body>
</html>
