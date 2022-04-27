package Servlets;

import DB.DbManagerNews;
import Model.Comment;
import Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddCommentsServlet", value = "/AddCommentsServlet")
public class AddCommentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long newsId = Long.parseLong(request.getParameter("blogId"));
        String com = request.getParameter("com");
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("current user");
        DbManagerNews.addComment(new Comment(null,com, currentUser.getId(), newsId));
        response.sendRedirect("/FirstPageServlet");
    }
}
