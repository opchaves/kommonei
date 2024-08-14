package com.opchaves.kommonei.users;

import java.time.LocalDateTime;
import java.util.Set;

import com.opchaves.kommonei.jwt.Role;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.smallrye.mutiny.Uni;

@MongoEntity(collection = "users")
public class User extends ReactivePanacheMongoEntity {
  public String name;
  public String email;
  public String password;
  public Set<Role> roles;
  public LocalDateTime createdAt;

  public User() {
  }

  public User(String name, String email, String password, Set<Role> roles) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  public UserRequest toRequest() {
    return new UserRequest(name, email, password, roles);
  }

  public UserResponse toResponse() {
    return new UserResponse(this);
  }

  public Uni<Boolean> emailAlreadyTaken() {
    return User.find("email", email).firstResult().map(u -> u != null);
  }
}
