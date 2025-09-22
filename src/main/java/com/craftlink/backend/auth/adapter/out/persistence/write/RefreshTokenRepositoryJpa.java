package com.craftlink.backend.auth.adapter.out.persistence.write;

import com.craftlink.backend.auth.adapter.out.persistence.RefreshTokenEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepositoryJpa extends JpaRepository<RefreshTokenEntity, UUID> {

  Optional<RefreshTokenEntity> findByToken(String token);

  void deleteByUserId(UUID userId);

  void deleteByToken(String token);
}
