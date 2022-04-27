package Servlets;

import DB.DbManagerNews;
import Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteCommentsServlet", value = "/DeleteCommentsServlet")
public class DeleteCommentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("current user");
        Long id = Long.parseLong(request.getParameter("comId"));
        DbManagerNews.deleteComment(id);
        response.sendRedirect("/FirstPageServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
