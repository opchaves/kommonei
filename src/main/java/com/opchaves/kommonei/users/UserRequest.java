package com.opchaves.kommonei.users;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.opchaves.kommonei.jwt.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "User Request Object")
public class UserRequest {
  final static String REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{10,}$";
  final static String REGEX_MESSAGE = "Password must have at least 10 characters, one uppercase letter, one lowercase letter, one number and one special character";

  @Schema(required = true, example = "John Doe", description = "User name")
  @NotBlank(message = "Name is required")
  public String name;

  @Schema(required = true, example = "jonhdoe@example.com", description = "User email")
  @NotBlank(message = "Email is required")
  @Email
  public String email;

  public Set<Role> roles = new HashSet<>(Arrays.asList(Role.USER));

  /**
   * This regex will enforce these rules:
   * - At least one upper case English letter, (?=.*?[A-Z])
   * - At least one lower case English letter, (?=.*?[a-z])
   * - At least one digit, (?=.*?[0-9])
   * - At least one special character, (?=.*?[#?!@$%^&*-])
   * - Minimum eight in length .{8,} (with the anchors)
   *
   * @see https://stackoverflow.com/a/19605207
   */
  @Schema(required = true, example = "ab39cD-$d22", description = REGEX_MESSAGE, minLength = 10, pattern = REGEX)
  @NotBlank(message = "Password is required")
  @Pattern(regexp = REGEX, message = REGEX_MESSAGE)
  public String password;

  public UserRequest() {
  }

  public UserRequest(String name, String email, String password, Set<Role> roles) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  public User toEntity() {
    return new User(name, email, password, roles);
  }
}
