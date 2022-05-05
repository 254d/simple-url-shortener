package jp.sus.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * General purpose response data.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseJsonForm {
  /** Message. */
  private String message;
}
