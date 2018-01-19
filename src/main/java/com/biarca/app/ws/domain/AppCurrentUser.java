package com.biarca.app.ws.domain;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class AppCurrentUser extends User {
  private static final long serialVersionUID = 1977217205557818587L;
  private AppUserEntity appUserEntity;

  public AppCurrentUser(AppUserEntity userEntity) {
    super(userEntity.getId(), userEntity.getPassword(),
        AuthorityUtils.createAuthorityList(userEntity.getRole().toString()));
    this.appUserEntity = userEntity;
  }

  public AppUserEntity getUser() {
    return appUserEntity;
  }

  public String getId() {
    return appUserEntity.getId();
  }

  public Role getRole() {
    return appUserEntity.getRole();
  }

  public boolean isAdmin() {
    return appUserEntity.getAdminStatus();
  }

  @Override
  public String toString() {
    return "AppCurrentUser{" + "user=" + appUserEntity + "} " + super.toString();
  }
}
