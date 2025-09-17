package com.craftlink.backend.auth.adapter.out.persistence.read;

import com.craftlink.backend.auth.adapter.out.persistence.UserEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQueryRepositorySpringData extends JpaRepository<UserEntity, UUID> {

  Optional<UserEntity> findByEmail(String email);

  @Query("""
      SELECT u FROM UserEntity u
      LEFT JOIN FETCH u.specialist s
      LEFT JOIN FETCH s.offeredServices
      WHERE u.id = :id
      """)
  Optional<UserEntity> findByIdWithServices(UUID id);

  @Query("""
      SELECT u FROM UserEntity u
      LEFT JOIN FETCH u.specialist s
      LEFT JOIN FETCH s.offeredServices
      WHERE u.email = :email
      """)
  Optional<UserEntity> findByEmailWithServices(String email);
}
