<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <meta charset="utf-8">
    <title>TimeClock Mobile</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>

<body>
<form action="/users/${userId}/clock" method="get"><input type="submit" class="btn btn-danger btn-mini" value="Clock in/out"/></form>
<br/>
Status: ${status}<br/>
<c:set var="timeOff" value="<%= new java.util.Date(estTimeOff)%>"/>
Time off: <fmt:formatDate type="time" value="${timeOff}" /><br/>

</body>
</html>
