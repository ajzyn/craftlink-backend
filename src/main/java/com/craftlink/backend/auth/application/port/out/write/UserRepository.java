package com.craftlink.backend.auth.application.port.out.write;

import com.craftlink.backend.auth.domain.model.user.User;
import com.craftlink.backend.auth.domain.model.user.vo.Email;

public interface UserRepository {

  void save(User user);

  boolean existByEmail(Email email);
}
