package Servlets;

import DB.DBMangerUsers;
import Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AuthorizationServlet", value = "/AuthorizationServlet")
public class AuthorizationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("emailR");
        Long password =Long.parseLong(request.getParameter("passwordR"));
        String fullname = request.getParameter("fullname");


        User user = new User(null,login,password,fullname);
        System.out.println(user);
        DBMangerUsers.connectToDB();
        DBMangerUsers.addUser(user);
        response.sendRedirect("/MainServlet");
    }
}
