package be.sfpd.blog.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import be.sfpd.blog.model.Article;
import be.sfpd.blog.service.ArticleService;

@Path("attachment")
@Produces(MediaType.APPLICATION_JSON)
public class AttachmentResource {

	private final ArticleService service = new ArticleService();

	@GET
	@Path("/{id}")
	@RolesAllowed("test")
	public Article getArticleById(@PathParam("id") Long articleId) {
		return service.getArticleById(articleId);
	}

	@POST
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public Response createArticleWithAttachment(Article article, InputStream input) throws IOException {

		Article obj = service.addArticle(article);
		obj.getAttachment().setData(input.readAllBytes());

		return Response.status(Response.Status.OK).entity("File uploaded successfully")
				.type(MediaType.TEXT_PLAIN).build();
	}
}
