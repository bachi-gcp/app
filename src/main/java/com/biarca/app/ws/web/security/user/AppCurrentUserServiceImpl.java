package com.biarca.app.ws.web.security.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biarca.app.ws.domain.AppCurrentUser;
import com.biarca.app.ws.domain.Role;


public class AppCurrentUserServiceImpl implements AppCurrentUserService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AppCurrentUserServiceImpl.class);

  @Override
  public boolean canAccessUser(AppCurrentUser currentUser, String userId) {
    LOGGER.debug("Checking if app user={} has access to the current login user = {}", currentUser,
        userId);
    return currentUser != null
        && (currentUser.getRole() == Role.ADMIN || currentUser.getId().equals(userId));
  }

}
