package com.craftlink.backend.user.repositories;

import com.craftlink.backend.user.entities.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("""
        SELECT u FROM UserEntity u
        LEFT JOIN FETCH u.specialist s
        LEFT JOIN FETCH s.specializations
        WHERE u.email = :email
        """)
    Optional<UserEntity> findByEmailWithSpecializations(String email);
}
