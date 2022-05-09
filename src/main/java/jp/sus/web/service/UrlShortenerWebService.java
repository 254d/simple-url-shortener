package jp.sus.web.service;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import jp.sus.web.model.UrlShortenerData;
import lombok.extern.slf4j.Slf4j;

/**
 * UrlShortenerWebService.
 */
@Service
@Slf4j
public class UrlShortenerWebService {
  /** URL of the API to call. */
  @Value("${jp.shortener.web.api.host:}")
  private String apiHost;
  /** Server port number. */
  @Value("${server.port:8080}")
  private String port;

  /** WebClient insntance */
  private WebClient webClient = WebClient.builder().build();

  /**
   * Get destination.
   * 
   * If it does not exist, null is returned.
   * 
   * @param id ID
   * @return destination URL
   */
  public String getDestenation(String id) {
    UrlShortenerData response = getSingleDataByApi(id);
    if (null == response) {
      return null;
    }
    return response.getOriginalUrl();
  }

  /**
   * Get destination from ID by API
   * 
   * @param id ID
   * @return URL shortener data
   */
  private UrlShortenerData getSingleDataByApi(String id) {
    try {
      String url = "http://" + apiHost + ":" + port +"/api/" + id;
      return webClient.get().uri(url).retrieve().bodyToMono(UrlShortenerData.class).block();
    } catch (Exception e) {
      log.debug(ExceptionUtils.getStackTrace(e));
      return null;
    }
  }
}
