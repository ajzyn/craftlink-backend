package com.craftlink.backend.auth.application.port;

import com.craftlink.backend.auth.application.dto.UserSnapshot;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import com.craftlink.backend.auth.domain.model.user.vo.UserId;
import java.util.Optional;

public interface UserReadRepository {

  Optional<UserSnapshot> findByEmail(Email email);

  Optional<UserSnapshot> findById(UserId id);
}
