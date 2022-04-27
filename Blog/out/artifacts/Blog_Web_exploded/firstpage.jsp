<%@ page import="Model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.News" %>
<%@ page import="DB.DbManagerNews" %>
<%@ page import="Model.Comment" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 28.01.2022
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="/index.js"></script>
    <title>Title</title>
</head>
<body>
<div class="container-fluid d-flex bg-black p-0 m-0 col-12  ">
    <%
    User currentUser= (User) session.getAttribute("current user");
    %>
    <div class="col-1 border-white m-3">
        <div class="d-flex align-items-start">
            <div class="nav flex-column nav-pills me-3 text-white"  aria-orientation="vertical">
                <div> <img src="https://i.pinimg.com/originals/c4/36/c8/c436c8f923cd7c538c28038e3f88b1e4.jpg" alt="" class="rounded-circle m-2" width="50px" height="50px">
                    <%=currentUser.getFullname()%>
                </div>
                <button class="nav-link active mb-1">Profile</button>
                <a href="/myNews.jsp" class="btn btn-primary mb-1">My News</a>
                <a href="/FirstPageServlet" class="btn btn-primary mb-1">All News</a>
                <button class=" btn btn-primary mb-1" data-bs-toggle="modal" data-bs-target="#exampleModal" type="button">Add News</button>

            </div>

        </div>
    </div>
    <div class="col-8 border border-1 border-top-0 border border-secondary p-3">
<%
    ArrayList<News> newslist= (ArrayList<News>) session.getAttribute("news");
    for(News news:newslist){
%>

        <div class="list-group-item list-group-item-action active m-2 bg-warning text-dark">
            <h5 class="mb-1 text-dark"><%=news.getTitle()%></h5>
            <p class="mb-1"><%=news.getContent()%></p>
            <small>Author: <a href="#" style="text-decoration: none" class=""><%=DbManagerNews.userNameByNewsId(news.getId())%></a></small>
            <a href="/CommentsServlet?newsId=<%=news.getId()%>"style="text-decoration: none" class="text-dark">Комментарии</a>
<%--            <a id="getCommentsButton" href="javascript:void(0)" onclick="getAllCommentsByNewsId(<%=news.getId()%>)">Комментарии</a>--%>

            <a href="/DetailsNewsServlet?newsId=<%=news.getId()%>"style="text-decoration: none" class="text-dark">Открыть</a>
            <%
                if(news.getUserId()==currentUser.getId()){
            %>
            <a href="/DeleteNewsServlet?newsId=<%=news.getId()%>"style="text-decoration: none" class="text-dark">Удалить</a>
            <%
                }
            %>
            <div >
                <form action="/AddCommentsServlet?blogId=<%=news.getId()%>" method="post" class="d-flex">
                    <input type="text" name="com" class="form-control transparent-input bg-warning  border-0 shadow-none" placeholder ="Написать комментарий...">
                    <button type="submit" class="btn btn-dark" >Send</button>
                </form>
            </div>
            <%
            if (request.getAttribute("comments") != null) {
                ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");
                for (Comment comment : comments) {
            %>

                 <%
                    if (news.getId() == comment.getNewsId()) {
            %>
            <div class="bg-warning border border-secondary border-1 p-1 mb-1 d-flex justify-content-between">
                <div class="d-flex">
                    <a href="#" style="text-decoration: none"><%=DbManagerNews.userNameByCommentsId(comment.getId())%>:</a>
                    <p class="m-0 p-0 form-control bg-warning border-0 shadow-none"> <%=comment.getComment()%></p>
                </div>
                <%
                    if(comment.getUserId()==currentUser.getId()){
                %>
                <div class="d-flex">
                    <a href="/EditCommentsServlet?comId=<%=comment.getId()%>"style="text-decoration: none" class="text-white btn btn-dark form-control p-0 ms-1">Редактировать</a>
                    <a href="/DeleteCommentsServlet?comId=<%=comment.getId()%>"style="text-decoration: none" class="text-white btn btn-dark form-control p-0 ms-1">Удалить</a>
                </div>
                <%
                    }
                %>
            </div>
            <%
                        }
                    }
                }
            %>
        </div>
        <%
            }
        %>
    </div>
    <div class="m-3">
        <form class="d-flex">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
    </div>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">New Blog</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="/AddBlogServlet" method="post">
                    <div class="mb-3">
                        <label for="recipient-name" class="col-form-label">Title:</label>
                        <input type="text" class="form-control" id="recipient-name" name="title">
                    </div>
                    <div class="mb-3">
                        <label for="message-text" class="col-form-label">Message:</label>
                        <textarea class="form-control" id="message-text" name="content"></textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Add News</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
