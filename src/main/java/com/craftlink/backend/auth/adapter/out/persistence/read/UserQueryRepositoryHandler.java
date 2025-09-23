package com.craftlink.backend.auth.adapter.out.persistence.read;

import com.craftlink.backend.auth.adapter.out.persistence.AuthorityEntity;
import com.craftlink.backend.auth.adapter.out.persistence.UserEntity;
import com.craftlink.backend.auth.application.port.in.query.getUserProfile.UserAuthView;
import com.craftlink.backend.auth.application.port.in.query.getUserProfile.UserView;
import com.craftlink.backend.auth.application.port.out.read.UserQueryRepository;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import com.craftlink.backend.category.adapter.out.persistance.ServiceEntity;
import com.craftlink.backend.shared.domain.vo.UserId;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserQueryRepositoryHandler implements UserQueryRepository {

  private final UserQueryRepositoryJpa jpa;

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
        .map(this::toAuthView);
  }

  @Override
  public Optional<UserAuthView> findByIdWithServices(UserId id) {
    return jpa.findByIdWithServices(id.value())
        .map(this::toAuthView);
  }

  private UserAuthView toAuthView(UserEntity user) {
    return new UserAuthView(
        user.getId(),
        user.getClient() != null ? user.getClient().getId() : null,
        user.getSpecialist() != null ? user.getSpecialist().getId() : null,
        user.getEmail(),
        user.getPassword(),
        user.getUserType(),
        user.getAuthorities().stream()
            .map(AuthorityEntity::getCode)
            .collect(Collectors.toSet()),
        user.getSpecialist() != null && user.getSpecialist().getOfferedServices() != null
            ? user.getSpecialist().getOfferedServices().stream()
            .map(ServiceEntity::getSlug)
            .collect(Collectors.toSet())
            : Set.of()
    );
  }
}
