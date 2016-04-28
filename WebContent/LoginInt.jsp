<jsp:useBean id="log" class="com.rahul.contactApp.LoginBean" scope="request">
<jsp:setProperty name="log" property="*"/>

</jsp:useBean>
<jsp:forward page="login.do" />

