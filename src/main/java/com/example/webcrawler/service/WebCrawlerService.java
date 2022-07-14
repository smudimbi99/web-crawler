package com.example.webcrawler.service;

import com.example.webcrawler.controller.request.WebCrawlerRequest;
import com.example.webcrawler.controller.response.WebCrawlerResponse;

public interface WebCrawlerService {
    WebCrawlerResponse getSearchTextFromUrl(WebCrawlerRequest webCrawlerRequest);
}
