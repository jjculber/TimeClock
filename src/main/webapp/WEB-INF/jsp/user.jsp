<!doctype html>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <meta charset="utf-8">
    <title>TimeClock Mobile</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>

<style>
.btn {
	background-color:#aaa;
	border:0px;
	width: 100%;
	max-width: 400px;
	height: 80px;
}
</style>

<body>
<form action="/users/${userId}/clock" method="get"><input type="submit" class="btn" value="Clock in/out"/></form>
<br/>
Status: ${status}<br/>
Time off: 
<jsp:useBean id="dateValue" class="java.util.Date" />
<jsp:setProperty name="dateValue" property="time" value="${estTimeOff}" />
<fmt:formatDate value="${dateValue}" pattern="h:mm a" timeZone="US/Pacific" />
<br />
Worked so far:
${fn:substringBefore(minutesWorkedToday / 60, '.')} hours
${minutesWorkedToday % 60} minutes
<br />

</body>
</html>
