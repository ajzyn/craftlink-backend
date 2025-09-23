package com.craftlink.backend.auth.domain.model.user;

import com.craftlink.backend.auth.domain.model.user.vo.AuthorityCode;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import com.craftlink.backend.auth.domain.model.user.vo.Password;
import com.craftlink.backend.auth.domain.model.user.vo.UserType;
import com.craftlink.backend.auth.domain.model.user.vo.Username;
import com.craftlink.backend.shared.domain.vo.UserId;
import java.util.Set;
import lombok.Getter;

@Getter
public final class User {

  private final UserId id;
  //  private final PhoneNumber phoneNumber;
  private final Email email;
  private final Username username;
  private final Password password;
  private final UserType userType;
  private final Set<AuthorityCode> authorities;

  private User(UserId id, Email email, Username username, Password password,
      UserType userType, Set<AuthorityCode> authorities) {
    this.id = id;
//    this.phoneNumber = phoneNumber;
    this.username = username;
    this.email = email;
    this.password = password;
    this.userType = userType;
    this.authorities = authorities;
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
}
