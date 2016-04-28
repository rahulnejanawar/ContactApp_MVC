<jsp:useBean id="upcon" class="com.rahul.contactApp.ContactBean" scope="request">
<jsp:setProperty name="upcon" property="*"/>
</jsp:useBean>
<jsp:forward page="updateContact.do"/>