package be.sfpd.blog.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import be.sfpd.blog.model.Article;
import be.sfpd.blog.model.Comment;
import be.sfpd.blog.repository.MockDatabase;

public class CommentService {

	private final Map<String, Comment> comments = MockDatabase.getComments();

	private ArticleService articleService = new ArticleService();

	public CommentService() {
		comments.put("1", new Comment(1L,"User", "Test", LocalDate.now()));
	}

	public List<Comment> getComments() {return new ArrayList<>(comments.values());}


	public Article addComment(long articleId, Comment comment) {
		Article article = articleService.getArticleById(articleId);
		if (StringUtils.isNotEmpty(comment.getBody())) {
			article.getComments().add(comment);
			return article;
		}
		return article;
	}

	public Comment updateComment(Comment comment) {
		if (comments.containsKey(comment.getId().toString())) {
			System.out.println("Not found comment " + comment.getId());
			return null;
		}
		System.out.println("Found article " + comment.getId().toString());

		Comment commentToUpdate = comments.get(comment.getId().toString());
		commentToUpdate.setBody(comment.getBody());
		commentToUpdate.setUserId(comment.getUserId());
		Comment replaced = comments.replace(commentToUpdate.getId().toString(), commentToUpdate);
		return replaced;
	}

}
