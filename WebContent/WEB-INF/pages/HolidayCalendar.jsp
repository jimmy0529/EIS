<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.eis.model.HolidayCalendar, java.util.*"%>
<html>
<head>
<title>設定行事曆</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
<h3>設定行事曆</h3>
<form action="<c:url value='/InsertWeekend'/>" method="post">
設定年度周末 : <input type="text" name="year" autocomplete="off">
<input type="submit" value="送出" />
</form>
<hr/>
<form action="<c:url value='/InsertHoliday'/>" method="post">
設定國定假日 : <input type="text" id="pickHoliday" name="date" autocomplete="off"> 
<input type="text" name="remark" />
<input type="submit" value="送出" />
</form>
<hr/>
<form action="<c:url value='/UpdateWorkday'/>" method="post">
設定補班 : <input type="text" id="pickWorkday" name="date" autocomplete="off"> 
<input type="text" name="remark" />
<input type="submit" value="送出" />
</form>
<hr/>
<form action="<c:url value='/DeleteCalendar'/>" method="post">
<table width="500" border="1">
<tr>
	<td><b>日期</b></td>
	<td><b>種類</b></td>
	<td><b>備註</b></td>
	<td><b>新增人員ID</b></td>
	<td><b><input type="submit" value="移除"></b></td>
</tr>
<%
	List<HolidayCalendar> calenderlist = (List<HolidayCalendar>) request.getAttribute("calenderlist");
	if (calenderlist == null || calenderlist.size() < 1) {
%>
<tr id="test">
	<td align="center" colspan="5">沒有資料!</td>
</tr>
<%
	} else {
		for (HolidayCalendar cal : calenderlist) {
%>

<tr align="center">
	<td><%=cal.getDate()%></td>
	<td><%=cal.getDateType()%></td>
	<td><%=cal.getRemark()%></td>
	<td><%=cal.getId()%></td>
	<td><input type="checkbox" name="Date" value="<%=cal.getDate()%>"></td>
</tr>
<%
		}
	}
%>
</form>
</table>
</html>
</body>
<script>
$(document).ready(function(){
    $("#pickHoliday").datepicker();
    $("#pickWorkday").datepicker();
})
</script>