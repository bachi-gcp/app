package com.biarca.app.ws.web.security.user;

import com.biarca.app.ws.domain.AppCurrentUser;

public interface AppCurrentUserService {

  public boolean canAccessUser(AppCurrentUser currentUser, String userId);

}
