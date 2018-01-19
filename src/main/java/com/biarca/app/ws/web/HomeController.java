package com.biarca.app.ws.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biarca.app.ws.web.layout.Layout;

@Controller
public class HomeController {

  private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

  @Layout(value = "layouts/blank")
  @RequestMapping("/")
  public String getHomePage() {
    LOGGER.debug("Getting home page = {}");
    return "redirect:/app/login";
  }
}
