<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Success</title>
</head>
<body>
<h1>登入成功</h1>
<h3>你的個人資訊</h3>
<label>員工編號: </label>${usersResultMap.EmployeeID}<br>
<label>帳號: </label>${usersResultMap.UserName}<br>
<label>密碼: </label>${usersResultMap.UserPassword}<br>
<label>職稱: </label>${usersResultMap.Title}<br>
<label>部門: </label>${usersResultMap.Department}<br>
<table>
	<tr>
		<td><input type="button" value="打卡" onclick="location.href='/EIS/PunchPage'"></td>
		<td><input type="button" value="出勤查詢" onclick="location.href='/EIS/InquiryPage'"></td>
		<td><input type="button" value="設定行事曆" onclick="location.href='/EIS/InqueryCalendar'"></td>
	</tr>
</table>
<a href="/EIS/logout/toLoginPage">LOGOUT</a>

</body>
</html>