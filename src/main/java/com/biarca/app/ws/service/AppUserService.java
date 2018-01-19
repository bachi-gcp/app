package com.biarca.app.ws.service;

import java.util.Collection;
import java.util.Optional;

import com.biarca.app.ws.domain.AppUserEntity;


public interface AppUserService {
  public Collection<AppUserEntity> findAll();

  public Optional<AppUserEntity> getUserById(String id);

  public Optional<AppUserEntity> getUserByEmail(String email);

  public AppUserEntity create(AppUserEntity user);

  // Should Not be implementing
  public AppUserEntity update(AppUserEntity user);

  // Should Not be implementing
  public void delete(String id);

  public Collection<AppUserEntity> getUserByOwner(AppUserEntity owner);
}
