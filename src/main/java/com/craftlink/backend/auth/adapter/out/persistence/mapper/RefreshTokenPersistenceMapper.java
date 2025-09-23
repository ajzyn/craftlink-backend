package com.craftlink.backend.auth.adapter.out.persistence.mapper;

import com.craftlink.backend.auth.adapter.out.persistence.RefreshTokenEntity;
import com.craftlink.backend.auth.adapter.out.persistence.UserEntity;
import com.craftlink.backend.auth.domain.model.refreshToken.RefreshToken;
import com.craftlink.backend.auth.domain.model.refreshToken.vo.ExpirationDate;
import com.craftlink.backend.auth.domain.model.refreshToken.vo.RefreshTokenId;
import com.craftlink.backend.auth.domain.model.refreshToken.vo.RefreshTokenValue;
import com.craftlink.backend.shared.domain.vo.UserId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RefreshTokenPersistenceMapper {

  default RefreshTokenEntity toEntity(RefreshToken refreshToken) {
    var refreshTokenEntity = new RefreshTokenEntity();
    refreshTokenEntity.setToken(refreshToken.getToken().value());
    refreshTokenEntity.setExpirationDate(refreshToken.getExpirationDate().value());

    var user = new UserEntity();
    user.setId(refreshToken.getUserId().value());

    refreshTokenEntity.setUser(user);

    return refreshTokenEntity;
  }

  default RefreshToken toDomain(RefreshTokenEntity refreshTokenEntity) {
    return RefreshToken.rehydrate(
        new RefreshTokenId(refreshTokenEntity.getId()),
        new UserId(refreshTokenEntity.getUser().getId()),
        new RefreshTokenValue(refreshTokenEntity.getToken()),
        new ExpirationDate(refreshTokenEntity.getExpirationDate())
    );
  }
}
