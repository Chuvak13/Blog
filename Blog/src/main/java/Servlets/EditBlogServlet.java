package Servlets;

import DB.DbManagerNews;
import Model.News;
import Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditBlogServlet", value = "/EditBlogServlet")
public class EditBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("current user");
        Long id = Long.parseLong(request.getParameter("newsId"));
        session.setAttribute("news", DbManagerNews.getNewsById(id));
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Long id =Long.parseLong(request.getParameter("newsId"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
//        Long userId=Long.parseLong(request.getParameter("userId"));
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("current user");
        News news= new News(id,title,content, currentUser.getId());
        DbManagerNews.updateNews(news);
        response.sendRedirect("/DetailsNewsServlet?newsId=" + id);
    }
}
