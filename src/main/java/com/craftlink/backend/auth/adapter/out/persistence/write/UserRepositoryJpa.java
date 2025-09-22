package com.craftlink.backend.auth.adapter.out.persistence.write;

import com.craftlink.backend.auth.adapter.out.persistence.UserEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJpa extends JpaRepository<UserEntity, UUID> {

  boolean existsByEmail(String email);
}
