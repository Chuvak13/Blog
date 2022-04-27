package DB;

import Model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBMangerUsers {
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
    public static User getUserByEmail(String email) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select * from users where login = ?");
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user = new User(
                        result.getLong("id"),
                        result.getString("login"),
                        result.getLong("password"),
                        result.getString("fullname")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public static void addUser(User user){
        try{
            PreparedStatement statement = connection.prepareStatement("insert into users values (null, ?, ?, ?)");
            statement.setString(1, user.getLogin());
            statement.setLong(2, user.getPassword());
            statement.setString(3, user.getFullname());
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }




}
