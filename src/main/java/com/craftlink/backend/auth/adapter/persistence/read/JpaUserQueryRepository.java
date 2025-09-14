package com.craftlink.backend.auth.adapter.persistence.read;

import com.craftlink.backend.auth.adapter.persistence.AuthorityEntity;
import com.craftlink.backend.auth.application.dto.UserAuthView;
import com.craftlink.backend.auth.application.dto.UserView;
import com.craftlink.backend.auth.application.port.repository.UserQueryRepository;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import com.craftlink.backend.auth.domain.model.user.vo.UserId;
import com.craftlink.backend.service.entities.ServiceEntity;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaUserQueryRepository implements UserQueryRepository {

  private final UserQueryRepositorySpringData jpa;

  @Override
  public Optional<UserView> findByEmail(Email email) {
    return jpa.findByEmail(email.value()).map(entity ->
        new UserView(
            entity.getId(),
            entity.getEmail(),
            entity.getUserType(),
            entity.getAuthorities().stream().map(AuthorityEntity::getCode).collect(Collectors.toSet())
        )
    );
  }

  @Override
  public Optional<UserView> findById(UserId id) {
    return jpa.findById(id.value()).map(entity ->
        new UserView(
            entity.getId(),
            entity.getEmail(),
            entity.getUserType(),
            entity.getAuthorities().stream().map(AuthorityEntity::getCode).collect(Collectors.toSet())
        )
    );
  }

  @Override
  public Optional<UserAuthView> findByEmailWithServices(Email email) {
    return jpa.findByEmailWithServices(email.value())
        .map(user -> new UserAuthView(
            user.getId(),
            user.getClient() != null ? user.getClient().getId() : null,
            user.getSpecialist() != null ? user.getSpecialist().getId() : null,
            user.getEmail(),
            user.getPassword(),
            user.getUserType(),
            user.getAuthorities().stream().map(AuthorityEntity::getCode).collect(Collectors.toSet()),
            user.getSpecialist() != null && user.getSpecialist().getOfferedServices() != null
                ? user.getSpecialist().getOfferedServices().stream().map(ServiceEntity::getSlug)
                .collect(Collectors.toSet())
                : Set.of()
        ));
  }

  @Override
  public Optional<UserAuthView> findByIdWithServices(UserId id) {
    return jpa.findByIdWithServices(id.value())
        .map(user -> new UserAuthView(
            user.getId(),
            user.getClient() != null ? user.getClient().getId() : null,
            user.getSpecialist() != null ? user.getSpecialist().getId() : null,
            user.getEmail(),
            user.getPassword(),
            user.getUserType(),
            user.getAuthorities().stream().map(AuthorityEntity::getCode).collect(Collectors.toSet()),
            user.getSpecialist() != null && user.getSpecialist().getOfferedServices() != null
                ? user.getSpecialist().getOfferedServices().stream().map(ServiceEntity::getSlug)
                .collect(Collectors.toSet())
                : Set.of()
        ));
  }
}
