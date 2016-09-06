<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="localhost.googlemaps.directions.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DISPLAY DIRECTIONS</title>
</head>
<body>
	<%
		ArrayList<String> list = Request.getDirections();
		for (int i = 0; i < list.size(); i++) {
	%>

	<p><%=list.get(i)%></p>
	<%
		}
	%>

</body>
</html>