package be.sfpd.blog.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comment {

	private Long id;
	private String userId;
	private String body;
	private LocalDate commentDate;

	public Comment() {
	}

	public Comment(Long id, String userId, String body, LocalDate commentDate) {
		this.id = id;
		this.userId = userId;
		this.body = body;
		this.commentDate = commentDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public LocalDate getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(LocalDate commentDate) {
		this.commentDate = commentDate;
	}




}
