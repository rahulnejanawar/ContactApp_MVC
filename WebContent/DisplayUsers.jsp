<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII" import="java.util.*,com.rahul.contactApp.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
List<RegisterBean> list=(List<RegisterBean>) request.getAttribute("userlist");
out.print("<table width=50% border=1>");  
out.print("<caption>Result:</caption>"); 
out.print("<tr>");
out.print("<th>Name</th>");
out.print("<th>Email</th>");
out.print("</tr>");
for(RegisterBean rb:list){
	out.print("<tr><td>"+rb.getUname()+"</td><td>"+rb.getEmail()+"</td></tr>"); 
}
out.print("</table>"); 

%>
</body>
</html>