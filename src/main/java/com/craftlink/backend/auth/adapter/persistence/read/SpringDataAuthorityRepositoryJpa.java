package com.craftlink.backend.auth.adapter.persistence.read;

import com.craftlink.backend.auth.adapter.persistence.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataAuthorityRepositoryJpa extends JpaRepository<AuthorityEntity, Integer> {

}
