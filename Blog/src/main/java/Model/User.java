package Model;

public class User {
    Long id;
    String login;
    Long password;
    String fullname;

    public User(){}
    public User(Long id, String login, long password, String fullname) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.fullname = fullname;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getPassword() {
        return password;
    }

    public void setPassword(long password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password=" + password +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
