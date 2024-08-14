package com.opchaves.kommonei.auth;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(name = "AuthRequest")
public class AuthRequest {
  final static String REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{10,}$";
  final static String REGEX_MESSAGE = "Password must have at least 10 characters, one uppercase letter, one lowercase letter, one number and one special character";

  @Schema(required = true, example = "jonhdoe@example.com", description = "User email")
  @NotBlank(message = "Email is required")
  @Email
  private String email;

  @Schema(required = true, example = "ab39cD-$d22", description = REGEX_MESSAGE, minLength = 10, pattern = REGEX)
  @NotBlank(message = "Password is required")
  @Pattern(regexp = REGEX, message = REGEX_MESSAGE)
  private String password;

  public AuthRequest() {
  }

  public AuthRequest(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
