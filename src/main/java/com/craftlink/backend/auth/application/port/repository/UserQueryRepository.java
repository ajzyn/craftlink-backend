package com.craftlink.backend.auth.application.port.repository;

import com.craftlink.backend.auth.application.dto.UserAuthView;
import com.craftlink.backend.auth.application.dto.UserView;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import com.craftlink.backend.auth.domain.model.user.vo.UserId;
import java.util.Optional;

public interface UserQueryRepository {

  Optional<UserView> findByEmail(Email email);

  Optional<UserView> findById(UserId id);

  Optional<UserAuthView> findByEmailWithServices(Email email);

  Optional<UserAuthView> findByIdWithServices(UserId id);
}
