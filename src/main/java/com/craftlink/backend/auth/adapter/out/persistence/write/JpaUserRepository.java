package com.craftlink.backend.auth.adapter.out.persistence.write;

import com.craftlink.backend.auth.adapter.out.persistence.mapper.UserPersistenceMapper;
import com.craftlink.backend.auth.adapter.out.persistence.read.AuthorityQueryRepositorySpringData;
import com.craftlink.backend.auth.application.port.out.write.UserRepository;
import com.craftlink.backend.auth.domain.model.user.User;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import com.craftlink.backend.infrastructure.exceptions.custom.BusinessException;
import com.craftlink.backend.infrastructure.exceptions.enums.ExceptionCode;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {

  private final UserRepositorySpringData jpa;
  private final AuthorityQueryRepositorySpringData authorityJpa;
  private final UserPersistenceMapper mapper;

  @Override
  public void save(User user) {
    var entity = mapper.toEntity(user);
    var authorities = user.getAuthorities().stream()
        .map(code -> authorityJpa
            .findByCode(code.value())
            .orElseThrow(() -> new BusinessException(ExceptionCode.RESOURCE_NOT_FOUND, Map.of(
                "resource", "authority",
                "code", code.value()
            ))))
        .collect(Collectors.toSet());
    entity.setAuthorities(authorities);
    jpa.save(entity);
  }

  @Override
  public boolean existByEmail(Email email) {
    return jpa.existsByEmail(email.value());
  }
}
