package DB;

import Model.Comment;
import Model.News;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DbManagerNews {
    private static Connection connection;

    public static void connectToDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blog?useUnicode=true&serverTimezone=UTC", "root", "");
            System.out.println("DB connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<News> getAllNews() {
        List<News> newsList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from news");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Long id = result.getLong("id");
                String title = result.getString("title");
                String content = result.getString("content");
                Long userId = result.getLong("userId");

                newsList.add(new News(id, title, content, userId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsList;
    }

    public static String userNameByNewsId(Long id) {
        String userName = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select a.fullname from users a join news b on b.userId = a.id where b.id = ?");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                userName = result.getString("fullname");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userName;

    }

    public static void addComment(Comment comment) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into comments values (null, ?,?,?)");
            statement.setString(1, comment.getComment());
            statement.setLong(2, comment.getUserId());
            statement.setLong(3, comment.getNewsId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Comment> getCommentsByNewsId(Long newsId) {
        List<Comment> comments = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from comments where newsId = ? order by id desc ");
            statement.setLong(1, newsId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Long id = result.getLong("id");
                String comment = result.getString("comment");
                Long userId = result.getLong("userId");
                comments.add(new Comment(id, comment, userId, newsId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }
    public static String userNameByCommentsId(Long id) {
        String userNameC = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select a.fullname from users a join comments b on b.userId = a.id where b.id = ?");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                userNameC = result.getString("fullname");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userNameC;
    }

    public static void addNews(News news) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into news values (null, ?,?,?)");
            statement.setString(1, news.getTitle());
            statement.setString(2, news.getContent());
            statement.setLong(3, news.getUserId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  public static void deleteNews(Long id){
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE  FROM news where id = ?");
            statement.setLong(1,id);
            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
  }
    public static void deleteComment(Long id){
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE  FROM comments where id = ?");
            statement.setLong(1,id);
            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static News getNewsById(Long id) {
        News news = new News();

        try {
            PreparedStatement statement = connection.prepareStatement("select * from news where id = ?");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                news = new News(
                        id,
                        result.getString("title"),
                        result.getString("content"),
                        result.getLong("userId")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public static void updateNews(News news) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE news set title = ?, content = ?, userId = ?  where id = ?");
            statement.setString(1, news.getTitle());
            statement.setString(2, news.getContent());
            statement.setLong(3, news.getUserId());
            statement.setDouble(4, news.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void updateComment(Comment comment) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE comments set comment = ?, userId = ?, newsId=?  where id = ?");
            statement.setString(1, comment.getComment());
            statement.setLong(2, comment.getUserId());
            statement.setLong(3, comment.getNewsId());
            statement.setDouble(4, comment.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Comment getCommentById(Long id) {
        Comment comment = new Comment();

        try {
            PreparedStatement statement = connection.prepareStatement("select * from comments where id = ?");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                comment = new Comment(
                        id,
                        result.getString("comment"),
                        result.getLong("userId"),
                        result.getLong("newsId")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comment;
    }
    public static List<News> getAllNewsById(Long userId) {
        List<News> newsList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from news where userId = ?");
            statement.setLong(1, userId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Long id = result.getLong("id");
                String title = result.getString("title");
                String content = result.getString("content");


                newsList.add(new News(id, title, content, userId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsList;
    }
    public static List<News> getNewsByKeyWord(String keyWord) {
        List<News> newsList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from news where title like '%" + keyWord + "%' ");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Long id = result.getLong("id");
                String title = result.getString("title");
                String content = result.getString("content");
                Long userId = result.getLong("userId");
                newsList.add(new News(id, title, content, userId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsList;
    }
}
