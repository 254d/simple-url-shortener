package jp.sus.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import jp.sus.web.service.UrlShortenerWebService;

@Controller
public class UrlShortenerWebController {
  /** Service. */
  @Autowired
  private UrlShortenerWebService service;

  @GetMapping("/{id}")
  public ModelAndView redirect(@PathVariable("id") String id) {
    String originalUrl = service.getDestenation(id);
    if (null == originalUrl) {
      return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
    }
    return new ModelAndView("redirect:" + originalUrl);
  }
}
