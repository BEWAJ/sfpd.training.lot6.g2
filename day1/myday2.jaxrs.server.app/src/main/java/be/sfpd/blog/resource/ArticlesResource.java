package be.sfpd.blog.resource;

import be.sfpd.blog.model.Article;
import be.sfpd.blog.model.Comment;
import be.sfpd.blog.service.ArticleService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Path("articles")
@Produces(MediaType.APPLICATION_JSON)
public class ArticlesResource {

    private final ArticleService service = new ArticleService();

    @GET
    public List<Article> getAllArticle(@QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit, @QueryParam("year") Integer year) {

    	System.out.println("yay this is query param limit " + limit);
    	System.out.println("yay this is query param offset " + offset);

		List<Article> articles = service.getArticles()
				.stream()
				.skip(offset == null ? 0 : offset)
				.limit(limit == null ? 0: limit)
				.filter(a -> a.getCreatedDate().getYear() == year)
				.collect(Collectors.toList());

		return articles;


    }

    @GET
    @Path("/{id}")
    public Article getArticleById(@PathParam("id") Long articleId) {
        return service.getArticleById(articleId);
    }


	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Article createArticle(Article article) {
        Article obj = service.addArticle(article);
        if (Objects.nonNull(obj)) {
            return obj;
        }
        return null;
    }

    @PUT
    @Path("/{articleId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Article updateArticle(@PathParam("articleId") Long id, Article article) {
        System.out.println("Will Update article " + id);
        article.setId(id);
        Article updatedArticle = service.updateArticle(article);
        if (Objects.nonNull(updatedArticle)) {
            System.out.println("Update OK for Id " + updatedArticle.getId());
            return updatedArticle;
        }
        System.out.println("Update KO for Id " + id);
        return null;
    }

    @DELETE
    @Path("/{articleId}")
    public void deleteArticle(@PathParam("articleId") Long id) {
        service.removeArticle(id);
    }
}