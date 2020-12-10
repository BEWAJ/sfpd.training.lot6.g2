package be.sfpd.blog.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Article {

    private Long id;

    private Date createdDate;

    private String body;

    public Article() {
    }

    public Article(Long id, Date createdDate, String body) {
        this.id = id;
        this.createdDate = createdDate;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
