<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="AddContactInt.jsp" method="post">
Enter Name:<input type="text" name="name"><br>
Enter Email:<input type="text" name="email"><br>
Enter Phone Number:<input type="text" name="phone"><br>
Enter Date of Birth:<input type="text" name="dob">dd/MM/yyyy<br>
Enter Tags:<input type="text" name="tags"><br>
<input type="submit" value="Add Contact">
</form>
<b>${errorMsg}</b>
</body>
</html>