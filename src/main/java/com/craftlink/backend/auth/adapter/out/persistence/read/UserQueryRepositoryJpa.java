package com.craftlink.backend.auth.adapter.out.persistence.read;

import com.craftlink.backend.auth.adapter.out.persistence.UserEntity;
import java.util.Optional;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQueryRepositoryJpa extends JpaRepository<UserEntity, UUID> {


  @NotNull
  @EntityGraph(attributePaths = {"authorities"})
  Optional<UserEntity> findById(@NotNull UUID id);

  @EntityGraph(attributePaths = {"authorities"})
  Optional<UserEntity> findByEmail(String email);


  @EntityGraph(attributePaths = {"authorities", "specialist.offeredServices"})
  @Query("SELECT u FROM UserEntity u WHERE u.id = :id")
  Optional<UserEntity> findByIdWithServices(UUID id);

  @EntityGraph(attributePaths = {"authorities", "specialist.offeredServices"})
  @Query("SELECT u FROM UserEntity u WHERE u.email = :email")
  Optional<UserEntity> findByEmailWithServices(String email);
}
