<jsp:useBean id="edit" class="com.rahul.contactApp.RegisterBean" scope="request">

<jsp:setProperty name="edit" property="*"/>

</jsp:useBean>
<jsp:forward page="editAccount.do"></jsp:forward>