<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Reading Checkbox Data</h1> 
<ul> 
	<li><p><b>Maths Flag:</b> 
		<%= request.getParameter("maths")%> </p></li> 
	<li><p><b>Physics Flag:</b> 
		<%= request.getParameter("physics")%> </p></li> <li><p><b>Chemistry Flag:</b> 
		<%= request.getParameter("chemistry")%> </p></li> 
</ul>

</body>
</html>