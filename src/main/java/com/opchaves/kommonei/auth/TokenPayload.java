package com.opchaves.kommonei.auth;

import java.util.Set;

public class TokenPayload {
  public String id;
  public String email;
  public Set<Role> roles;
  public String issuer;
  public Long duration;

  public TokenPayload() {
  }

  public TokenPayload(String id, String email, Set<Role> roles, String issuer, Long duration) {
    this.id = id;
    this.email = email;
    this.roles = roles;
    this.issuer = issuer;
    this.duration = duration;
  }
}
