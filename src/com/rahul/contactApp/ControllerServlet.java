package com.rahul.contactApp;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

// Referenced classes of package com.rahul.contactApp:
//            Model, RegisterBean, LoginBean, ContactBean

public class ControllerServlet extends HttpServlet
{

    public ControllerServlet()
    {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        try
        {
            process(request, response);
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        try
        {
            process(request, response);
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException
    {
        String uri = request.getRequestURI();
        Model model = new Model();
        RequestDispatcher rd = null;
        if(uri.contains("/openRegisterView"))
        {
            rd = request.getRequestDispatcher("Register.jsp");
            rd.forward(request, response);
        }
        if(uri.contains("/openLoginView"))
        {
            rd = request.getRequestDispatcher("Login.jsp");
            rd.forward(request, response);
        }
        if(uri.contains("/register"))
        {
            RegisterBean bean = (RegisterBean)request.getAttribute("reg");
            System.out.println((new StringBuilder("bean=")).append(bean).toString());
            String result = model.register(bean);
            if(result.equals("success"))
            {
                request.setAttribute("msg", "Your registration is Successfull <a href='index.html'>Click here to goBack Homepage</a>");
                rd = request.getRequestDispatcher("Success.jsp");
                rd.forward(request, response);
            } else
            {
                request.setAttribute("errorMsg", result);
                rd = request.getRequestDispatcher("Register.jsp");
                rd.forward(request, response);
            }
        }
        if(uri.contains("/login"))
        {
            LoginBean bean = (LoginBean)request.getAttribute("log");
            System.out.println((new StringBuilder("login bean=")).append(bean).toString());
            String result = model.authenticate(bean);
            if(result.equals("success"))
            {
                HttpSession session = request.getSession();
                session.setAttribute("user", bean.getEmail());
                rd = request.getRequestDispatcher("Menu.jsp");
                rd.forward(request, response);
            } else
            {
                request.setAttribute("errorMsg", result);
                rd = request.getRequestDispatcher("Login.jsp");
                rd.forward(request, response);
            }
        }
        if(uri.contains("/logout"))
        {
            HttpSession session = request.getSession(false);
            if(session != null)
            {
                session.removeAttribute("user");
                session.invalidate();
            }
            rd = request.getRequestDispatcher("Success.jsp");
            request.setAttribute("msg", "You Succesfully loggedout..<a href='index.html'>Click here to go back HomePage</a>");
            rd.forward(request, response);
        }
        if(uri.contains("/openUserList"))
        {
            List li = new ArrayList();
            li = model.fetchUsers();
            request.setAttribute("userlist", li);
            rd = request.getRequestDispatcher("DisplayUsers.jsp");
            rd.forward(request, response);
        }
        if(uri.contains("/openEditAccount"))
        {
            HttpSession session = request.getSession(false);
            if(session == null || session.getAttribute("user") == null)
            {
                request.setAttribute("errorMsg", "First login, then try to edit account!");
                rd = request.getRequestDispatcher("Error.jsp");
                rd.forward(request, response);
            } else
            {
                String email = (String)session.getAttribute("user");
                RegisterBean bean = model.getUserDetail(email);
                System.out.print((new StringBuilder("user bean=")).append(bean).toString());
                request.setAttribute("userDetail", bean);
                rd = request.getRequestDispatcher("EditAccount.jsp");
                rd.forward(request, response);
            }
        }
        if(uri.contains("/editAccount"))
        {
            HttpSession session = request.getSession(false);
            if(session == null || session.getAttribute("user") == null)
            {
                request.setAttribute("errorMsg", "First login, then try to edit account!");
                rd = request.getRequestDispatcher("Error.jsp");
                rd.forward(request, response);
            } else
            {
                RegisterBean bean = (RegisterBean)request.getAttribute("edit");
                System.out.println((new StringBuilder("bean=")).append(bean).toString());
                String result = model.editAccount(bean);
                if(result.equals("success"))
                {
                    request.setAttribute("msg", "Your account is upadated Successfull <a href='index.html'>Click here to goBack Homepage</a>");
                    rd = request.getRequestDispatcher("Success.jsp");
                    rd.forward(request, response);
                } else
                {
                    request.setAttribute("errorMsg", result);
                    request.setAttribute("userDetail", bean);
                    rd = request.getRequestDispatcher("EditAccount.jsp");
                    rd.forward(request, response);
                }
            }
        }
        if(uri.contains("/openAddContactView"))
        {
            rd = request.getRequestDispatcher("AddContact.jsp");
            rd.forward(request, response);
        }
        if(uri.contains("/openAddContact"))
        {
            HttpSession session = request.getSession(false);
            if(session == null || session.getAttribute("user") == null)
            {
                request.setAttribute("errorMsg", "First login, then try to edit account!");
                rd = request.getRequestDispatcher("Error.jsp");
                rd.forward(request, response);
            } else
            {
                String email = (String)session.getAttribute("user");
                ContactBean bean = (ContactBean)request.getAttribute("con");
                System.out.println((new StringBuilder("bean add contact=")).append(bean).toString());
                String result = model.addContact(bean, email);
                if(result.equals("success"))
                {
                    request.setAttribute("msg", "Contact added successfully <a href='Menu.jsp'>Go back to Menu</a>");
                    rd = request.getRequestDispatcher("Success.jsp");
                    rd.forward(request, response);
                } else
                {
                    request.setAttribute("errorMsg", result);
                    rd = request.getRequestDispatcher("AddContact.jsp");
                    rd.forward(request, response);
                }
            }
        }
        if(uri.contains("/openListContactView"))
        {
            HttpSession session = request.getSession(false);
            if(session == null || session.getAttribute("user") == null)
            {
                request.setAttribute("errorMsg", "First login, then try to edit account!");
                rd = request.getRequestDispatcher("Error.jsp");
                rd.forward(request, response);
            } else
            {
                String email = (String)session.getAttribute("user");
                List li = new ArrayList();
                li = model.listContact(email);
                request.setAttribute("contactlist", li);
                rd = request.getRequestDispatcher("ListContact.jsp");
                rd.forward(request, response);
            }
        }
        if(uri.contains("/openSearchView"))
        {
            HttpSession session = request.getSession(false);
            if(session == null || session.getAttribute("user") == null)
            {
                request.setAttribute("errorMsg", "First login, then try to edit account!");
                rd = request.getRequestDispatcher("Error.jsp");
                rd.forward(request, response);
            } else
            {
                String email = (String)session.getAttribute("user");
                List li = new ArrayList();
                li = model.listContact(email);
                request.setAttribute("contactlist", li);
                rd = request.getRequestDispatcher("SearchContact.jsp");
                rd.forward(request, response);
            }
        }
        if(uri.contains("/searchResults"))
        {
            HttpSession session = request.getSession(false);
            if(session == null || session.getAttribute("user") == null)
            {
                request.setAttribute("errorMsg", "First login, then try to edit account!");
                rd = request.getRequestDispatcher("Error.jsp");
                rd.forward(request, response);
            } else
            {
                String email = (String)session.getAttribute("user");
                String searchkey = request.getParameter("search");
                System.out.println((new StringBuilder("search key")).append(searchkey).toString());
                List li = new ArrayList();
                li = model.listContact(email);
                List list = new ArrayList();
                System.out.println((new StringBuilder("lsit")).append(li).toString());
                for(Iterator iterator = li.iterator(); iterator.hasNext();)
                {
                    ContactBean cb = (ContactBean)iterator.next();
                    if(cb.getName().contains(searchkey))
                        list.add(cb);
                }

                if(list.size() >= 1)
                {
                    request.setAttribute("contactlist", list);
                    rd = request.getRequestDispatcher("SearchContact.jsp");
                    rd.forward(request, response);
                } else
                {
                    request.setAttribute("contactlist", list);
                    request.setAttribute("errorMsg", "The Search contact does not exist");
                    rd = request.getRequestDispatcher("SearchContact.jsp");
                    rd.forward(request, response);
                }
            }
        }
        if(uri.contains("/openEditContactView"))
        {
            HttpSession session = request.getSession(false);
            if(session == null || session.getAttribute("user") == null)
            {
                request.setAttribute("errorMsg", "First login, then try to edit account!");
                rd = request.getRequestDispatcher("Error.jsp");
                rd.forward(request, response);
            } else
            {
                String email = (String)session.getAttribute("user");
                List li = new ArrayList();
                li = model.listContact(email);
                request.setAttribute("contactlist", li);
                rd = request.getRequestDispatcher("EditContact.jsp");
                rd.forward(request, response);
            }
        }
        if(uri.contains("/openUpdateContact"))
        {
            HttpSession session = request.getSession(false);
            if(session == null || session.getAttribute("user") == null)
            {
                request.setAttribute("errorMsg", "First login, then try to edit account!");
                rd = request.getRequestDispatcher("Error.jsp");
                rd.forward(request, response);
            } else
            {
                String email = (String)session.getAttribute("user");
                String editemail = request.getParameter("email");
                ContactBean bean = model.getContactDetail(editemail);
                System.out.print((new StringBuilder("user bean=")).append(bean).toString());
                request.setAttribute("contactDetail", bean);
                rd = request.getRequestDispatcher("UpdateContact.jsp");
                rd.forward(request, response);
            }
        }
        if(uri.contains("/updateContact"))
        {
            HttpSession session = request.getSession(false);
            if(session == null || session.getAttribute("user") == null)
            {
                request.setAttribute("errorMsg", "First login, then try to edit account!");
                rd = request.getRequestDispatcher("Error.jsp");
                rd.forward(request, response);
            } else
            {
                ContactBean bean = (ContactBean)request.getAttribute("upcon");
                System.out.println((new StringBuilder("bean=")).append(bean).toString());
                String result = model.updateContact(bean);
                if(result.equals("success"))
                {
                    request.setAttribute("msg", "Your contact details are upadated Successfull <a href='index.html'>Click here to goBack Homepage</a>");
                    rd = request.getRequestDispatcher("Success.jsp");
                    rd.forward(request, response);
                } else
                {
                    request.setAttribute("errorMsg", result);
                    request.setAttribute("contactDetail", bean);
                    rd = request.getRequestDispatcher("UpdateContact.jsp");
                    rd.forward(request, response);
                }
            }
        }
        if(uri.contains("/searchContacts"))
        {
            HttpSession session = request.getSession(false);
            if(session == null || session.getAttribute("user") == null)
            {
                request.setAttribute("errorMsg", "First login, then try to edit account!");
                rd = request.getRequestDispatcher("Error.jsp");
                rd.forward(request, response);
            } else
            {
                String email = (String)session.getAttribute("user");
                String searchkey = request.getParameter("search");
                System.out.println((new StringBuilder("search key")).append(searchkey).toString());
                List li = new ArrayList();
                li = model.listContact(email);
                List list = new ArrayList();
                System.out.println((new StringBuilder("lsit")).append(li).toString());
                for(Iterator iterator1 = li.iterator(); iterator1.hasNext();)
                {
                    ContactBean cb = (ContactBean)iterator1.next();
                    if(cb.getName().contains(searchkey))
                        list.add(cb);
                }

                if(list.size() >= 1)
                {
                    request.setAttribute("contactlist", list);
                    rd = request.getRequestDispatcher("EditContact.jsp");
                    rd.forward(request, response);
                } else
                {
                    request.setAttribute("contactlist", list);
                    request.setAttribute("errorMsg", "The Search contact does not exist");
                    rd = request.getRequestDispatcher("EditContact.jsp");
                    rd.forward(request, response);
                }
            }
        }
        if(uri.contains("/deleteContact"))
        {
            HttpSession session = request.getSession(false);
            if(session == null || session.getAttribute("user") == null)
            {
                request.setAttribute("errorMsg", "First login, then try to edit account!");
                rd = request.getRequestDispatcher("Error.jsp");
                rd.forward(request, response);
            } else
            {
                String email = (String)session.getAttribute("user");
                String editemail = request.getParameter("email");
                String result = model.deleteContact(editemail);
                if(result.equals("success"))
                {
                    request.setAttribute("msg", "Your contact deleted Successfull <a href='index.html'>Click here to goBack Homepage</a>");
                    rd = request.getRequestDispatcher("Success.jsp");
                    rd.forward(request, response);
                } else
                {
                    request.setAttribute("errorMsg", result);
                    rd = request.getRequestDispatcher("Error.jsp");
                    rd.forward(request, response);
                }
            }
        }
        if(uri.contains("/openBdayRemainderView"))
        {
            HttpSession session = request.getSession(false);
            if(session == null || session.getAttribute("user") == null)
            {
                request.setAttribute("errorMsg", "First login, then try to edit account!");
                rd = request.getRequestDispatcher("Error.jsp");
                rd.forward(request, response);
            } else
            {
                String email = (String)session.getAttribute("user");
                List li = new ArrayList();
                li = model.listContact(email);
                Collections.sort(li);
                request.setAttribute("contactlist", li);
                rd = request.getRequestDispatcher("BirthdayRemainder.jsp");
                rd.forward(request, response);
            }
        }
    }

    private static final long serialVersionUID = 1L;
}