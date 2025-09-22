package com.craftlink.backend.auth.adapter.out.persistence.mapper;

import com.craftlink.backend.auth.adapter.out.persistence.UserEntity;
import com.craftlink.backend.auth.domain.model.user.User;
import com.craftlink.backend.auth.domain.model.user.vo.AuthorityCode;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import com.craftlink.backend.auth.domain.model.user.vo.Password;
import com.craftlink.backend.auth.domain.model.user.vo.Username;
import com.craftlink.backend.shared.vo.UserId;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {


  default UserEntity toEntity(User user) {
    var userEntity = new UserEntity();
    userEntity.setId(user.getId().value());
    userEntity.setEmail(user.getEmail().value());
    userEntity.setUsername(user.getUsername().value());
    userEntity.setPassword(user.getPassword().value());
    userEntity.setUserType(user.getUserType());
    return userEntity;
  }

  default User toDomain(UserEntity entity) {
    var authorities = entity.getAuthorities().stream()
        .map(e -> new AuthorityCode(e.getCode()))
        .collect(Collectors.toSet());

    return User.rehydrate(
        new UserId(entity.getId()),
        new Email(entity.getEmail()),
        new Username(entity.getUsername()),
        new Password(entity.getPassword()),
        entity.getUserType(),
        authorities
    );
  }

}
