package com.example.webcrawler.service.impl;

import com.example.webcrawler.model.request.WebCrawlerRequest;
import com.example.webcrawler.model.response.SearchResult;
import com.example.webcrawler.model.response.WebCrawlerResponse;
import com.example.webcrawler.service.WebCrawlerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class WebCrawlerServiceImpl implements WebCrawlerService {

    private final Logger logger = LoggerFactory.getLogger(WebCrawlerServiceImpl.class);

    @Override
    public WebCrawlerResponse getSearchTextFromUrl(WebCrawlerRequest webCrawlerRequest) {
        WebCrawlerResponse webCrawlerResponse = new WebCrawlerResponse();
        List<SearchResult> searchResults = new ArrayList<>();
        try {
            Set<String> pageUrls = getPageUrls(webCrawlerRequest.getUrl());
            pageUrls.add(webCrawlerRequest.getUrl());
            for (String pageUrl : pageUrls) {
                Document document = Jsoup.connect(pageUrl).get();
                Elements elements = document.getElementsContainingText(webCrawlerRequest.getText());
                if (!CollectionUtils.isEmpty(elements)) {
                    for (Element element : elements) {
                        searchResults.add(new SearchResult(pageUrl, element.text()));
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Exception in WebCrawlerServiceImpl :: getSearchTextFromUrl : {}", e.getMessage());
        }
        webCrawlerResponse.setSearchResults(searchResults);
        logger.debug("Response in WebCrawlerServiceImpl :: getSearchTextFromUrl : {}", webCrawlerResponse);
        return webCrawlerResponse;
    }

    private Set<String> getPageUrls(String url) throws Exception {
        Document document = Jsoup.connect(url).get();
        Elements linksOnPage = document.select("a[href]");
        return linksOnPage.stream()
                .map(element -> element.attr("abs:href"))
                .collect(Collectors.toSet());
    }
}
