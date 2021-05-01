package com.github.saintukranian.servlets.api;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = AppConfiguration.class)
public class ServletsApiTest {

  static RestTemplate restTemplate;


  @BeforeAll
  public static void init() {
    restTemplate = new RestTemplateBuilder().build();
  }

  @Test
  public void shouldReturnHomepageWithInputs() {
    String htmlResponse = restTemplate.getForObject(AppConfiguration.getUrl(), String.class);
    assertNotNull(htmlResponse);

    List<Element> inputs = Jsoup.parse(htmlResponse).getElementsByClass("form__input");

    assertEquals(3, inputs.size());
  }

  @Test
  public void shouldReturnCalculatePageResult() {
    String calculateExpression = "calculate?type=Sin&value=2.5";

    ResponseEntity<String> responseEntity =
        restTemplate.postForEntity(AppConfiguration.getUrl() + calculateExpression, null, String.class);

    String htmlValue = responseEntity.getBody();

    assertNotNull(htmlValue);
    assertEquals(200, responseEntity.getStatusCodeValue());

    String result =
        Jsoup.parse(htmlValue).getElementById("result").text();

   assertEquals("Result of Sin(2.5): 0.5984721441039564", result);
  }

  @Test
  public void shouldRedirectToHomepage() {
    // defining setting for redirect handling
    final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    final HttpClient httpClient = HttpClientBuilder.create()
        .setRedirectStrategy(new LaxRedirectStrategy())
        .build();
    factory.setHttpClient(httpClient);

    // setting request factory for redirection
    restTemplate.setRequestFactory(factory);

    // getting response
    ResponseEntity<String> responseEntity =
        restTemplate.postForEntity(AppConfiguration.getUrl() + "calculate", null, String.class);

    String htmlResponse = responseEntity.getBody();

    assertNotNull(htmlResponse);
    assertEquals(200, responseEntity.getStatusCodeValue());

    String htmlValue =
        Jsoup.parse(htmlResponse).head().getElementsByTag("title").text();
    assertEquals("Homepage", htmlValue);
  }
}
