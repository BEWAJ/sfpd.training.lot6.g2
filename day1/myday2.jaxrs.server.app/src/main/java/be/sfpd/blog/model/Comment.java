package be.sfpd.blog.model;

import java.time.LocalDateTime;

public class Comment {

    private Long id;

    private String message;

    private LocalDateTime createdDate;

    private String author;

    public Comment() {
    }

    public Comment(Long id, String message, LocalDateTime createdDate, String author) {
        this.id = id;
        this.message = message;
        this.createdDate = createdDate;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
