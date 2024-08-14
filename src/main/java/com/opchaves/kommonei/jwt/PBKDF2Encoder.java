package com.opchaves.kommonei.jwt;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class PBKDF2Encoder {
  @ConfigProperty(name = "com.opchaves.kommonei.password.secret")
  private String secret;

  @ConfigProperty(name = "com.opchaves.kommonei.password.iterations")
  private Integer iterations;

  @ConfigProperty(name = "com.opchaves.kommonei.password.keylength")
  private Integer keyLength;

  public String encode(CharSequence cs) {
    try {
      var keySpec = new PBEKeySpec(cs.toString().toCharArray(), secret.getBytes(), iterations, keyLength);
      byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512").generateSecret(keySpec).getEncoded();

      return Base64.getEncoder().encodeToString(result);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
      throw new RuntimeException(ex);
    }
  }

  public Boolean verify(CharSequence cs, String encodedPassword) {
    return encode(cs).equals(encodedPassword);
  }
}
