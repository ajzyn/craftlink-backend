package com.craftlink.backend.auth.adapter.persistence.read;

import com.craftlink.backend.auth.adapter.persistence.RefreshTokenEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataRefreshTokenRepositoryJpa extends JpaRepository<RefreshTokenEntity, Integer> {

  Optional<RefreshTokenEntity> findByToken(String token);

  void deleteByUserId(UUID userId);
}
