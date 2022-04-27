package Servlets;

import DB.DBMangerUsers;
import Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "MainServlet", value = "/MainServlet")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("current user");
        if(currentUser!=null){
            request.getRequestDispatcher("/FirstPageServlet").forward(request,response);
        }else{
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email =request.getParameter("email");
        Long password =Long.parseLong(request.getParameter("password"));
        DBMangerUsers.connectToDB();
        User user = DBMangerUsers.getUserByEmail(email);
        System.out.println(user);


        if(user!=null){
            if(Objects.equals(user.getPassword(), password)){
                HttpSession session = request.getSession();
                session.setAttribute("current user",user);
                response.sendRedirect("/FirstPageServlet");

            }else{
                System.out.println(user.getPassword()+" "+password);

            }

        }else{
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }

    }
}
