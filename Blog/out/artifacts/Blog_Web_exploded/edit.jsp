<%@ page import="Model.User" %>
<%@ page import="Model.News" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 02.02.2022
  Time: 15:49
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
<%
    User currentUser= (User) session.getAttribute("current user");
    News news= (News)session.getAttribute("news");
%>
<div class="container-fluid d-flex ">
    <div class="col-lg-6 m-auto mt-3">
    <form action="/EditBlogServlet?newsId=<%=news.getId()%>" method="post">
        <div class="mb-3">
            <label for="recipient-name" class="col-form-label">Title:</label>
            <input type="text" class="form-control" id="recipient-name" value="<%=news.getTitle()%>" name="title">
        </div>
        <div class="mb-3">
            <label for="message-text" class="col-form-label">Message:</label>
            <textarea class="form-control" id="message-text" style="height: 100px" name="content"><%=news.getContent()%></textarea>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-primary">Safe</button>
        </div>
    </form>
    </div>
</div>
</body>
</html>
