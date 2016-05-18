package com.rahul.contactApp;

import java.sql.*;

public class JDBCHelper
{

    public JDBCHelper()
    {
    }

    public static Connection getConnection()
        throws ClassNotFoundException
    {
        Connection con = null;
        try
        {
            Class.forName("org.hsqldb.jdbcDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/", "SA", "");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return con;
    }
}