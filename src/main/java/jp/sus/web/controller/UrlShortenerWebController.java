package jp.sus.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import jp.sus.web.service.UrlShortenerWebService;
import lombok.extern.slf4j.Slf4j;

/**
 * UrlShortenerWebController.
 */
@Slf4j
@Controller
public class UrlShortenerWebController {
  /** Service. */
  @Autowired
  private UrlShortenerWebService service;

  /**
   * Redirect
   * 
   * @param id ID
   * @return redirect
   */
  @GetMapping("/{id}")
  public ModelAndView redirect(@PathVariable("id") String id) {
    log.info("id = " + id);
    String originalUrl = service.getDestenation(id);
    if (null == originalUrl) {
      log.info("NOT FOUND !!");
      return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
    }
    log.info("FOUND");
    return new ModelAndView("redirect:" + originalUrl);
  }
}
