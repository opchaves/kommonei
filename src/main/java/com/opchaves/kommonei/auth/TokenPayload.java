package com.opchaves.kommonei.auth;

import java.util.Set;

import com.opchaves.kommonei.users.User;

public class TokenPayload {
  public String id;
  public String email;
  public Set<Role> roles;
  public String issuer;
  public Long duration;

  public TokenPayload() {
  }

  public TokenPayload(User user, String issuer, Long duration) {
    this.id = user.id.toString();
    this.email = user.email;
    this.roles = user.roles;
    this.issuer = issuer;
    this.duration = duration;
  }
}
