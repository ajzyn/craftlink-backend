package com.craftlink.backend.auth.adapter.persistence.read;

import com.craftlink.backend.auth.adapter.persistence.AuthorityEntity;
import com.craftlink.backend.auth.application.dto.UserAuthView;
import com.craftlink.backend.auth.application.port.UserAuthRepository;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import com.craftlink.backend.auth.domain.model.user.vo.UserId;
import com.craftlink.backend.service.entities.ServiceEntity;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserAuthRepositoryAdapter implements UserAuthRepository {

  private final SpringDataUserReadRepositoryJpa jpa;

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
