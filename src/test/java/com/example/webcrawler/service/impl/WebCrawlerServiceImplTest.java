package com.example.webcrawler.service.impl;

import com.example.webcrawler.model.request.WebCrawlerRequest;
import com.example.webcrawler.model.response.WebCrawlerResponse;
import com.example.webcrawler.service.WebCrawlerService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WebCrawlerServiceImplTest {

    @Test
    public void testGetSearchTextFromUrl() {

        String expectedText = "Google - About Google, Our Culture & Company News Jump to content About Products Commitments " +
                "Stories India Blog Our mission is to organise the world’s information and make it universally accessible and useful. " +
                "International Women’s Day A closer look at the journey to help accelerate equity and representation for women at Google " +
                "and beyond Learn more Company news {['@']}{[::feed.account | stripSpaces]} {[feed.account]} {['@']}{[::feed.account | stripSpaces]} " +
                "{[::feed.published_at|date:'mediumDate']} {['@']}{[::feed.account | stripSpaces]} {[feed.account]} {['@']}{[::feed.account | " +
                "stripSpaces]} {[::feed.published_at|date:'mediumDate']} Our Products Hi, how can we help? See what we build Our Commitments Dedicated " +
                "to improving people’s lives. As true today as it was at the start. How we contribute Our Stories The best part about technology " +
                "is seeing what the world does with it Explore our stories Careers at Google Join us Google locations Find us From the garage to the " +
                "Googleplex Our history Contact Us Say hello Today in Doodle history {[::doodle.localizedDate]} {[::doodle.title]} " +
                "{[::doodleCtrl.doodleHistory[0].localizedDate]} {[::doodleCtrl.doodleHistory[0].title]} Discover more Google Doodles Back to top " +
                "Follow us हिन्दी More about us Contact us Investor relations Careers Locations Blog Think with Google Press room Press enquiries " +
                "Images & B-roll Permissions Policy Application security Software principles Unwanted software policy Responsible supply chain Extended " +
                "workforce Conflict minerals policy Community Guidelines How our business works Help Privacy Terms";

        WebCrawlerRequest webCrawlerRequest = new WebCrawlerRequest();
        webCrawlerRequest.setUrl("http://www.google.com");
        webCrawlerRequest.setText("Google - About Google, Our Culture & Company News Jump to content About Products Commitments Stories India Blog Our mission is to organise");

        WebCrawlerService webCrawlerService = new WebCrawlerServiceImpl();
        WebCrawlerResponse response = webCrawlerService.getSearchTextFromUrl(webCrawlerRequest);

        assertEquals(2, response.getSearchResults().size());

        assertEquals("http://www.google.com/intl/en/about.html", response.getSearchResults().get(0).getPageUrl());
        assertEquals(expectedText, response.getSearchResults().get(0).getText());
        assertEquals("http://www.google.com/intl/en/about.html", response.getSearchResults().get(1).getPageUrl());
        assertEquals(expectedText, response.getSearchResults().get(1).getText());
    }

    @Test
    public void testGetSearchTextFromUrl_blankUrl() {

        WebCrawlerRequest webCrawlerRequest = new WebCrawlerRequest();
        webCrawlerRequest.setUrl("");
        webCrawlerRequest.setText("Google - About Google, Our Culture & Company News Jump to content About Products Commitments Stories India Blog Our mission is to organise");

        WebCrawlerService webCrawlerService = new WebCrawlerServiceImpl();
        WebCrawlerResponse response = webCrawlerService.getSearchTextFromUrl(webCrawlerRequest);

        assertTrue(response.getSearchResults().isEmpty());
    }

    @Test
    public void testGetSearchTextFromUrl_exceptionThrown() {

        WebCrawlerService webCrawlerService = new WebCrawlerServiceImpl();
        WebCrawlerResponse response = webCrawlerService.getSearchTextFromUrl(null);

        assertTrue(response.getSearchResults().isEmpty());
    }
}
