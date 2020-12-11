package be.sfpd.blog.resource;

import be.sfpd.blog.model.Comment;
import be.sfpd.blog.service.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Objects;

@Path("articles/{articleId}/comments")
@Produces(MediaType.APPLICATION_JSON)
public class CommentsResource {

    private final CommentService service = new CommentService();

    @GET
    public List<Comment> getAllComments(@PathParam("articleId") Long articleId) {
        return service.getAllComments(articleId);
    }

    @GET
    @Path("/{id}")
    public Comment getCommentById(@PathParam("articleId") Long articleId, @PathParam("commentId") Long commentId) {
        return service.getCommentById(articleId, commentId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Comment createComment(@PathParam("articleId") Long articleId, Comment comment) {
        Comment obj = service.addComment(articleId, comment);
        if (Objects.nonNull(obj)) {
            return obj;
        }
        return null;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Comment updateComment(@PathParam("articleId") Long articleId, @PathParam("id") Long commentId, Comment comment) {
        System.out.println("Will Update comment " + commentId);
        comment.setId(commentId);
        Comment updatedComment = service.updateComment(articleId, comment);
        if (Objects.nonNull(updatedComment)) {
            System.out.println("Update OK for Id " + updatedComment.getId());
            return updatedComment;
        }
        System.out.println("Update KO for Id " + commentId);
        return null;
    }

    @DELETE
    @Path("/{id}")
    public void deleteComment(@PathParam("articleId") Long articleId, @PathParam("id") Long commentId) {
        service.removeComment(articleId, commentId);
    }
}