package be.sfpd.blog.resource;

import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import be.sfpd.blog.model.Article;
import be.sfpd.blog.model.Comment;
import be.sfpd.blog.service.ArticleService;
import be.sfpd.blog.service.CommentService;

@Path("articles/{articleId}/comments")
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

	private final CommentService service = new CommentService();

	@GET
	@Path("/{commentId}")
	public Comment getCommentById(@PathParam("articleId") Long id, @PathParam("commentId") Long commentId) {
		return service.getCommentById(id, commentId);
	}


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Article addComment(@PathParam("articleId") Long id, Comment comment) {
		Article updatedArticle = service.addComment(id, comment);
		if (Objects.nonNull(updatedArticle)) {
			System.out.println("Update OK for Id " + updatedArticle.getId());
			return updatedArticle;
		}
		System.out.println("Update KO for Id " + id);
		return null;
	}

	@PUT
	@Path("/{commentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Comment updateComment(@PathParam("articleId") Long id, @PathParam("commentId") Long commentId, Comment comment) {
		System.out.println("Will Update comment " + id);
		comment.setId(commentId);
		Comment updatedComment = service.updateComment(id, comment);
		if (Objects.nonNull(updatedComment)) {
			System.out.println("Update OK for Id " + updatedComment.getId());
			return updatedComment;
		}
		System.out.println("Update KO for Id " + id);
		return null;
	}

	@DELETE
	public void deleteComment(@PathParam("articleId") Long id, Comment comment) {
		service.removeComment(id, comment.getId());
	}

}
