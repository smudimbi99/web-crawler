package com.example.webcrawler.controller;

import com.example.webcrawler.model.request.WebCrawlerRequest;
import com.example.webcrawler.model.response.SearchResult;
import com.example.webcrawler.model.response.WebCrawlerResponse;
import com.example.webcrawler.service.WebCrawlerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WebCrawlerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private WebCrawlerService webCrawlerService;

    @InjectMocks
    private WebCrawlerController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testSearchText() throws Exception {

        String webCrawlerRequest = "{\"url\":\"http://www.google.com\",\"text\":\"test\"}";
        WebCrawlerResponse webCrawlerResponse = new WebCrawlerResponse();
        webCrawlerResponse.setSearchResults(new HashSet<>(Collections.singletonList(new SearchResult("http://www.google.com", "test"))));

        when(webCrawlerService.getSearchTextFromUrl(isA(WebCrawlerRequest.class))).thenReturn(webCrawlerResponse);

        MvcResult result = this.mockMvc
                .perform(post("/api/searchText").content(webCrawlerRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());

        verify(webCrawlerService).getSearchTextFromUrl(isA(WebCrawlerRequest.class));
    }

    @Test
    public void testSearchText_inValidUrl() throws Exception {

        String webCrawlerRequest = "{\"url\":\"://www.@#^\",\"text\":\"test\"}";

        MvcResult result = this.mockMvc
                .perform(post("/api/searchText").content(webCrawlerRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertEquals(400, result.getResponse().getStatus());
        assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains("Invalid Url"));

        verifyNoInteractions(webCrawlerService);
    }

    @Test
    public void testSearchText_blankUrl() throws Exception {

        String webCrawlerRequest = "{\"url\":null,\"text\":\"test\"}";

        MvcResult result = this.mockMvc
                .perform(post("/api/searchText").content(webCrawlerRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertEquals(400, result.getResponse().getStatus());
        assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains("Url cannot be blank"));

        verifyNoInteractions(webCrawlerService);
    }

    @Test
    public void testSearchText_inValidText() throws Exception {

        String webCrawlerRequest = "{\"url\":\"http://www.google.com\",\"text\":\"!@#$RT\"}";

        MvcResult result = this.mockMvc
                .perform(post("/api/searchText").content(webCrawlerRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertEquals(400, result.getResponse().getStatus());
        assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains("Invalid text"));

        verifyNoInteractions(webCrawlerService);
    }

    @Test
    public void testSearchText_blankText() throws Exception {

        String webCrawlerRequest = "{\"url\":\"http://www.google.com\",\"text\":null}";

        MvcResult result = this.mockMvc
                .perform(post("/api/searchText").content(webCrawlerRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertEquals(400, result.getResponse().getStatus());
        assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains("Text cannot be blank"));

        verifyNoInteractions(webCrawlerService);
    }
}
