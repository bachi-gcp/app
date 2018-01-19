package com.biarca.app.ws.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biarca.app.ws.domain.AppUserEntity;
import com.biarca.app.ws.repository.AppUserRepository;
import com.biarca.app.ws.service.AppUserService;


@Service
public class AppUserServiceBean implements AppUserService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AppUserServiceBean.class);

  @Autowired
  private AppUserRepository appUserRepository;

  @Override
  public Collection<AppUserEntity> findAll() {
    return appUserRepository.findAll();
  }

  @Override
  public Optional<AppUserEntity> getUserById(String id) {
    LOGGER.debug("Getting app user = {}", id);
    return Optional.ofNullable(appUserRepository.findOne(id));
  }

  @Override
  public Optional<AppUserEntity> getUserByEmail(String email) {
    LOGGER.debug("Getting app user by email = {}", email);
    return appUserRepository.findOneByEmail(email);
  }

  @Override
  public AppUserEntity create(AppUserEntity user) {
    if (appUserRepository.exists(user.getId())) {
      return null;
    }
    LOGGER.debug("Creating app user = {}", user);
    return appUserRepository.saveAndFlush(user);
  }

  @Override
  public AppUserEntity update(AppUserEntity user) {
    if (!appUserRepository.exists(user.getId())) {
      return null;
    }

    LOGGER.debug("Updating app user = {}", user);
    return appUserRepository.saveAndFlush(user);
  }

  @Override
  public void delete(String id) {
    LOGGER.debug("Deleting app user = {}", id);
    appUserRepository.delete(id);
  }

  @Override
  public Collection<AppUserEntity> getUserByOwner(AppUserEntity owner) {
    LOGGER.debug("Getting app user by owner = {}", owner);
    return appUserRepository.getAppUserByOwner(owner);
  }
}
