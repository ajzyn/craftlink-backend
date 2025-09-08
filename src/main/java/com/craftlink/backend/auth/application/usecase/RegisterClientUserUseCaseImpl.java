package com.craftlink.backend.auth.application.usecase;

import com.craftlink.backend.auth.adapter.acl.ClientProfileAcl;
import com.craftlink.backend.auth.adapter.security.PasswordHasherAdapter;
import com.craftlink.backend.auth.application.dto.RegisterUserCommand;
import com.craftlink.backend.auth.domain.model.user.User;
import com.craftlink.backend.auth.domain.model.user.vo.ClientId;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import com.craftlink.backend.auth.domain.model.user.vo.Password;
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
  public void registerClientUser(RegisterUserCommand cmd) {
    var email = new Email(cmd.email());

    if (userRepository.existByEmail(email)) {
      throw new BusinessException(ExceptionCode.USER_ALREADY_EXISTS);
    }

    var hashedPassword = passwordHasherAdapter.hash(cmd.password());

    var user = User.registerUser(
        new Email(cmd.email()),
        new Password(hashedPassword),
        Set.of()
    );

    userRepository.save(user);

    var clientId = clientProfileAcl.createClientForUser(user.getId().value());

    user.assignClientId(new ClientId(clientId));

    userRepository.save(user);

  }
}
