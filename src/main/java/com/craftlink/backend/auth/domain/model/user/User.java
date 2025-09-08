package com.craftlink.backend.auth.domain.model.user;

import com.craftlink.backend.auth.domain.model.authority.vo.AuthorityCode;
import com.craftlink.backend.auth.domain.model.refreshToken.vo.RefreshTokenId;
import com.craftlink.backend.auth.domain.model.user.vo.ClientId;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import com.craftlink.backend.auth.domain.model.user.vo.Password;
import com.craftlink.backend.auth.domain.model.user.vo.SpecialistId;
import com.craftlink.backend.auth.domain.model.user.vo.UserId;
import com.craftlink.backend.auth.domain.model.user.vo.UserType;
import com.craftlink.backend.shared.domain.model.vo.AggregateRoot;
import com.craftlink.backend.shared.exceptions.DomainViolation;
import java.util.Set;
import lombok.Getter;

@Getter
public final class User extends AggregateRoot {

  private final UserId id;
  //  private final PhoneNumber phoneNumber;
  private final Email email;
  private final Password password;
  private final UserType userType;
  private final Set<AuthorityCode> authorities;
  private final ClientId clientId;
  private final SpecialistId specialistId;
  private final RefreshTokenId refreshTokenId;

  private User(UserId id, Email email, Password password,
      UserType userType, Set<AuthorityCode> authorities, ClientId clientId, SpecialistId specialistId,
      RefreshTokenId refreshTokenId) {
    this.id = id;
//    this.phoneNumber = phoneNumber;
    this.email = email;
    this.password = password;
    this.userType = userType;
    this.authorities = authorities;
    this.clientId = clientId;
    this.specialistId = specialistId;
    this.refreshTokenId = refreshTokenId;
  }

  public static User registerUser(Email email,
      Password password, Set<AuthorityCode> authorities) {
    var id = UserId.newId();

    return new User(id, email, password,
        UserType.SPECIALIST, authorities, null, null, null);
  }

  public static User createAdmin(UserId id, Email email,
      Password password, Set<AuthorityCode> authorities) {
    return new User(id, email, password,
        UserType.ADMIN, authorities, null, null, null);
  }

  public void assignClientId(ClientId clientId) {
    if (this.clientId != null) {
      throw new DomainViolation("CLIENT_ALREADY_ASSIGNED",
          "Client is already assigned");
    }
  }

  public void assignSpecialistId(SpecialistId specialistId) {
    if (this.specialistId != null) {
      throw new DomainViolation("SPECIALIST_ALREADY_ASSIGNED",
          "Specialist is already assigned");
    }
  }

  public void assignRefreshTokenId(RefreshTokenId refreshTokenId) {
    if (this.refreshTokenId != null) {
      throw new DomainViolation("REFRESH_TOKEN_ALREADY_ASSIGNED",
          "Refresh token is already assigned");
    }
  }

//  public static User rehydrate(UserId id, PhoneNumber phoneNumber, Email email, Password password,
//      UserType userType, Set<AuthorityCode> authorities, ClientId clientId, SpecialistId specialistId) {
//    return new User(id, phoneNumber, email, password, userType, authorities, clientId, specialistId);
//  }
}
