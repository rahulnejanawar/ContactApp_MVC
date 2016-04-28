<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact App</title>
</head>
<body>
<h1>Register Here</h1><br>
<form action="EditAccountInt.jsp" method="post">
Enter Name:<input type="text" name="uname" value='${userDetail.uname}'><br>
Enter Email:<input type="text" name="email" value="${userDetail.email}" readonly="readonly"><br>
Enter Password:<input type="password" name="pass" value="${userDetail.pass}"><br> 
Repeat Password:<input type="password" name="rpass" value="${userDetail.rpass}"><br>
<input type="submit" value="Register">
</form>
<b>${errorMsg}</b>
</body>
</html>