package com.rahul.contactApp;

public class ContactBean
{

    public ContactBean()
    {
        System.out.println("in contact bean constructor");
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getDob()
    {
        return dob;
    }

    public void setDob(String dob)
    {
        this.dob = dob;
    }

    public String getTags()
    {
        return tags;
    }

    public void setTags(String tags)
    {
        this.tags = tags;
    }

    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        ContactBean other = (ContactBean)obj;
        if(dob == null)
        {
            if(other.dob != null)
                return false;
        } else
        if(!dob.equals(other.dob))
            return false;
        if(email == null)
        {
            if(other.email != null)
                return false;
        } else
        if(!email.equals(other.email))
            return false;
        if(name == null)
        {
            if(other.name != null)
                return false;
        } else
        if(!name.equals(other.name))
            return false;
        if(phone == null)
        {
            if(other.phone != null)
                return false;
        } else
        if(!phone.equals(other.phone))
            return false;
        if(tags == null)
        {
            if(other.tags != null)
                return false;
        } else
        if(!tags.equals(other.tags))
            return false;
        return true;
    }

    public String toString()
    {
        return (new StringBuilder("ContactBean [name=")).append(name).append(", email=").append(email).append(", phone=").append(phone).append(", dob=").append(dob).append(", tags=").append(tags).append("]").toString();
    }

    public String validate()
    {
        StringBuilder sb = new StringBuilder();
        if(name == null || name.trim().equals(""))
            sb.append("Name should not be blank,please enter name<br>");
        if(email == null || email.trim().equals(""))
            sb.append("Email is mandatory,enter email<br>");
        if(phone == null || phone.trim().equals(""))
            sb.append("Please enter Phone number<br>");
        if(dob == null || dob.trim().equals(""))
            sb.append("Please enter date of birth<br>");
        if(tags == null || tags.trim().equals(""))
            sb.append("Please enter tags<br>");
        String msg = sb.toString();
        if(msg.equals(""))
            return "success";
        else
            return msg;
    }

    private String name;
    private String email;
    private String phone;
    private String dob;
    private String tags;
}