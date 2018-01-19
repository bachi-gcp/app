package com.biarca.app.ws.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.biarca.app.ws.domain.AppCurrentUser;

@ControllerAdvice
public class CurrentUserControllerAdvice {

  @ModelAttribute("currentUser")
  public AppCurrentUser getCurrentUser(Authentication authentication) {
    return (authentication == null) ? null : (AppCurrentUser) authentication.getPrincipal();
  }
}
