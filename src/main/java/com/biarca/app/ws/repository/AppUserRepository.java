package com.biarca.app.ws.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biarca.app.ws.domain.AppUserEntity;


public interface AppUserRepository extends JpaRepository<AppUserEntity, String> {

  Optional<AppUserEntity> findOneByEmail(String email);

  Collection<AppUserEntity> getAppUserByOwner(AppUserEntity owner);

}
