package com.craftlink.backend.auth.application.port.out.read;

import com.craftlink.backend.auth.application.port.in.query.getUserProfile.UserAuthView;
import com.craftlink.backend.auth.application.port.in.query.getUserProfile.UserView;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import com.craftlink.backend.shared.domain.vo.UserId;
import java.util.Optional;

public interface UserQueryRepository {

  Optional<UserView> findByEmail(Email email);

  Optional<UserView> findById(UserId id);

  Optional<UserAuthView> findByEmailWithServices(Email email);

  Optional<UserAuthView> findByIdWithServices(UserId id);
}
