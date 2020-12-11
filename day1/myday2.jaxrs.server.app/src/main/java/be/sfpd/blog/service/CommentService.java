package be.sfpd.blog.service;

import be.sfpd.blog.model.Article;
import be.sfpd.blog.model.Comment;
import be.sfpd.blog.repository.MockDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentService {

    private Map<String, Article> articles = MockDatabase.getArticles();

    public List<Comment> getAllComments(Long articleId) {
        Article selectedArticle = articles.get(articleId.toString());
        Map<Long, Comment> comments = selectedArticle.getComments();
        return new ArrayList<>(comments.values());
    }

    public Comment getCommentById(Long articleId, Long commentId) {
        Article selectedArticle = articles.get(articleId.toString());
        Map<Long, Comment> comments = selectedArticle.getComments();

        return comments.get(commentId);
    }

    public Comment addComment(Long articleId, Comment comment) {
        Article selectedArticle = articles.get(articleId.toString());
        Map<Long, Comment> comments = selectedArticle.getComments();

        comment.setId((long) (comments.size() + 1));
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment updateComment(Long articleId, Comment comment) {
        Article selectedArticle = articles.get(articleId.toString());
        Map<Long, Comment> comments = selectedArticle.getComments();

        Comment commentToUpdate = comments.get(comment.getId());
        commentToUpdate.setMessage(comment.getMessage());
        comments.replace(commentToUpdate.getId(), commentToUpdate);

        return comment;
    }

    public void removeComment(Long articleId, Long commentId) {
        Map<Long, Comment> comments = articles.get(articleId.toString()).getComments();
        comments.remove(commentId);
    }
}
