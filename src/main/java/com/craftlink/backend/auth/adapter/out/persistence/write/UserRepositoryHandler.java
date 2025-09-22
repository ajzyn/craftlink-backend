package com.craftlink.backend.auth.adapter.out.persistence.write;

import com.craftlink.backend.auth.adapter.out.persistence.mapper.UserPersistenceMapper;
import com.craftlink.backend.auth.application.port.out.write.UserRepository;
import com.craftlink.backend.auth.domain.model.user.User;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryHandler implements UserRepository {

  private final UserRepositoryJpa jpa;
  private final UserPersistenceMapper mapper;

  @Override
  public void save(User user) {
    var entity = mapper.toEntity(user);
    jpa.save(entity);
  }

  @Override
  public boolean existByEmail(Email email) {
    return jpa.existsByEmail(email.value());
  }
}
