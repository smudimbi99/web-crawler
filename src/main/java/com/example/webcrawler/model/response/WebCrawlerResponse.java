package com.example.webcrawler.model.response;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class WebCrawlerResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    Set<SearchResult> searchResults;

    public Set<SearchResult> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(Set<SearchResult> searchResults) {
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
