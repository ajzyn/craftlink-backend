package com.craftlink.backend.auth.adapter.persistence.write;

import com.craftlink.backend.auth.domain.model.user.User;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import com.craftlink.backend.auth.domain.port.UserRepository;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryAdapter implements UserRepository {

  @Override
  public void save(User user) {

  }

  @Override
  public Optional<User> findByEmail(Email email) {
    return Optional.empty();
  }

  @Override
  public boolean existByEmail(Email email) {
    return false;
  }
}
