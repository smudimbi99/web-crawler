package com.example.webcrawler.service;

import com.example.webcrawler.model.request.WebCrawlerRequest;
import com.example.webcrawler.model.response.WebCrawlerResponse;

public interface WebCrawlerService {
    WebCrawlerResponse getSearchTextFromUrl(WebCrawlerRequest webCrawlerRequest);
}
