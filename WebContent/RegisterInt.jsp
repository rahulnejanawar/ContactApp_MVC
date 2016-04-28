<jsp:useBean id="reg" class="com.rahul.contactApp.RegisterBean" scope="request">

<jsp:setProperty name="reg" property="*"/>

</jsp:useBean>
<jsp:forward page="register.do"></jsp:forward>