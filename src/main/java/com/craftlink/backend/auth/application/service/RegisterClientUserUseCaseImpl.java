package com.craftlink.backend.auth.application.service;

import com.craftlink.backend.auth.adapter.acl.ClientProfileAcl;
import com.craftlink.backend.auth.adapter.security.PasswordHasherAdapter;
import com.craftlink.backend.auth.application.dto.RegisterUserCommand;
import com.craftlink.backend.auth.application.port.usecase.RegisterClientUserUseCase;
import com.craftlink.backend.auth.domain.model.user.User;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import com.craftlink.backend.auth.domain.model.user.vo.Password;
import com.craftlink.backend.auth.domain.model.user.vo.Username;
import com.craftlink.backend.auth.domain.port.UserRepository;
import com.craftlink.backend.config.exceptions.custom.BusinessException;
import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RegisterClientUserUseCaseImpl implements RegisterClientUserUseCase {

  private final UserRepository userRepository;
  private final PasswordHasherAdapter passwordHasherAdapter;
  private final ClientProfileAcl clientProfileAcl;

  @Override
  @Transactional
  public void handle(RegisterUserCommand cmd) {
    var email = new Email(cmd.email());

    if (userRepository.existByEmail(email)) {
      throw new BusinessException(ExceptionCode.USER_ALREADY_EXISTS);
    }

    var hashedPassword = passwordHasherAdapter.hash(cmd.password());

    var user = User.registerClient(
        new Email(cmd.email()),
        new Username(cmd.username()),
        new Password(hashedPassword),
        Set.of()
    );

    userRepository.save(user);

    clientProfileAcl.createClientForUser(user.getId().value());
  }
}
