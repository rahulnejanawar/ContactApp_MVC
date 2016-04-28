<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII" import="java.util.*,com.rahul.contactApp.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>

<h1>BirthDay Remainder</h1>
<br>

</div>
<%
List<ContactBean> list=(List<ContactBean>) request.getAttribute("contactlist");
out.print("<table width=50% border=1>");  
out.print("<caption>Here is the Contact list:</caption>"); 
out.print("<tr>");
out.print("<th>Name</th>");
out.print("<th>Email</th>");
out.print("<th>Phone</th>");
out.print("<th>DOB</th>");
out.print("<th>Tags</th>");
out.print("</tr>");
for(ContactBean rb:list){
	out.print("<tr><td>"+rb.getName()+"</td><td>"+rb.getEmail()+"</td><td>"+rb.getPhone()+"</td><td>"+rb.getDob()+"</td><td>"+rb.getTags()+"</td></tr>"); 
}
out.print("</table>"); 

%>
<div>
<br>
<a href="Menu.jsp">Click here to go back Menu</a><br>
<a href="index.html">Click here to go back HomePage</a><br>
${errorMsg}
</div>
</body>
</html>