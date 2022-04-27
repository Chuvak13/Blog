<%@ page import="Model.User" %>
<%@ page import="Model.News" %>
<%@ page import="DB.DbManagerNews" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 02.02.2022
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <title>Title</title>
</head>
<body>
<div class="container-fluid d-flex  col-4 m-auto mt-3  ">
    <%
        User currentUser= (User) session.getAttribute("current user");
        News news= (News)session.getAttribute("news");
    %>

    <div class="bg-secondary  list-group-item list-group-item-action active ">
        <h5 class="mb-1 text-dark"><%=news.getTitle()%></h5>
        <p class="mb-1"><%=news.getContent()%></p>
        <small>Author: <a href="#" style="text-decoration: none" class="text-white"><%=DbManagerNews.userNameByNewsId(news.getId())%></a></small>
        <%
            if(news.getUserId()==currentUser.getId()){
        %>
        <a href="/EditBlogServlet?newsId=<%=news.getId()%>"style="text-decoration: none" class="text-dark">Редактировать</a>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
