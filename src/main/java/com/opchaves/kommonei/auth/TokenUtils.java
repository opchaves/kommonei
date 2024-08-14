package com.opchaves.kommonei.auth;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import io.smallrye.jwt.build.Jwt;

public class TokenUtils {

  public static String generateToken(TokenPayload params) throws Exception {
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

  // NOTE: privatekey is read from `smallrye.jwt.sign.key.location`
  /*
   * public static PrivateKey readPrivateKey(final String pemResName) throws
   * Exception {
   * try (InputStream contentIS =
   * TokenUtils.class.getResourceAsStream(pemResName)) {
   * byte[] tmp = new byte[4096];
   * int length = contentIS.read(tmp);
   * return decodePrivateKey(new String(tmp, 0, length, "UTF-8"));
   * }
   * }
   *
   * public static PrivateKey decodePrivateKey(String pemEncoded) throws Exception
   * {
   * byte[] encodedBytes = toEncodedBytes(pemEncoded);
   * PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedBytes);
   * KeyFactory kf = KeyFactory.getInstance("RSA");
   * return kf.generatePrivate(keySpec);
   * }
   *
   * public static byte[] toEncodedBytes(final String pemEncoded) {
   * final String normalizedPem = removeBeginEnd(pemEncoded);
   * return Base64.getDecoder().decode(normalizedPem);
   * }
   *
   * public static String removeBeginEnd(String pem) {
   * pem = pem.replaceAll("-----BEGIN (.*)-----", "");
   * pem = pem.replaceAll("-----END (.*)----", "");
   * pem = pem.replaceAll("\r\n", "");
   * pem = pem.replaceAll("\n", "");
   * return pem.trim();
   * }
   */

  public static int currentTimeInSecs() {
    long currentTimeMS = System.currentTimeMillis();
    return (int) (currentTimeMS / 1000);
  }
}
