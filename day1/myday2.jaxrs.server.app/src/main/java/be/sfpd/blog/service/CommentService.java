package be.sfpd.blog.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import be.sfpd.blog.model.Article;
import be.sfpd.blog.model.Comment;
import be.sfpd.blog.repository.MockDatabase;

public class CommentService {

	private final Map<String, Article> articles = MockDatabase.getArticles();
	private final Map<String, Comment> comments = MockDatabase.getComments();

	public CommentService() {
		comments.put("1", new Comment(1L,"User", "Test", LocalDate.now()));
		articles.put("1", new Article(1L, "Hello world", new ArrayList<Comment>(comments.values())));
		articles.put("2", new Article(2L, "Hello Jersey"));
	}

	public List<Comment> getComments() {return new ArrayList<>(comments.values());}


	public Article addComment(long articleId, Comment comment) {
		Article article = articles.get(String.valueOf(articleId));
		if (article != null) {
			if (StringUtils.isNotEmpty(comment.getBody())) {
				comment.setId((long) article.getComments().size() + 1);
				article.getComments().add(comment);
				return article;
			}
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
		Article article = articles.get(String.valueOf(articleId));
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
		Article article = articles.get(String.valueOf(articleId));
		article.getComments().remove(commentToDelete);
	}


	public Comment getCommentById(Long id, Long commentId) {
		return getComment(id, commentId);
	}
}
