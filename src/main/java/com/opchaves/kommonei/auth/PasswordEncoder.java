package com.opchaves.kommonei.auth;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class PasswordEncoder {

  @ConfigProperty(name = "com.opchaves.kommonei.password.iterations")
  private Integer iterations;

  public String encode(String passwrod) {
    return BcryptUtil.bcryptHash(passwrod, iterations);
  }

  public Boolean matches(String plainPassword, String encodedPassword) {
    return BcryptUtil.matches(plainPassword, encodedPassword);
  }
}
