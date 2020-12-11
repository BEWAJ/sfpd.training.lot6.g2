package be.sfpd.blog.resource;

import be.sfpd.blog.exception.MyCustomException;
import be.sfpd.blog.model.Article;
import be.sfpd.blog.resource.bean.ArticleFilterBean;
import be.sfpd.blog.service.ArticleService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Path("articles")
@Produces(MediaType.APPLICATION_JSON)
public class ArticlesResource {

    private final ArticleService service = new ArticleService();

    @GET
	@RolesAllowed("test")
    public Response getAllArticle(@BeanParam ArticleFilterBean articleFilterBean) {
        List<Article> articlesByYear = new ArrayList<>();

        if (articleFilterBean.getYear() > 0) {
            articlesByYear.addAll(service.getArticlesByYear(articleFilterBean.getYear()));
        } else {
            articlesByYear.addAll(service.getArticles());
        }
        if (articleFilterBean.getOffset() >= 0 && articleFilterBean.getLimit() > 0) {
			List<Article> paginatedArticles = service.getPaginatedArticles(articleFilterBean.getOffset(), articleFilterBean.getLimit(), articlesByYear);
			articlesByYear.clear();
			articlesByYear.addAll(paginatedArticles);
        }
        return Response
				.accepted(articlesByYear)
				.build();
    }

    @GET
    @Path("/{id}")
	@RolesAllowed("test")
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
    public Article updateArticle(@PathParam("articleId") Long id, Article article) throws MyCustomException {
        System.out.println("Will Update article " + id);
        article.setId(id);
        Article updatedArticle = service.updateArticle(article);
        if (Objects.nonNull(updatedArticle)) {
            System.out.println("Update OK for Id " + updatedArticle.getId());
            return updatedArticle;
        } else {
        	throw new MyCustomException();
		}
    }

    @DELETE
    @Path("/{articleId}")
    public void deleteArticle(@PathParam("articleId") Long id) {
        service.removeArticle(id);
    }
}