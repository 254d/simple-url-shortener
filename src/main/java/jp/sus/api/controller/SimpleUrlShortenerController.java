package jp.sus.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jp.sus.api.model.ResponseJsonForm;
import jp.sus.api.model.UrlShortenerData;
import jp.sus.api.service.SimpleUrlShortenerService;

/**
 * Controller.
 */
@RestController
@RequestMapping("/api")
public class SimpleUrlShortenerController {
  /** Service. */
  @Autowired
  private SimpleUrlShortenerService service;

  /**
   * Retrieve all URL shortener data.
   * 
   * @return all URL shortener data
   */
  @GetMapping("/")
  public ResponseEntity<Object> all() {

    List<UrlShortenerData> response = service.all();

    return ResponseEntity.status(200).body(response);
  }

  /**
   * Retrieve URL shortener data.
   * 
   * @param id ID
   * @return URL shortener data
   */
  @GetMapping("/{id}")
  public ResponseEntity<Object> findById(@PathVariable("id") String id) {
    UrlShortenerData response = service.findById(id);

    if (null == response) {
      ResponseJsonForm errorResponse = new ResponseJsonForm();
      errorResponse.setMessage("Not found.");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  /**
   * URL shortener data registration.
   * 
   * @param newItem URL shortener data
   * @param result binding results
   * @return post registration URL shortener data
   */
  @PostMapping("/")
  public ResponseEntity<Object> create(@RequestBody @Validated UrlShortenerData newItem,
      BindingResult result) {
    if (result.hasErrors()) {
      ResponseJsonForm response = new ResponseJsonForm();
      response.setMessage(result.getFieldError().getDefaultMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    UrlShortenerData response = service.create(newItem);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
