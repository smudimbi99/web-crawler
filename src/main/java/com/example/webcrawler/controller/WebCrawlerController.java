package com.example.webcrawler.controller;

import com.example.webcrawler.model.request.WebCrawlerRequest;
import com.example.webcrawler.model.response.WebCrawlerResponse;
import com.example.webcrawler.service.WebCrawlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class WebCrawlerController {

    private final Logger logger = LoggerFactory.getLogger(WebCrawlerController.class);

    @Autowired
    private WebCrawlerService webCrawlerService;

    @PostMapping(value = "/searchText", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebCrawlerResponse searchText(@RequestBody @Valid WebCrawlerRequest webCrawlerRequest) {
        logger.debug("Request in WebCrawlerController :: searchText : {}", webCrawlerRequest);
        return webCrawlerService.getSearchTextFromUrl(webCrawlerRequest);
    }
}
