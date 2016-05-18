package com.rahul.contactApp;


public class LoginBean
{

    public LoginBean()
    {
    }

    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
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
        LoginBean other = (LoginBean)obj;
        if(email == null)
        {
            if(other.email != null)
                return false;
        } else
        if(!email.equals(other.email))
            return false;
        if(pass == null)
        {
            if(other.pass != null)
                return false;
        } else
        if(!pass.equals(other.pass))
            return false;
        return true;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public String toString()
    {
        return (new StringBuilder("LoginBean [email=")).append(email).append(", pass=").append(pass).append("]").toString();
    }

    public String validate()
    {
        StringBuilder sb = new StringBuilder();
        if(email == null || email.trim().equals(""))
            sb.append("Email is Mandatory,Enter email id<br>");
        if(pass == null || pass.trim().equals(""))
            sb.append("Password is mandatory,enter passowrd<br>");
        String msg = sb.toString();
        if(msg.equals(""))
            return "success";
        else
            return msg;
    }

    private String email;
    private String pass;
}