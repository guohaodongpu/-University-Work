<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>">
		</head>
	<body style="text-align: center">
		<form action="login.action" method="post" id="loginForm">
		    <s:actionmessage/>
		    <p>
			<label for="username">�û���:</label>
			<input type="text" name="user.username" id="username"/>
			<span id="usernamespan"></span>
			</p>
			<p>
			<label for="password">��      ��:</label>
			<input type="password" name="user.password" id="password"/>
			<span id="passwordspan"></span>
			</p>
			<input type="submit" value="�ύ" /><input type="reset" value="����" />
		</form>
	</body>
</html>
