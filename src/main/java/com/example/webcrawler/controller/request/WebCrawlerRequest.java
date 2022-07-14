package com.example.webcrawler.controller.request;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

public class WebCrawlerRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Url cannot be blank")
    @Pattern(regexp = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)", message = "Invalid Url")
    private String url;

    @NotNull(message = "Text cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "Invalid text")
    private String text;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        WebCrawlerRequest that = (WebCrawlerRequest) o;
        return Objects.equals(url, that.url) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, text);
    }

    @Override
    public String toString() {
        return "WebCrawlerRequest{" +
                "url='" + url + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
