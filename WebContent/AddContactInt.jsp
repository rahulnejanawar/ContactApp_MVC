<jsp:useBean id="con" class="com.rahul.contactApp.ContactBean" scope="request">
<jsp:setProperty name="con" property="*"/>
</jsp:useBean>
<jsp:forward page="openAddContact.do"/>