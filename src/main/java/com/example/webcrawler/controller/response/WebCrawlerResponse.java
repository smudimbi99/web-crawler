package com.example.webcrawler.controller.response;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class WebCrawlerResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    List<SearchResult> searchResults;

    public List<SearchResult> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<SearchResult> searchResults) {
        this.searchResults = searchResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebCrawlerResponse that = (WebCrawlerResponse) o;
        return Objects.equals(searchResults, that.searchResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchResults);
    }

    @Override
    public String toString() {
        return "WebCrawlerResponse{" +
                "searchResults=" + searchResults +
                '}';
    }
}
