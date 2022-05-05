package jp.sus.api.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jp.sus.api.mapper.SimpleUrlShortenerMapper;
import jp.sus.api.model.UrlShortenerData;
import lombok.extern.slf4j.Slf4j;

/**
 * SimpleUrlShortenerService.
 */
@Service
@Slf4j
public class SimpleUrlShortenerService {
  /** Length of ID to be generated. */
  @Value("${jp.shortener.id.length:8}")
  private int idLength;

  /** Mapper. */
  @Autowired
  private SimpleUrlShortenerMapper mapper;

  /**
   * Retrieve all URL shortener data.
   * 
   * @return all URL shortener data
   */
  public List<UrlShortenerData> all() {
    List<UrlShortenerData> allItems = mapper.all();
    return allItems;
  }

  /**
   * Retrieve URL shortener data.
   * 
   * @param id ID
   * @return URL shortener data
   */
  public UrlShortenerData findById(String id) {
    UrlShortenerData response = mapper.findById(id);
    return response;
  }

  /**
   * URL shortener data registration.
   * 
   * @param data URL shortener data
   * @return post registration URL shortener data
   */
  public UrlShortenerData create(UrlShortenerData data) {
    UrlShortenerData newItem = data;
    newItem.setId(getId(idLength));
    newItem.setCreatedTimestamp(getTimeStamp());

    Boolean result = mapper.create(newItem);

    if (!result) {
      // 更新失敗
      throw new RuntimeException();
    }

    return newItem;
  }

  /**
   * ID generation.
   * 
   * @param length Length of ID
   * @return ID
   */
  private String getId(int length) {
    String id = "";

    while (true) {
      // ID generation
      id = RandomStringUtils.randomAlphanumeric(length);
      // Existence check of generated IDs
      UrlShortenerData search = mapper.findById(id);
      if (null == search) {
        // If not present, exit loop
        break;
      } else {
        log.debug(search.toString());
      }
    }

    return id;
  }

  /**
   * Get timestamp(yyyyMMddHHmmssSSS).
   * 
   * @return timestamp
   */
  private String getTimeStamp() {
    LocalDateTime ldt = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    String timeStamp = format.format(ldt);

    return timeStamp;
  }

}
