package com.craftlink.backend.auth.application.port;

import com.craftlink.backend.auth.application.dto.UserAuthView;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import com.craftlink.backend.auth.domain.model.user.vo.UserId;
import java.util.Optional;

public interface UserAuthRepository {

  Optional<UserAuthView> findByEmailWithServices(Email email);

  Optional<UserAuthView> findByIdWithServices(UserId id);
}
