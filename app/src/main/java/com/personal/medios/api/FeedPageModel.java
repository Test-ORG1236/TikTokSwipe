package com.personal.medios.api;

import java.util.List;

public class FeedPageModel {
    private List<FeedModel> feed;
    String cursor_token;

    public String getCursor_token() {
        return cursor_token;
    }

    public void setCursor_token(String cursor_token) {
        this.cursor_token = cursor_token;
    }
    public List<FeedModel> getFeed() {
        return feed;
    }

    public void setFeed(List<FeedModel> feed) {
        this.feed = feed;
    }

}
