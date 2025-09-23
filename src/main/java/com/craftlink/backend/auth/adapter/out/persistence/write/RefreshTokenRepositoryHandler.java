package com.craftlink.backend.auth.adapter.out.persistence.write;

import com.craftlink.backend.auth.adapter.out.persistence.mapper.RefreshTokenPersistenceMapper;
import com.craftlink.backend.auth.application.port.out.write.RefreshTokenRepository;
import com.craftlink.backend.auth.domain.model.refreshToken.RefreshToken;
import com.craftlink.backend.auth.domain.model.refreshToken.vo.RefreshTokenValue;
import com.craftlink.backend.shared.domain.vo.UserId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshTokenRepositoryHandler implements RefreshTokenRepository {

  private final RefreshTokenRepositoryJpa jpa;
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

  @Override
  public void deleteByToken(RefreshTokenValue token) {
    jpa.deleteByToken(token.value());
  }
}
