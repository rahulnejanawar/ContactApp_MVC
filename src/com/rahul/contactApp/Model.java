package com.rahul.contactApp;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.rahul.contactApp:
//            RegisterBean, JDBCHelper, LoginBean, ContactBean

public class Model
{

    public Model()
    {
    }

    public String register(RegisterBean bean)
        throws ClassNotFoundException, SQLException
    {
        String msg = bean.validate();
        Connection con = null;
        if(msg.equals("success"))
        {
            con = JDBCHelper.getConnection();
            if(con == null)
                return "Problem in Database Connection,Contact admin";
            PreparedStatement ps_sql = con.prepareStatement("select * from register where email=?");
            ps_sql.setString(1, bean.getEmail());
            ps_sql.execute();
            ResultSet rs = ps_sql.getResultSet();
            if(rs.next())
            {
                return "Email ID is already registered,Please choose new email id";
            } else
            {
                PreparedStatement ps_ins = con.prepareStatement("insert into register(name,email,pass) values(?,?,?)");
                ps_ins.setString(1, bean.getUname());
                ps_ins.setString(2, bean.getEmail());
                ps_ins.setString(3, bean.getPass());
                ps_ins.execute();
                return "success";
            }
        } else
        {
            return msg;
        }
    }

    public String authenticate(LoginBean bean)
        throws SQLException, ClassNotFoundException
    {
        String msg = bean.validate();
        Connection con = null;
        if(msg.equals("success"))
        {
            con = JDBCHelper.getConnection();
            if(con == null)
                return "DB Connection problem,contact admin";
            PreparedStatement ps_sql = con.prepareStatement("select * from register where email=? and pass=?");
            ps_sql.setString(1, bean.getEmail());
            ps_sql.setString(2, bean.getPass());
            ps_sql.execute();
            ResultSet rs = ps_sql.getResultSet();
            if(rs.next())
                return "success";
            else
                return "Invalid email or password";
        } else
        {
            return msg;
        }
    }

    public List fetchUsers()
        throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        con = JDBCHelper.getConnection();
        List li = new ArrayList();
        PreparedStatement ps_sql = con.prepareStatement("select * from register");
        ps_sql.execute();
        ResultSet rs = ps_sql.getResultSet();
        RegisterBean rb = null;
        for(; rs.next(); li.add(rb))
        {
            String name = rs.getString("name");
            String email = rs.getString("email");
            rb = new RegisterBean();
            rb.setUname(name);
            rb.setEmail(email);
        }

