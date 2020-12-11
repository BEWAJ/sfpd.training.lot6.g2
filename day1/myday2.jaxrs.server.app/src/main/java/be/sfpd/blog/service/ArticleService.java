package be.sfpd.blog.service;

import be.sfpd.blog.model.Article;
import be.sfpd.blog.repository.MockDatabase;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ArticleService {

    private Map<String, Article> articles = MockDatabase.getArticles();

    public ArticleService() {
    }

    public List<Article> getArticles() {
        return new ArrayList<>(articles.values());
    }

    public List<Article> getArticlesByYear(int year) {
        List<Article> articleList = new ArrayList<>(articles.values());
        return articleList
                .stream()
                .filter(article -> article.getCreatedDate().getYear() == year)
                .collect(Collectors.toList());
    }

    public List<Article> getPaginatedArticles(int offset, int limit, List<Article> articlesToPaginate) {
        if (articlesToPaginate == null || articlesToPaginate.isEmpty()) {
            articlesToPaginate = new ArrayList<>();
            articlesToPaginate.addAll(articles.values());
        }
        if (offset + limit > articlesToPaginate.size()) {
            if (offset <= articlesToPaginate.size() ) {
                return articlesToPaginate.subList(offset, articlesToPaginate.size());
            }
            return new ArrayList<>();
        }
        return articlesToPaginate.subList(offset, offset + limit);
    }

    public Article getArticleById(Long id) {
        return articles.get(id.toString());
    }

    public Article addArticle(Article article) {
        if (StringUtils.isNotEmpty(article.getBody())) {
            article.setId((long) (articles.size() + 1));
            article.setCreatedDate(LocalDateTime.now());
            article.setUpdatedAt(LocalDateTime.now());
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
        articleToUpdate.setUpdatedAt(LocalDateTime.now());
        Article replaced = articles.replace(articleToUpdate.getId().toString(), articleToUpdate);
        return replaced;
    }

    public void removeArticle(Long id) {
        articles.remove(id.toString());
    }
}
