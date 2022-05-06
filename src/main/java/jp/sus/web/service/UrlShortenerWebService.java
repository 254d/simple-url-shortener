package jp.sus.web.service;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import jp.sus.web.model.UrlShortenerData;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UrlShortenerWebService {
  @Value("${jp.shortener.web.api.url:}")
  private String apiUrl;

  private WebClient webClient = WebClient.builder().build();

  public String getDestenation(String id) {
    UrlShortenerData response = getSingleDataByApi(id);
    if (null == response) {
      return null;
    }
    return response.getOriginalUrl();
  }

  private UrlShortenerData getSingleDataByApi(String id) {
    try {
      return webClient.get().uri(apiUrl + id).retrieve().bodyToMono(UrlShortenerData.class).block();
    } catch (Exception e) {
      log.debug(ExceptionUtils.getStackTrace(e));
      return null;
    }
  }

}