        return li;
    }

    public RegisterBean getUserDetail(String email)
        throws ClassNotFoundException, SQLException
    {
        Connection con = null;
        con = JDBCHelper.getConnection();
        PreparedStatement ps_sql = con.prepareStatement("select * from register where email=?");
        ps_sql.setString(1, email);
        ps_sql.execute();
        ResultSet rs = ps_sql.getResultSet();
        RegisterBean rb = null;
        if(rs.next())
        {
            String name = rs.getString("name");
            String emailid = rs.getString("email");
            String pass = rs.getString("pass");
            rb = new RegisterBean();
            rb.setUname(name);
            rb.setEmail(emailid);
            rb.setPass(pass);
        }
        return rb;
    }

    public String editAccount(RegisterBean bean)
        throws ClassNotFoundException, SQLException
    {
        String msg = bean.validate();
        Connection con = null;
        if(msg.equals("success"))
        {
            con = JDBCHelper.getConnection();
            if(con == null)
            {
                return "Problem in Database Connection,Contact admin";
            } else
            {
                PreparedStatement ps_sql = con.prepareStatement("update register set name=? , pass=? where email=?");
                ps_sql.setString(1, bean.getUname());
                ps_sql.setString(2, bean.getPass());
                ps_sql.setString(3, bean.getEmail());
                ps_sql.executeUpdate();
                ResultSet rs = ps_sql.getResultSet();
                return "success";
            }
        } else
        {
            return msg;
        }
    }

    public String addContact(ContactBean bean, String email)
        throws SQLException, ClassNotFoundException, ParseException
    {
        String msg = bean.validate();
        System.out.println((new StringBuilder("msg==")).append(msg).toString());
        Connection con = null;
        if(msg.equals("success"))
        {
            con = JDBCHelper.getConnection();
            if(con == null)
                return "Problem in Database Connection,Contact admin";
            PreparedStatement ps_sql = con.prepareStatement("select * from contacts where email=?");
            ps_sql.setString(1, bean.getEmail());
            ps_sql.execute();
            ResultSet rs = ps_sql.getResultSet();
            if(rs.next())
            {
                return "Email ID is already registered,Please choose new email id";
            } else
            {
                PreparedStatement ps_ins = con.prepareStatement("insert into contacts(reg_sl,name,email,phone,dob,tags) values((select sl_no from register where email=?),?,?,?,?,?)");
                ps_ins.setString(1, email);
                ps_ins.setString(2, bean.getName());
                ps_ins.setString(3, bean.getEmail());
                ps_ins.setString(4, bean.getPhone());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date dt = sdf.parse(bean.getDob());
                ps_ins.setDate(5, new Date(dt.getTime()));
                ps_ins.setString(6, bean.getTags());
                ps_ins.execute();
                return "success";
            }
        } else
        {
            return msg;
        }
    }

    public List listContact(String emailid)
        throws ClassNotFoundException, SQLException
    {
        Connection con = null;
        con = JDBCHelper.getConnection();
        List li = new ArrayList();
        System.out.println((new StringBuilder("emailid")).append(emailid).toString());
        PreparedStatement ps_sql = con.prepareStatement("select * from contacts where reg_sl=(select sl_no from register where email=?)");
        ps_sql.setString(1, emailid);
        ps_sql.execute();
        ResultSet rs = ps_sql.getResultSet();
        ContactBean rb = null;
        for(; rs.next(); li.add(rb))
        {
            String name = rs.getString("name");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            String dob = rs.getString("dob");
            String tags = rs.getString("tags");
            rb = new ContactBean();
            rb.setName(name);
            rb.setEmail(email);
            rb.setPhone(phone);
            rb.setDob(dob);
            rb.setTags(tags);
        }

        return li;
    }

    public ContactBean getContactDetail(String email)
        throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        con = JDBCHelper.getConnection();
        PreparedStatement ps_sql = con.prepareStatement("select * from contacts where email=?");
        ps_sql.setString(1, email);
        ps_sql.execute();
        ResultSet rs = ps_sql.getResultSet();
        ContactBean rb = null;
        if(rs.next())
        {
            String name = rs.getString("name");
            String emailid = rs.getString("email");
            String phone = rs.getString("phone");
            String dob = rs.getString("dob");
            String tags = rs.getString("tags");
            rb = new ContactBean();
            rb.setName(name);
            rb.setEmail(emailid);
            rb.setPhone(phone);
            rb.setDob(dob);
            rb.setTags(tags);
        }
        return rb;
    }

    public String updateContact(ContactBean bean)
        throws SQLException, ClassNotFoundException, ParseException
    {
        String msg = bean.validate();
        Connection con = null;
        if(msg.equals("success"))
        {
            con = JDBCHelper.getConnection();
            if(con == null)
            {
                return "Problem in Database Connection,Contact admin";
            } else
            {
                PreparedStatement ps_sql = con.prepareStatement("update contacts set name=? , phone=? ,dob=?,tags=? where email=?");
                ps_sql.setString(1, bean.getName());
                ps_sql.setString(2, bean.getPhone());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date dt = sdf.parse(bean.getDob());
                ps_sql.setDate(3, new Date(dt.getTime()));
                ps_sql.setString(4, bean.getTags());
                ps_sql.setString(5, bean.getEmail());
                ps_sql.executeUpdate();
                ResultSet rs = ps_sql.getResultSet();
                return "success";
            }
        } else
        {
            return msg;
        }
    }

    public String deleteContact(String editemail)
        throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        con = JDBCHelper.getConnection();
        if(con == null)
        {
            return "Problem in Database Connection,Contact admin";
        } else
        {
            PreparedStatement ps_sql = con.prepareStatement("delete from contacts  where email=?");
            ps_sql.setString(1, editemail);
            ps_sql.executeUpdate();
            ResultSet rs = ps_sql.getResultSet();
            return "success";
        }
    }
}