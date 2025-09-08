package com.craftlink.backend.auth.domain.port;

import com.craftlink.backend.auth.domain.model.user.User;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import java.util.Optional;

public interface UserRepository {

  void save(User user);

  Optional<User> findByEmail(Email email);

  boolean existByEmail(Email email);
}
