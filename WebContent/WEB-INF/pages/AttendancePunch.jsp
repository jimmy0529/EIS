<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.eis.model.Attendance, java.util.*"%>
<html>
<head>
<title>打卡</title>
<script type="text/javascript">
	function showTime() {
		var today = new Date();
		var hour = parseInt(today.getHours()) < 10 ? '0' + today.getHours()
				: today.getHours();

		var min = parseInt(today.getMinutes()) < 10 ? '0' + today.getMinutes()
				: today.getMinutes();

		var sec = parseInt(today.getSeconds()) < 10 ? '0' + today.getSeconds()
				: today.getSeconds();

		document.getElementById('nowtime').innerHTML = hour + ':' + min + ':'
				+ sec
	}
	window.onload = function() {
		setInterval(showTime, 1000);
		var today = new Date();
		document.getElementById('nowdate').textContent = today.getFullYear()
				+ '/' + (today.getMonth() + 1) + '/' + today.getDate() + '/'
				+ '星期' + today.getDay()
	}
</script>
</head>
<body>
	<h3>打卡</h3>
	<h2>
		日期：<span id="nowdate"></span>
	</h2>
	<h2>
		時間：<span id="nowtime"></span>
	</h2>
	<form action="<c:url value="/PunchAction" />" method="post">
		<input type="submit" value="打卡" />
	</form>
	<table width="500" border="1">
		<tr>
			<td><b>日期</b></td>
			<td><b>上班時間</b></td>
			<td><b>下班時間</b></td>
			<td><b>狀態</b></td>
			<td><b>假別</b></td>
		</tr>
		<%
			List<Attendance> myPunch = (List<Attendance>) request.getAttribute("myPunch");
			if (myPunch == null || myPunch.size() < 1) {
		%>
		<tr id="test">
			<td align="center" colspan="5">沒有資料!</td>
		</tr>
		<%
			} else {
				for (Attendance mypunch : myPunch) {
		%>

		<tr align="center">
			<td><%=mypunch.getDate()%></td>
			<td><%=mypunch.getStartTime()%></td>
			<td><%=mypunch.getEndTime()%></td>
			<td><%=mypunch.getStatus()%></td>
			<td><%=mypunch.getLeaveType()%></td>
		</tr>
		<%
			}
			}
		%>
	</table>
	<h2>${punchMsg}</h2>
</body>
</html>