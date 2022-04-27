package Model;

public class Comment {
    private Long id;
    private String comment;
    private Long userId;
    private Long newsId;

    public Comment() {
    }

    public Comment(Long id, String comment, Long userId, Long newsId) {
        this.id = id;
        this.comment = comment;
        this.userId = userId;
        this.newsId = newsId;
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", userId=" + userId +
                ", newsId=" + newsId +
                '}';
    }
}
