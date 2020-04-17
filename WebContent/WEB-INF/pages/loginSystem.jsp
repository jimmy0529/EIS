<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>登入</title>
</head>
<h3>登入</h3>
<form action="<c:url value="/checkLogin.controller" />" method="post">
	<table>
		<tr>
			<td>帳號:</td>
			<td><input type="text" name="username"></td>
			<td>${errors.name}</td>
		</tr>
		<tr>
			<td>密碼:</td>
			<td><input type="password" name="userpassword"></td>
			<td>${errors.pwd}</td>
		</tr>
		<tr>
			<td><input type="submit"></td>
			<td>${errors.msg}</td>
		</tr>
	</table>
</form>
</html>