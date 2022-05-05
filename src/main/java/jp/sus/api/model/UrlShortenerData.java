package jp.sus.api.model;

import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * URL shortener data.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlShortenerData {
  /** Identity. */
  private String id;
  /** URL before shortening. */
  @NotEmpty
  @Length(max = 2048)
  private String originalUrl;
  /** URL shortening expiration date. */
  private String expire;
  /** Timestamp. */
  private String createdTimestamp;
}
