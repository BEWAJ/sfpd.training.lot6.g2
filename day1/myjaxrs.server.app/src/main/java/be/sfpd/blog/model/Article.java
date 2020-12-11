package be.sfpd.blog.model;

import be.sfpd.blog.adapter.LocalDateTimeAdapter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement
public class Article {

    private Long id;

    private LocalDateTime createdDate;
    private LocalDateTime updatedAt;

    private String body;

    private Map<Long, Comment> comments = new HashMap<>();

    public Article() {
    }

    public Article(Long id, String body) {
        this.id = id;
        this.createdDate = LocalDateTime.now();;
        this.updatedAt = LocalDateTime.now();;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @XmlTransient
    public Map<Long, Comment> getComments() {
        return comments;
    }

    public void setComments(Map<Long, Comment> comments) {
        this.comments = comments;
    }
}
