package Servlets;

import DB.DbManagerNews;
import Model.Comment;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CommentsServlet", value = "/CommentsServlet")
public class CommentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long newsId = Long.parseLong(request.getParameter("newsId"));
        request.setAttribute("news", DbManagerNews.getAllNews());
        request.setAttribute("comments", DbManagerNews.getCommentsByNewsId(newsId));
        request.getRequestDispatcher("/FirstPageServlet").forward(request, response);

//        Long newsId = Long.parseLong(request.getParameter("newsId"));
//        List<Comment> comments = DbManagerNews.getCommentsByNewsId(newsId);
//        String commentsJson = new Gson().toJson(comments);
//        PrintWriter out = response.getWriter();
//        out.println(commentsJson);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
