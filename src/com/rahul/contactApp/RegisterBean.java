package com.rahul.contactApp;


public class RegisterBean
{

    public RegisterBean()
    {
    }

    public String getUname()
    {
        return uname;
    }

    public String getRpass()
    {
        return rpass;
    }

    public void setRpass(String rpass)
    {
        this.rpass = rpass;
    }

    public void setUname(String uname)
    {
        this.uname = uname;
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
        return (new StringBuilder("RegisterBean [uname=")).append(uname).append(", email=").append(email).append(", pass=").append(pass).append(", rpass=").append(rpass).append("]").toString();
    }

    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        result = 31 * result + (rpass != null ? rpass.hashCode() : 0);
        result = 31 * result + (uname != null ? uname.hashCode() : 0);
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
        RegisterBean other = (RegisterBean)obj;
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
        if(rpass == null)
        {
            if(other.rpass != null)
                return false;
        } else
        if(!rpass.equals(other.rpass))
            return false;
        if(uname == null)
        {
            if(other.uname != null)
                return false;
        } else
        if(!uname.equals(other.uname))
            return false;
        return true;
    }

    public String validate()
    {
        StringBuilder sb = new StringBuilder();
        if(uname == null || uname.trim().equals(""))
            sb.append("Please Enter Name<br>");
        if(email == null || email.trim().equals(""))
            sb.append("Email is Mandatory,Please Enter Email<br>");
        if(pass == null || pass.trim().equals(""))
            sb.append("Password is Mandatory,Please Enter Password<br>");
        else
        if(!pass.equals(rpass))
            sb.append("Password and Reapeat Password are not same<br>");
        String msg = sb.toString();
        if(msg.equals(""))
            return "success";
        else
            return msg;
    }

    private String uname;
    private String email;
    private String pass;
    private String rpass;
}