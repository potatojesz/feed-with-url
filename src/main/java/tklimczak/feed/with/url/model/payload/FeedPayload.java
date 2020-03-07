package tklimczak.feed.with.url.model.payload;

import java.util.List;

public class FeedPayload {
    private String email;
    private List<String> urls;

    public FeedPayload() { }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getUrls() {
        return urls;
    }
    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
