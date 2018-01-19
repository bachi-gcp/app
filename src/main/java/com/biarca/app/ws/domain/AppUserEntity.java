package com.biarca.app.ws.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "appuser")
public class AppUserEntity implements Serializable {
  private static final long serialVersionUID = 323252700227935319L;

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "password")
  private String password;

  @Column(name = "name")
  private String name;

  @Column(name = "emailid")
  private String email;

  @Column(name = "is_admin")
  private boolean adminStatus;

  @Column(name = "created_time")
  @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
  private Calendar createdTime;

  @Column(name = "status")
  private boolean activeStatus;

  @ManyToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "created_by")
  private AppUserEntity owner;

  @OneToMany(mappedBy = "owner")
  private Set<AppUserEntity> appUsers = new HashSet<AppUserEntity>();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean getAdminStatus() {
    return adminStatus;
  }

  public void setAdminStatus(boolean adminStatus) {
    this.adminStatus = adminStatus;
  }

  public Calendar getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Calendar createdTime) {
    this.createdTime = createdTime;
  }

  public boolean getActiveStatus() {
    return activeStatus;
  }

  public void setActiveStatus(boolean activeStatus) {
    this.activeStatus = activeStatus;
  }

  public AppUserEntity getOwner() {
    return owner;
  }

  public void setOwner(AppUserEntity owner) {
    this.owner = owner;
  }

  public Set<AppUserEntity> getAppUsers() {
    return appUsers;
  }

  public void setAppUsers(Set<AppUserEntity> appUsers) {
    this.appUsers = appUsers;
  }

  @Override
  public String toString() {
    return "AppUserEntity{id=" + id + ",name=" + name + ",email id=" + email + "}";
  }

  @Override
  public int hashCode() {
    return this.id.hashCode() + this.password.hashCode() + this.name.hashCode();
  }

  @Override
  public boolean equals(Object u) {
    if (u instanceof AppUserEntity) {
      AppUserEntity appUserEntity = (AppUserEntity) u;

      if (this.id.equals(appUserEntity.getId()) && this.password.equals(appUserEntity.getPassword())
          && this.name.equals(appUserEntity.getName())) {
        return true;
      }
    }
    return false;
  }

  public Role getRole() {
    if (this.adminStatus) {
      return Role.ADMIN;
    } else {
      return Role.USER;
    }
  }
}
