package be.sfpd.blog.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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


	public Comment updateComment(long articleId, Comment comment) {
		Comment commentToUpdate = getComment(articleId, comment.getId());
		commentToUpdate.setBody(comment.getBody());
		commentToUpdate.setUserId(comment.getUserId());
		return commentToUpdate;
	}

	private Comment getComment(long articleId, Long commentId) {
		Article article = articleService.getArticleById(articleId);
		List<Comment> comments = article.getComments().stream()
				.filter(c -> c.getId() == commentId)
				.collect(Collectors.toList());
		if (comments.isEmpty()) {
			System.out.println("Not found comment " + commentId);
			return null;
		}
		if( comments.size() > 1 ) {
			System.out.println("More then one comment with id " + commentId);
			return null;
		}
		System.out.println("Found article " + commentId.toString());
		return comments.get(0);
	}

	public void removeComment(Long articleId, Long commentId) {
		Comment commentToDelete = getComment(articleId, commentId);
		Article article = articleService.getArticleById(articleId);
		article.getComments().remove(commentToDelete);
	}


}
