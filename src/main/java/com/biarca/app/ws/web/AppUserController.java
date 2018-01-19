package com.biarca.app.ws.web;

import java.security.Principal;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.biarca.app.ws.domain.AppUserEntity;
import com.biarca.app.ws.service.AppUserService;
import com.biarca.app.ws.web.layout.Layout;

@Controller
public class AppUserController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AppUserController.class);

  @Autowired
  private AppUserService appUserService;

  @Layout(value = "layouts/default")
  @RequestMapping("/app/dashboard")
  public ModelAndView getUserPage(HttpSession session, @RequestParam Optional<String> error,
      Principal principal) {
    LOGGER.info("Getting app user page for user = {}");

    ModelAndView modelView = new ModelAndView("user/dashboard", "error", error);
    return modelView;
  }

  @Layout(value = "layouts/default")
  @RequestMapping("/app/users")
  public ModelAndView getUsersPage(HttpSession session, @RequestParam Optional<String> error,
      Principal principal) {
    LOGGER.info("Getting app users page for user = {}");

    ModelAndView modelView = new ModelAndView("user/users", "error", error);
    List<AppUserEntity> createdUsers = null;
    /* Populate the users he has created and display in the users list */
    createdUsers = (List<AppUserEntity>) appUserService.findAll();
    modelView.addObject("users", createdUsers);
    return modelView;
  }

  @Layout(value = "layouts/default")
  @RequestMapping("/app/create_user")
  public String createUser(HttpSession session, AppUserEntity appUserEntity,
      @RequestParam Optional<String> error, Principal principal) {
    LOGGER.info("Creating app user  = {}");

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String hashedPassword = passwordEncoder.encode(appUserEntity.getPassword());
    appUserEntity.setPassword(hashedPassword);

    appUserEntity.setCreatedTime(Calendar.getInstance());

    Optional<AppUserEntity> owner = appUserService.getUserById(principal.getName());
    appUserEntity.setOwner(owner.get().getOwner());

    appUserService.create(appUserEntity);
    passwordEncoder = null;
    return "redirect:/app/users";
  }

  @Layout(value = "layouts/default")
  @RequestMapping(value = "/app/edit_user", method = RequestMethod.GET)
  public ModelAndView editUser(@RequestParam String userId, @RequestParam Optional<String> error,
      Principal principal) {
    LOGGER.info("Viewing user details={}");

    ModelAndView modelView = new ModelAndView("user/user", "error", error);

    AppUserEntity selUser = appUserService.getUserById(userId).get();

    modelView.addObject("makeDisabled", new Boolean(true));
    modelView.addObject("user", selUser);
    return modelView;
  }

  @Layout(value = "layouts/default")
  @RequestMapping("/app/services")
  public ModelAndView getServicesPage(HttpSession session, @RequestParam Optional<String> error,
      Principal principal) {
    LOGGER.info("Getting app services page for user = {}");
    Collection<AppUserEntity> createdUsers = null;
    ModelAndView modelView = new ModelAndView("user/services", "error", error);
    /* Populate the users he has created and display in the users list */
    createdUsers = appUserService.findAll();
    modelView.addObject("users", createdUsers);
    return modelView;
  }


  @Layout(value = "layouts/default")
  @RequestMapping("/app/about")
  public ModelAndView getAboutPage(HttpSession session, @RequestParam Optional<String> error,
      Principal principal) {
    LOGGER.info("Getting app about page for user = {}");

    ModelAndView modelView = new ModelAndView("user/about", "error", error);
    return modelView;
  }

  @Layout(value = "layouts/default")
  @RequestMapping("/app/contact")
  public ModelAndView getContactPage(HttpSession session, @RequestParam Optional<String> error,
      Principal principal) {
    LOGGER.info("Getting app contact page for user = {}");

    ModelAndView modelView = new ModelAndView("user/contact", "error", error);
    return modelView;
  }
}
