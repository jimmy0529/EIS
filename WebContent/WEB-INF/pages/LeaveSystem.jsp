<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>請假系統</title>
</head>
<h3>請假系統</h3>
	<table>
		<tr>
			<td>${employeeid}</td>
			<td><input type="button" value="打卡" onclick="javascript:location.href='/EIS/AttendancePunch'"></td>
			<td><input type="button" value="請假" onclick=""></td>
			<td><input type="button" value="出勤查詢" onclick="javascript:location.href='/EIS/AttendanceInquiry'"></td>
			<td><input type="button" value="簽核" onclick=""></td>
		</tr>
	</table>
</html>