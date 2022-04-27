package Servlets;

import DB.DbManagerNews;
import Model.News;
import Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddBlogServlet", value = "/AddBlogServlet")
public class AddBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("current user");
        DbManagerNews.addNews(new News(null, title, content, currentUser.getId()));
        response.sendRedirect("/FirstPageServlet");
    }
}
