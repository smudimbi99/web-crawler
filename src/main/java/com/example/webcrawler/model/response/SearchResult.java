package com.example.webcrawler.model.response;

import java.io.Serializable;
import java.util.Objects;

public class SearchResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String pageUrl;
    private String text;

    public SearchResult(String pageUrl, String text) {
        this.pageUrl = pageUrl;
        this.text = text;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResult that = (SearchResult) o;
        return Objects.equals(pageUrl, that.pageUrl) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageUrl, text);
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "pageUrl='" + pageUrl + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
