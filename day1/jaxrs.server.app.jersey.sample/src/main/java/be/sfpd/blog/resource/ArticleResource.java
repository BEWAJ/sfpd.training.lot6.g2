package be.sfpd.blog.resource;

import be.sfpd.blog.model.Article;
import be.sfpd.blog.service.ArticleServiceSample;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("articles")
public class ArticleResource {

    ArticleServiceSample service = new ArticleServiceSample();

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Article> getAllArticle() {
        return service.getArticles();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Article getArticleById(@PathParam("id") Long articleId) {
        return service.getArticleById(articleId);
    }

}
