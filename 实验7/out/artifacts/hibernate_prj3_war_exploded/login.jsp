<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/11/22
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>登录</title>
</head>
<body>
<s:property value="#request.tip"/>
<s:form action="UserLogin" method="POST">
    <s:textfield name = "loginUser.account" label="请输入用户名"/>
    <s:password name="loginUser.password" label="请输入密码"/>
    <s:submit value = "登录"/>
</s:form>
<a href = "./register.jsp">注册</a>
</body>
</html>

