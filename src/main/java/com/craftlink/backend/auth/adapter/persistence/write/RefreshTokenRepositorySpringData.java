package com.craftlink.backend.auth.adapter.persistence.write;

import com.craftlink.backend.auth.adapter.persistence.RefreshTokenEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepositorySpringData extends JpaRepository<RefreshTokenEntity, UUID> {

  Optional<RefreshTokenEntity> findByToken(String token);

  void deleteByUserId(UUID userId);

  void deleteByToken(String token);
}
