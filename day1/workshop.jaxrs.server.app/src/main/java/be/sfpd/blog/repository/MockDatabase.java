package be.sfpd.blog.repository;

import be.sfpd.blog.model.Article;
import be.sfpd.blog.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class MockDatabase {

    public static Map<String, Article> articles = new HashMap<>();
    public static Map<String, Profile> profiles = new HashMap<>();

    public static Map<String, Article> getArticles() {
        return articles;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }

}
