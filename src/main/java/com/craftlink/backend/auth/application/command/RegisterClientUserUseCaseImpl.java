package com.craftlink.backend.auth.application.command;

import com.craftlink.backend.auth.adapter.out.security.PasswordHasherAdapter;
import com.craftlink.backend.auth.application.port.in.command.register.RegisterClientUserUseCase;
import com.craftlink.backend.auth.application.port.in.command.register.RegisterUserCommand;
import com.craftlink.backend.auth.application.port.out.external.ClientProfilePort;
import com.craftlink.backend.auth.application.port.out.write.UserRepository;
import com.craftlink.backend.auth.domain.model.user.User;
import com.craftlink.backend.auth.domain.model.user.vo.Email;
import com.craftlink.backend.auth.domain.model.user.vo.Password;
import com.craftlink.backend.auth.domain.model.user.vo.Username;
import com.craftlink.backend.infrastructure.exceptions.custom.BusinessException;
import com.craftlink.backend.infrastructure.exceptions.enums.ExceptionCode;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RegisterClientUserUseCaseImpl implements RegisterClientUserUseCase {

  private final UserRepository userRepository;
  private final PasswordHasherAdapter passwordHasherAdapter;
  private final ClientProfilePort clientProfile;

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

    clientProfile.createClientForUser(user.getId().value());
  }
}
