package com.craftlink.backend.auth.adapter.persistence.write;

import com.craftlink.backend.auth.adapter.persistence.mapper.RefreshTokenPersistenceMapper;
import com.craftlink.backend.auth.adapter.persistence.read.SpringDataRefreshTokenRepositoryJpa;
import com.craftlink.backend.auth.domain.model.refreshToken.RefreshToken;
import com.craftlink.backend.auth.domain.model.refreshToken.vo.RefreshTokenValue;
import com.craftlink.backend.auth.domain.model.user.vo.UserId;
import com.craftlink.backend.auth.domain.port.token.RefreshTokenRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshTokenRepositoryAdapter implements RefreshTokenRepository {

  private final SpringDataRefreshTokenRepositoryJpa jpa;
  private final RefreshTokenPersistenceMapper mapper;


  @Override
  public RefreshToken save(RefreshToken refreshToken) {
    var entity = mapper.toEntity(refreshToken);
    jpa.save(entity);
    return mapper.toDomain(entity);
  }

  @Override
  public Optional<RefreshToken> findByToken(RefreshTokenValue token) {
    return jpa.findByToken(token.value()).map(mapper::toDomain);
  }

  @Override
  public void deleteByUserId(UserId id) {
    jpa.deleteByUserId(id.value());
  }

  @Override
  public void delete(RefreshToken refreshToken) {
    jpa.delete(mapper.toEntity(refreshToken));
  }
}
