package be.sfpd.blog.model;

import javax.xml.bind.annotation.XmlRootElement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class Article {

    private Long id;

    private LocalDate createdDate;
    private LocalDate updatedAt;

    private String body;
	private List<Comment> comments = new ArrayList<>();


    public Article(Long id, String body) {
        this.id = id;
        this.createdDate =  LocalDate.now();
        this.updatedAt =  LocalDate.now();
        this.body = body;
    }

	public Article(Long id, String body, List<Comment> comments) {
		this.id = id;
		this.createdDate =  LocalDate.now();
		this.updatedAt =  LocalDate.now();
		this.body = body;
		this.comments = comments;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Comment> getComments() {
		return comments;
	}



	public Article() {
	}

}
