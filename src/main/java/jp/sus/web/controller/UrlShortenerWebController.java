package jp.sus.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
  /** URL of the API to call. */
  @Value("${jp.shortener.web.api.host:}")
  private String apiHost;
  /** Server port number. */
  @Value("${server.port:8080}")
  private String port;

  /**
   * Index
   * 
   * @param model
   * @return index
   */
  @GetMapping("/")
  public String index(Model model) {
    String serviceUrl = "http://" + apiHost + ":" + port + "/";
    String apiUrl = serviceUrl + "api/";
    model.addAttribute("apiUrl", apiUrl);
    model.addAttribute("serviceUrl", serviceUrl);
    return "index";
  }

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
