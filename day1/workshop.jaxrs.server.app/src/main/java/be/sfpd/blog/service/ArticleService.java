package be.sfpd.blog.service;

import be.sfpd.blog.model.Article;
import be.sfpd.blog.repository.MockDatabase;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class ArticleService {

    private final Map<String, Article> articles = MockDatabase.getArticles();

    public ArticleService() {
        articles.put("1", new Article(1L, "Hello world"));
        articles.put("2", new Article(2L, "Hello Jersey"));
    }

    public List<Article> getArticles() {
        return new ArrayList<>(articles.values());
    }

    public Article getArticleById(Long id) {
        return articles.get(id.toString());
    }

    public Article addArticle(Article article) {
        if (StringUtils.isNotEmpty(article.getBody())) {
            article.setId((long) (articles.size() + 1));
            article.setCreatedDate(new Date());
            articles.put(article.getId().toString(), article);
            return article;
        }
        return null;
    }

    public Article updateArticle(Article article) {
        if (articles.containsKey(article.getId().toString())) {
            System.out.println("Not found article " + article.getId());
            return null;
        }
        System.out.println("Found article " + article.getId().toString());

        Article articleToUpdate = articles.get(article.getId().toString());
        articleToUpdate.setBody(article.getBody());
        articleToUpdate.setUpdatedAt(new Date());
        Article replaced = articles.replace(articleToUpdate.getId().toString(), articleToUpdate);
        return replaced;
    }

    public void removeArticle(Long id) {
        articles.remove(id.toString());
    }
}
