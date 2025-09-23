package com.craftlink.backend.auth.domain.model.user;

import com.craftlink.backend.auth.domain.model.user.vo.AuthorityCode;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import com.craftlink.backend.auth.domain.model.user.vo.Password;
import com.craftlink.backend.auth.domain.model.user.vo.UserType;
import com.craftlink.backend.auth.domain.model.user.vo.Username;
import com.craftlink.backend.shared.domain.vo.UserId;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

@Getter
public final class User {

  private final UserId id;
  private final UserType userType;
  private Email email;
  private Username username;
  private Password password;
  private Set<AuthorityCode> authorityCodes;

  private User(UserId id, Email email, Username username, Password password,
      UserType userType, Set<AuthorityCode> authorityCodes) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.userType = userType;
    this.authorityCodes = new HashSet<>(authorityCodes);
  }

  public static User register(Email email, Username username,
      Password password, UserType userType, Set<AuthorityCode> authorities) {
    var id = UserId.newId();

    return new User(id, email, username, password,
        userType, authorities);
  }

  public static User rehydrate(UserId id, Email email, Username username, Password password,
      UserType userType, Set<AuthorityCode> authorities) {
    return new User(id, email, username, password, userType, authorities);
  }

  public void updateAuthorities(Set<AuthorityCode> authorityCodes) {
    this.authorityCodes = new HashSet<>(authorityCodes);
  }

  public void changePassword(Password newPassword) {
    this.password = newPassword;
  }

  public void changeDetails(Email newEmail, Username newUsername) {
    this.email = newEmail;
    this.username = newUsername;
  }
}
