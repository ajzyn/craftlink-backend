package com.craftlink.backend.auth.adapter.out.persistence.write;

import com.craftlink.backend.auth.adapter.out.persistence.AuthorityEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityQueryRepositorySpringData extends JpaRepository<AuthorityEntity, Integer> {

  Optional<AuthorityEntity> findByCode(String code);
}
