package Servlets;

import DB.DbManagerNews;
import Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FirstPageServlet", value = "/FirstPageServlet")
public class FirstPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DbManagerNews.connectToDB();
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("current user");
        session.setAttribute("news",DbManagerNews.getAllNews());
        request.getRequestDispatcher("/firstpage.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
