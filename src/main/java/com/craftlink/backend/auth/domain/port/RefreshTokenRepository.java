package com.craftlink.backend.auth.domain.port;

import com.craftlink.backend.auth.domain.model.refreshToken.RefreshToken;
import com.craftlink.backend.auth.domain.model.refreshToken.vo.RefreshTokenValue;
import com.craftlink.backend.auth.domain.model.user.vo.UserId;
import java.util.Optional;

public interface RefreshTokenRepository {

  RefreshToken save(RefreshToken refreshToken);

  Optional<RefreshToken> findByToken(RefreshTokenValue token);

  void deleteByUserId(UserId id);

  void delete(RefreshToken refreshToken);
}
