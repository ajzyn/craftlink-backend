package com.craftlink.backend.auth.adapter.persistence.read;

import com.craftlink.backend.auth.adapter.persistence.AuthorityEntity;
import com.craftlink.backend.auth.application.dto.UserSnapshot;
import com.craftlink.backend.auth.application.port.UserReadRepository;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import com.craftlink.backend.auth.domain.model.user.vo.UserId;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReadRepositoryJpaAdapter implements UserReadRepository {

  private final SpringDataUserReadRepositoryJpa jpa;

  @Override
  public Optional<UserSnapshot> findByEmail(Email email) {
    return jpa.findByEmail(email.value()).map(entity ->
        new UserSnapshot(
            entity.getId(),
            entity.getEmail(),
            entity.getUserType(),
            entity.getAuthorities().stream().map(AuthorityEntity::getCode).collect(Collectors.toSet())
        )
    );
  }

  @Override
  public Optional<UserSnapshot> findById(UserId id) {
    return jpa.findById(id.value()).map(entity ->
        new UserSnapshot(
            entity.getId(),
            entity.getEmail(),
            entity.getUserType(),
            entity.getAuthorities().stream().map(AuthorityEntity::getCode).collect(Collectors.toSet())
        )
    );
  }
}
