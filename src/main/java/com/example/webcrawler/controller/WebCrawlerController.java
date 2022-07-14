package com.example.webcrawler.controller;

import com.example.webcrawler.controller.request.WebCrawlerRequest;
import com.example.webcrawler.controller.response.WebCrawlerResponse;
import com.example.webcrawler.service.WebCrawlerService;
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

    @Autowired
    private WebCrawlerService webCrawlerService;

    @PostMapping(value = "/searchText", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebCrawlerResponse searchText(@RequestBody @Valid WebCrawlerRequest webCrawlerRequest) {
        return webCrawlerService.getSearchTextFromUrl(webCrawlerRequest);
    }
}
