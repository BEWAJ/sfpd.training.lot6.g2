package be.sfpd.blog.resource;

import be.sfpd.blog.model.Article;
import be.sfpd.blog.service.ArticleService;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Path("articles")
public class ArticleResource {

    ArticleService service = new ArticleService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getAllArticle() {
        return service.getArticles();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Article getArticleById(@PathParam("id") Long articleId) {
        return service.getArticleById(articleId);
    }

    @POST
	@Path("/add")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.TEXT_PLAIN)
	public String add(Article article){
    	article.setCreatedDate(LocalDateTime.now());
    	service.addArticle(article);
    	return "article added";
    }

	@PUT
	@Path("/update")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.TEXT_PLAIN)
	public String update(Article article){
    	System.out.println("Update called");
    	Article a = service.getArticleById(article.getId());
		a.setBody(article.getBody());
		System.out.println("body:" + a.getId() + a.getBody());
		System.out.println("body:" + article.getId() + article.getBody());
		service.updateArticle(article);
    	System.out.println("Update done");
		return "article updated";
	}
}
