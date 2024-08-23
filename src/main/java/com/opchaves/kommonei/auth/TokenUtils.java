package com.opchaves.kommonei.auth;

import java.util.HashSet;
import java.util.Set;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtSignatureException;

public class TokenUtils {

  public static String generateToken(TokenPayload params) throws JwtSignatureException {
    Set<String> groups = new HashSet<>();
    for (Role role : params.roles) {
      groups.add(role.toString());
    }

    String token = Jwt.issuer(params.issuer)
        .upn(params.email)
        .subject(params.id)
        .groups(groups)
        .issuedAt(currentTimeInSecs())
        .expiresAt(currentTimeInSecs() + params.duration)
        .claim("email", params.email)
        .sign();

    return token;
  }

  public static int currentTimeInSecs() {
    long currentTimeMS = System.currentTimeMillis();
    return (int) (currentTimeMS / 1000);
  }
}
