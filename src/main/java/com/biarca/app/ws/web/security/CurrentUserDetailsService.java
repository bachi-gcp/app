package com.biarca.app.ws.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biarca.app.ws.domain.AppCurrentUser;
import com.biarca.app.ws.domain.AppUserEntity;
import com.biarca.app.ws.service.AppUserService;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsService.class);

  private final AppUserService userService;

  @Autowired
  public CurrentUserDetailsService(AppUserService userService) {
    this.userService = userService;
  }

  @Override
  public AppCurrentUser loadUserByUsername(String id) throws UsernameNotFoundException {
    AppUserEntity user = userService.getUserById(id).orElseThrow(
        () -> new UsernameNotFoundException(String.format("User with id=%s was not found", id)));

    AppCurrentUser currentUser = null;
    if (user.getActiveStatus()) {
      LOGGER.debug("Authenticating user with id = {}", id);
      currentUser = new AppCurrentUser(user);
    }

    if (currentUser == null) {
      String message = String.format("User with id=%s is disabled = {}", id);
      LOGGER.info(message);
      throw new UsernameNotFoundException(message);
    }
    return currentUser;
  }

}
