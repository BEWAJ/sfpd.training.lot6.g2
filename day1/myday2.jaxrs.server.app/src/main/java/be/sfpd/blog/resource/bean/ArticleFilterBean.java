package be.sfpd.blog.resource.bean;

import javax.ws.rs.QueryParam;

public class ArticleFilterBean {

    @QueryParam("offset") int offset;

    @QueryParam("limit") int limit;

    @QueryParam("year") int year;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /*
    @HeaderParam("authToken") String token;

    @CookieParam("preferences") String preferences;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }
    */
}
